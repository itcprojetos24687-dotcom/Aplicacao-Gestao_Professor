package dao;
import java.sql.*;

import model.Formador;
import model.Sala;
import java.util.ArrayList;
public class SalaDao {
	public void cadastrarSala(Sala sala) throws ExceptionDao{
		String sql = "insert into Sala (designacao,tipo_sala) values(?,?)";
		Connection con = null;
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setString(1, sala.getDesignacao());
			inserir.setString(2,sala.getTipo());
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
	public ArrayList<Sala> listarSala(String designacao) throws ExceptionDao{
		String sql = "select * from Sala where nome=?";
		Connection con = null;
		PreparedStatement select = null;
		ArrayList<Sala> salas = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			ResultSet rs = select.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					Sala sala = new Sala();
					sala.setDesignacao(rs.getString("designacao"));
					sala.setTipo(rs.getString("tipo_sala"));
					salas.add(sala);
					
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
		return salas;
	}
	public void atualizarSala(Sala sala) throws ExceptionDao{
		String sql = "update Sala set designacao = ?, tipo_sala = ? where codigo = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			alterar.setString(1, sala.getDesignacao());
			alterar.setString(2, sala.getTipo());
			alterar.setInt(3, sala.getCodigo());
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
	public void apagarSala(Sala sala) throws ExceptionDao {

		String sql = "delete from Sala where codigo=?";
		PreparedStatement apagar = null;
		Connection con = null;
		try {
			
			con = new Conexao().getConnection();
			apagar = con.prepareStatement(sql);
			apagar.setInt(1, sala.getCodigo());
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
