package dao;
import model.Formando;
import model.Modulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;



public class ModuloDao {
	
	Connection con = null;
	public void cadastrarModulo(Modulo modulo) throws ExceptionDao{
		
		String sql = "insert into Modulo(nome, carga_horaria)" + "values(?,?)";
		PreparedStatement InsertModulo = null;
		try {
			
			con = new Conexao().getConnection();
			InsertModulo = con.prepareStatement(sql);
			InsertModulo.setString(1, modulo.getNome());
			InsertModulo.setInt(2, modulo.getCarga_horaria());
			InsertModulo.execute();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Statemente fechado com sucesso");
			throw new ExceptionDao ("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(InsertModulo != null) {
					InsertModulo.close();
					JOptionPane.showMessageDialog(null, "Statemente fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
					JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
				}
			}catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao");
			}
		}
	}
	public static void main(String[] args) throws ExceptionDao {
		
	}
	public ArrayList<Modulo>listarModulo(String nome) throws ExceptionDao{
		String sql = "select * from Modulo whre nome like '%" + nome +"%'";
		PreparedStatement listarModulo = null;
		ArrayList <Modulo> modulos = null;
		
		
		try {
			con = new Conexao().getConnection();
			listarModulo = con.prepareStatement(sql);
			ResultSet rs = listarModulo.executeQuery();
			
			if (rs != null) {
				modulos = new ArrayList();
				while(rs.next()) {
					Modulo modulo = new Modulo();
					
					modulo.setCodigo(rs.getInt("codigo"));
					modulo.setNome(rs.getString("nome"));
					modulo.setCarga_horaria(rs.getInt("Carga_horaria"));
					modulos.add(modulo);
					JOptionPane.showMessageDialog(null, "Adicionar com sucesso");
					
				}
			}
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao selecionar");
			throw new ExceptionDao("Erro ao selecionar" + ex );
			
		}finally {
			try {
				if(listarModulo != null) {
					listarModulo.close();
					JOptionPane.showMessageDialog(null, "statement fechado com sucesso");
				}
			}catch(Exception es) {
				JOptionPane.showMessageDialog(null, "Erro ao fechar statement ");
				throw new ExceptionDao("Erro ao fechar o statement" + es);
			}
			try {
				if(con != null) {
					con.close();
					JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
				}
			}
			catch(SQLException sq) {
				JOptionPane.showMessageDialog(null, "Erro ao fechar conexao");
				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
			}
		}
		return modulos;
	}
	public void atualizarModulo(Modulo modulo) {
		String sql = "update Modulo set nome = ?, carga_horaria= ? where codigo = ?";
		PreparedStatement alterarModulo = null;
		
		try {
			con = new Conexao().getConnection();
			alterarModulo = con.prepareStatement(sql);
			alterarModulo.setString(1, modulo.getNome());
			alterarModulo.setInt(2, modulo.getCarga_horaria());
			JOptionPane.showMessageDialog(null, "Alterado com sucesso");
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar");
			e.printStackTrace();
		}
		finally {
			try {
				if(alterarModulo != null) {
					alterarModulo.close();
					JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException sq) {
				sq.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
					JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException l) {
				JOptionPane.showMessageDialog(null, "Falha de fechado ");
			}
		}
	}
	public void apagarModulo(Modulo modulo) throws ExceptionDao {
		

		String sql = "delete from Modulo where codigo=?";
		PreparedStatement apagarModulo = null;
		try {
			
			con = new Conexao().getConnection();
			apagarModulo = con.prepareStatement(sql);
			apagarModulo.setInt(1, modulo.getCodigo());
			apagarModulo.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			 throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(apagarModulo != null) {
					apagarModulo.close();
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
