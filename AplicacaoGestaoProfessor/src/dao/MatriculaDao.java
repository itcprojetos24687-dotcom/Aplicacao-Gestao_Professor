package dao;
import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class MatriculaDao {

	Connection con = null;
	public void cadastrarMatricula(Matricula matricula) throws ExceptionDao{
		
		
		String sql = "insert into Matricula(cod_formando,cod_Quali,data)" + " values(?,?,?)";
		PreparedStatement InsertMatricula = null;
		try {
			
			con = new Conexao().getConnection();
			InsertMatricula = con.prepareStatement(sql);
			InsertMatricula.setInt(1, matricula.getFormando().getCodigo());
			InsertMatricula.setInt(2, matricula.getQualificacao().getCodigo());
			InsertMatricula.setString(3, matricula.getData_matricula());
			
			InsertMatricula.execute();
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			throw new ExceptionDao("Erro ao inserir dados :" + e );
		}finally {
			try {
				if(InsertMatricula != null) {
					InsertMatricula.close();
					//JOptionPane.showMessageDialog(null, "Statemente fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
			if (con != null) {
				con.close();
				//JOptionPane.showMessageDialog(null, "Conexao fechado com Sucesso" );
			}
		}catch(SQLException f) {
			throw new ExceptionDao("Erro ao fechar a conexao ");
		}
	}
}
	public ArrayList<Matricula> listarMatricula(String data) throws ExceptionDao{
		
		String sql = "select Matricula.codigo, nome_formando,apelido_formando, titulo, Nivel.nome as nivel, data from Matricula"
				+ " join Formando on codigo_formando = cod_formando "
				+ "join Qualificacao on Qualificacao.cod_Quali = Matricula.cod_Quali "
				+ "join Quali_Nivel on Qualificacao.cod_Quali= Quali_Nivel.cod_Quali "
				+ "join Nivel on Nivel.codigo= cod_Nivel";
		PreparedStatement listarMatricula = null;
		ArrayList <Matricula> matriculas = null;
		
		try {
		con = new Conexao().getConnection();
			listarMatricula = con.prepareStatement(sql);
				ResultSet rs = listarMatricula.executeQuery();
				
				if (rs != null) {
					matriculas = new ArrayList();
					while(rs.next()) {
					Formando f = new Formando();
					Qualificacao q = new Qualificacao();
					Nivel n = new Nivel();
					int codigo = rs.getInt("codigo");
					f.setNome(rs.getString("nome_formando"));
					f.setApelido(rs.getString("apelido_formando"));
					
					q.setTitulo(rs.getString("titulo"));
					n.setNome(rs.getString("nivel"));
					String data1 = rs.getString("data");
					
					 Matricula matricula = new Matricula(f,q,n,data1); 
					 matricula.setCodigo(codigo);
					matriculas.add(matricula);
				
					
					}
				}
		}catch(SQLException ex) {
			//JOptionPane.showMessageDialog(null, "Erro ao selecionar");
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(listarMatricula != null) {
					listarMatricula.close();
					//JOptionPane.showMessageDialog(null, "statement fechado com sucesso");
				}
			}catch(Exception es) {
				//JOptionPane.showMessageDialog(null, "Erro ao fechar statement");
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}
			try {
				if(con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
				}
			}
			catch(SQLException sq) {
				//JOptionPane.showMessageDialog(null, "Erro ao fechar conexao");
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}
		return matriculas;
			
	}
	public void atualizarMatricula(Matricula matricula) {
		String  sql = "update Matricula set cod_formando = ?, cod_Quali = ? ,data_matricula = ? where codigo = ?";
		PreparedStatement alterarMatricula = null;
		
		try {
			con = new Conexao().getConnection();
			alterarMatricula = con.prepareStatement(sql);
			alterarMatricula.setInt(1, matricula.getFormando().getCodigo());
			alterarMatricula.setInt(2, matricula.getQualificacao().getCodigo());
			alterarMatricula.setString(3, matricula.getData_matricula());
			
			alterarMatricula.setInt(3, matricula.getCodigo());
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null,"Erro ao alterar");
			e.printStackTrace();
		}
		finally {
			try {
				if(alterarMatricula != null) {
					alterarMatricula.close();
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

public void apagarMatricula(Matricula matricula) throws ExceptionDao {

	String sql = "delete from Matricula where codigo=?";
	PreparedStatement apagarMatricula = null;
	try {
		
		con = new Conexao().getConnection();
		apagarMatricula = con.prepareStatement(sql);
		apagarMatricula.setInt(1, matricula.getCodigo());
		apagarMatricula.executeUpdate();
		//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		
	}catch(SQLException e) {
		//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
		 throw new ExceptionDao("Erro ao inserir dados :" + e);
	}finally {
		try {
			if(apagarMatricula != null) {
				apagarMatricula.close();
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