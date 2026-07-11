package dao;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;



public class FormadorDao {

	Connection con = null;
	public void cadastrarFormador(Formador formador) throws ExceptionDao {
		String sql = "insert into Formador(nome, apelido, email, genero, estadoCivil, contacto, salario)" + "values(?,?,?,?,?,?,?)";
		PreparedStatement InsertFormador = null;
		try {
			
			con = new Conexao().getConnection();
			InsertFormador = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			InsertFormador.setString(1, formador.getNome());
			InsertFormador.setString(2, formador.getApelido());
			InsertFormador.setString(3, formador.getEmail() );
			InsertFormador.setString(4, formador.getGenero());
			InsertFormador.setString(5, formador.getEstadoCivil());
			InsertFormador.setInt(6, formador.getContacto());
			InsertFormador.setDouble(7, formador.getSalario());
			InsertFormador.execute();
			Usuario u = Seccao.obterUtilizador();
			Logs log = new Logs("INSERT","Formador: "+formador.getNome()+"cadastro",u );
			new LogDao().salvar(log);
			
			ResultSet rs = InsertFormador.getGeneratedKeys();
			if(rs != null  && rs.next()) {
				formador.setCodigo(rs.getInt(1));
				
			}
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			 throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(InsertFormador != null) {
					InsertFormador.close();
					//JOptionPane.showMessageDialog(null, "Statemente fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
				}
			}catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}
	public ArrayList<Formador> listarFormador(String nome) throws ExceptionDao{

		String sql = "select * from Formador where nome like '%" + nome + "%'";
		PreparedStatement listarFormador = null;
		ArrayList <Formador> formadores = null;
		
		
		try {
			con = new Conexao().getConnection();
			listarFormador = con.prepareStatement(sql);
			ResultSet rs = listarFormador.executeQuery();
			
			if (rs != null) {
				formadores = new ArrayList();
				while(rs.next()) {
					Formador formador = new Formador();
					
					formador.setCodigo(rs.getInt("codigo"));
					formador.setNome(rs.getString("nome"));
					formador.setApelido(rs.getString("apelido"));
					formador.setEmail(rs.getString("email"));
					formador.setGenero(rs.getString("genero"));
					formador.setEstadoCivil(rs.getString("estadoCivil"));
					formador.setContacto(rs.getInt("contacto"));
					formador.setSalario(rs.getDouble("salario"));
					formadores.add(formador);
					//JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
				}
			}
			
			
		}catch(SQLException ex) {
			//JOptionPane.showMessageDialog(null, "Erro ao selecionar");
			throw new ExceptionDao("Erro ao selecionar dados" + ex);
			
		}finally {
			try {
				if(listarFormador != null) {
					listarFormador.close();
					//JOptionPane.showMessageDialog(null, "statement fechado com sucesso");
				}
			}catch(Exception es) {
				//JOptionPane.showMessageDialog(null, "Erro ao fechar statement");
				throw new ExceptionDao("Erro ao fechar o statement: "+ es);
			}
			try {
				if(con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
				}			
			}
			catch(SQLException sq) {
				//JOptionPane.showMessageDialog(null, "Erro ao fechar conexao");
				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
			}
		}
		return formadores;
		
	}
	public void atualizarFormador(Formador formador) {
		String sql = "update Formador set nome = ?, apelido = ?, email = ?, genero = ?, estadoCivil = ?, contacto = ?, salario = ? where codigo = ?";
		PreparedStatement alterarFormador = null;
		
		try {
			con = new Conexao().getConnection();
			alterarFormador = con.prepareStatement(sql);
			alterarFormador.setString(1, formador.getNome());
			alterarFormador.setString(2, formador.getApelido());
			alterarFormador.setString(3, formador.getEmail());
			alterarFormador.setString(4, formador.getGenero());
			alterarFormador.setString(5, formador.getEstadoCivil());
			alterarFormador.setInt(6, formador.getContacto());
			alterarFormador.setDouble(7, formador.getSalario());
			alterarFormador.setInt(8, formador.getCodigo());
			alterarFormador.executeUpdate();
			//JOptionPane.showMessageDialog(null,"Alterado com sucesso");
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null,"Erro ao alterar");
			e.printStackTrace();
		}
		finally {
			try {
				if(alterarFormador != null) {
					alterarFormador.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException sq) {
				sq.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException l) {
				//JOptionPane.showMessageDialog(null, "Falha de fechado ");
			}
			
		}
	}
	public void apagarFormador(Formador formador) throws ExceptionDao {

		String sql = "delete from Formador where codigo=?";
		PreparedStatement apagarFormador = null;
		try {
			
			con = new Conexao().getConnection();
			apagarFormador = con.prepareStatement(sql);
			apagarFormador.setInt(1, formador.getCodigo());
			apagarFormador.executeUpdate();
			
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			 throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(apagarFormador != null) {
					apagarFormador.close();
					//JOptionPane.showMessageDialog(null, "Statemente fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
				}
			}catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}
}

