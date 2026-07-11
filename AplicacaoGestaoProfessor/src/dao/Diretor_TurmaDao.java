package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Diretor_Turma;
import model.Formador;
import model.Qualificacao;


public class Diretor_TurmaDao {
	Connection con = null;
	public void cadastrarDiretor_Turma(Diretor_Turma dt) throws ExceptionDao {
		String sql = "insert into Diretor_Turma values (?)";
		PreparedStatement InsertFormador = null;
		try {
			
			con = new Conexao().getConnection();
			InsertFormador = con.prepareStatement(sql);
			
			InsertFormador.setInt(1, dt.getFomador().getCodigo());
			
			InsertFormador.execute();
			
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
	public ArrayList<Diretor_Turma> comboDiretor_Turma()throws ExceptionDao{
		String sql = "select * from Diretor_Turma";
		Connection con = null;
		PreparedStatement comboQualificacao = null;
		ArrayList<Diretor_Turma> diretor_turma= null;

		try {
			con = new Conexao().getConnection();
			comboQualificacao = con.prepareStatement(sql);
			ResultSet rs = comboQualificacao.executeQuery();

			if (rs != null) {
				diretor_turma = new ArrayList();
				while(rs.next()) {
					Diretor_Turma dt = new Diretor_Turma();
					Formador f = new Formador();
					f.setCodigo(rs.getInt("cod_Formador"));
					dt.setFormador(f);
					
					diretor_turma.add(dt);
				}
			}
		}catch(SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(comboQualificacao != null) {
					comboQualificacao.close();
				}
			}catch(Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}
			try {
				if(con != null) {
					con.close();
				}
			}
			catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}
		return diretor_turma;
	}
	/*public ArrayList<Diretor_Turma> listaDiretor_Turma(String nome) throws ExceptionDao{

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
	public void atualizarDiretor_Turma(Diretor_Turma diretor) {
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
	public void apagarDiretor_Turma(Diretor_Turma diretor) throws ExceptionDao {

		String sql = "delete from Formador where codigo=?";
		PreparedStatement apagarFormador = null;
		try {
			
			con = new Conexao().getConnection();
			apagarFormador = con.prepareStatement(sql);
			apagarFormador.setInt(1, formador.getCodigo());
			apagarFormador.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
			
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
	}*/
	public void atualizarDiretor_Turma(Diretor_Turma diretor, int codigo)throws ExceptionDao {
		String sql = "update Turma set cod_Formador = ? where cod_Formador = "+codigo;
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			alterar.setInt(1, diretor.getFomador().getCodigo());
			
			alterar.executeUpdate();
			//JOptionPane.showMessageDialog(null,"Alterado com sucesso");
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null,"Erro ao alterar");
			throw new ExceptionDao("Erro ao fechar  a conexao"+ e);
		}
		finally {
			try {
				if(alterar != null) {
					alterar.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ sq);
			}
			try {
				if(con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ l);
				//JOptionPane.showMessageDialog(null, "Falha de fechado ");
			}
			
		}
		
	}
}
