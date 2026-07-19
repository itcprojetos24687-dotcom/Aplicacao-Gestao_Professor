package dao;
import java.sql.*;
import java.time.LocalDateTime;

import model.Formador;
import model.Sala;
import model.Logs;
import model.Usuario;
import model.Seccao;
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

			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				try {
					Logs log = new Logs("INSERT", "Sala " + sala.getDesignacao() + " foi cadastrada", u);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				} catch (ExceptionDao logEx) {
					System.err.println("Aviso: falha ao gravar log: " + logEx.getMessage());
				}
			}
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(inserir != null) {
					inserir.close();
				}
			}catch(SQLException ql) {
				throw new ExceptionDao("Erro ao fechar o statement" + ql);
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao" +sq);
			}
		}
		
	}
	public ArrayList<Sala> listarSala(String designacao) throws ExceptionDao{
		String sql = "select * from Sala where designacao like ?";
		Connection con = null;
		PreparedStatement select = null;
		ArrayList<Sala> salas = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			select.setString(1, "%" + designacao + "%");
			ResultSet rs = select.executeQuery();
			
			if(rs != null) {
				salas = new ArrayList<>();
				while (rs.next()) {
					Sala sala = new Sala();
					sala.setCodigo(rs.getInt("codigo"));
					sala.setDesignacao(rs.getString("designacao"));
					sala.setTipo(rs.getString("tipo_sala"));
					salas.add(sala);
					
				}
			}
		}catch(SQLException sq) {
			throw new ExceptionDao("Erro ao selecionar dados: " + sq);
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

			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				try {
					Logs log = new Logs("UPDATE", "Sala " + sala.getDesignacao() + " (ID: " + sala.getCodigo() + ") foi atualizada", u);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				} catch (ExceptionDao logEx) {
					System.err.println("Aviso: falha ao gravar log: " + logEx.getMessage());
				}
			}
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados: " + e);
		}
		finally {
			try {
				if(alterar != null) {
					alterar.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement: "+ sq);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar a conexao: "+ l);
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

			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				try {
					Logs log = new Logs("DELETE", "Sala (ID: " + sala.getCodigo() + ") foi removida", u);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				} catch (ExceptionDao logEx) {
					System.err.println("Aviso: falha ao gravar log: " + logEx.getMessage());
				}
			}
			
		}catch(SQLException e) {
			 throw new ExceptionDao("Erro ao apagar dados :" + e);
		}finally {
			try {
				if(apagar != null) {
					apagar.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}
}