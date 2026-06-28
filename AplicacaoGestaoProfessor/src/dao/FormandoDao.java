package dao;
import model.Formando;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class FormandoDao {

	Connection con = null;
	public void cadastrarFormando(Formando formando) throws ExceptionDao{
		
		
		String sql = "insert into Formando(nome, apelido, contacto, email, bi)" + "values(?,?,?,?,?)";
		PreparedStatement InsertFormando = null;
		try {
			
			con = new Conexao().getConnection();
			InsertFormando = con.prepareStatement(sql);
			InsertFormando.setString(1, formando.getNome());
			InsertFormando.setString(2, formando.getApelido());
			InsertFormando.setInt(3, formando.getContacto());
			InsertFormando.setString(4, formando.getEmail());
			InsertFormando.setString(5, formando.getBi());
			InsertFormando.execute();
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			throw new ExceptionDao("Erro ao inserir dados :" + e );
		}finally {
			try {
				if(InsertFormando != null) {
					InsertFormando.close();
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
	public ArrayList<Formando> listarFormando(String nome ) throws ExceptionDao{
		
		String sql = "select * from formando where nome like '%" + nome + "%'";
		PreparedStatement listarFormando = null;
		ArrayList <Formando> formandos = null;
		
		try {
		con = new Conexao().getConnection();
			listarFormando = con.prepareStatement(sql);
				ResultSet rs = listarFormando.executeQuery();
				
				if (rs != null) {
					formandos = new ArrayList();
					while(rs.next()) {
					Formando formando = new Formando(); 
					
					formando.setCodigo(rs.getInt("codigo"));
					formando.setNome(rs.getString("nome"));
					formando.setApelido(rs.getString("apelido"));
					formando.setContacto(rs.getInt("contacto"));
					formando.setEmail(rs.getString("Email"));
					formando.setBi(rs.getString("Bi"));
					formandos.add(formando);
					//JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
					
					}
				}
		}catch(SQLException ex) {
			//JOptionPane.showMessageDialog(null, "Erro ao selecionar");
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(listarFormando != null) {
					listarFormando.close();
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
		return formandos;
			
	}
	public void atualizarFormando(Formando formando) {
		String  sql = "update Formando set nome = ?, apelido = ?, contacto = ?, email = ?, Bi = ? where codigo = ?";
		PreparedStatement alterarFormando = null;
		
		try {
			con = new Conexao().getConnection();
			alterarFormando = con.prepareStatement(sql);
			alterarFormando.setString(1, formando.getNome());
			alterarFormando.setString(2, formando.getApelido());
			alterarFormando.setInt(3, formando.getContacto());
			alterarFormando.setString(3, formando.getEmail());
			alterarFormando.setString(4, formando.getBi());
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null,"Erro ao alterar");
			e.printStackTrace();
		}
		finally {
			try {
				if(alterarFormando != null) {
					alterarFormando.close();
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

public void apagarFormando(Formando formando) throws ExceptionDao {

	String sql = "delete from Formador where codigo=?";
	PreparedStatement apagarFormando = null;
	try {
		
		con = new Conexao().getConnection();
		apagarFormando = con.prepareStatement(sql);
		apagarFormando.setInt(1, formando.getCodigo());
		apagarFormando.executeUpdate();
		//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		
	}catch(SQLException e) {
		//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
		 throw new ExceptionDao("Erro ao inserir dados :" + e);
	}finally {
		try {
			if(apagarFormando != null) {
				apagarFormando.close();
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

