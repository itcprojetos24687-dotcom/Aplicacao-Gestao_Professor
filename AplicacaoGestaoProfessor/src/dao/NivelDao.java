package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Nivel;

public class NivelDao {
	public void cadastrarNivel(Nivel nivel) throws ExceptionDao{
		String sql = "insert into Nivel (nome) values(?)";
		Connection con = null;
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setString(1, nivel.getNome());
			inserir.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(inserir != null) {
					inserir.close();
				}
			}catch(SQLException ql) {
				ql.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException sq) {
				new ExceptionDao("Erro ao fechar conexao" +sq);
			}
		}
		
	}
	public ArrayList<Nivel> listarNivel(String nome) throws ExceptionDao{
		String sql = "select * from Nivel where nome like '%" + nome+ "%'";
		Connection con = null;
		PreparedStatement select = null;
		ArrayList<Nivel> niveis = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			ResultSet rs = select.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					Nivel nivel = new Nivel();
					nivel.setCodigo(rs.getInt("codigo"));
					nivel.setNome(rs.getString("nome"));
					niveis.add(nivel);
					
				}
			}
		}catch(SQLException sq) {
			throw new ExceptionDao("Erro ao inserir dados");
		}finally {
			try{
				if(select != null) {
					select.close();
				}
			}catch(SQLException e) {
				throw new ExceptionDao("Erro ao fechar o statemnt"+e);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar a conexao"+s);
			}
		}
		return niveis;
	}
	public void atualizarNivel(Nivel nivel) throws ExceptionDao{
		String sql = "update Nivel set nome = ? where codigo = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			alterar.setString(1, nivel.getNome());
			alterar.setInt(2, nivel.getCodigo());
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
	public void apagarNivel(Nivel nivel) throws ExceptionDao {

		String sql = "delete from Campo where codigo=?";
		PreparedStatement apagar = null;
		Connection con = null;
		try {
			
			con = new Conexao().getConnection();
			apagar = con.prepareStatement(sql);
			apagar.setInt(1, nivel.getCodigo());
			apagar.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			 throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(apagar != null) {
					apagar.close();
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
