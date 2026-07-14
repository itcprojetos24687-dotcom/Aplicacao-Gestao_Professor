package dao;
import java.sql.*;
import java.time.LocalDateTime;

import model.Quali_modulo;
import model.Logs;
import model.Seccao;
import model.Usuario;
import java.util.ArrayList;

public class Quali_moduloDao {
	
	public void cadastrarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
		String sql = "insert into Quali_modulo (cod_modulo,cod_Quali,semestre) values(?,?,?)";
		Connection con = null;
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setInt(1, quali_modulo.getModulo().getCodigo());
			inserir.setInt(2, quali_modulo.getQualificacao().getCodigo());
			inserir.setString(3, quali_modulo.getSemestre());
			inserir.execute();

			// LOG: Cadastro de quali_modulo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Quali_modulo do semestre " + quali_modulo.getSemestre() + " foi cadastrado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(inserir != null) {
					inserir.close();
				}
			}catch(SQLException ql) {
				throw new ExceptionDao("Erro ao fechar o statement: " + ql);
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
			}
		}
		
	}
	public ArrayList<Quali_modulo> listarQuali_modulo(String semestre) throws ExceptionDao{
		String sql = "select * from Quali_modulo where semestre = ?";
		Connection con = null;
		PreparedStatement select = null;
		ArrayList<Quali_modulo> quali_modulos = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			select.setString(1, semestre);
			ResultSet rs = select.executeQuery();
			
			if (rs != null) {
				quali_modulos = new ArrayList<>();
				while (rs.next()) {
					Quali_modulo quali_modulo = new Quali_modulo();
					quali_modulo.setCodigo(rs.getInt("codigo"));
					quali_modulo.setSemestre(rs.getString("semestre"));
					quali_modulos.add(quali_modulo);
					
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
				throw new ExceptionDao("Erro ao fechar o statement: " + e);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar a conexao: " + s);
			}
		}
		return quali_modulos;
	}
	public void atualizarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
		String sql = "update Quali_modulo set cod_modulo = ?, cod_Quali = ?, semestre = ? where codigo = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			alterar.setInt(1, quali_modulo.getModulo().getCodigo());
			alterar.setInt(2, quali_modulo.getQualificacao().getCodigo());
			alterar.setString(3, quali_modulo.getSemestre());
			alterar.setInt(4, quali_modulo.getCodigo());
			alterar.executeUpdate();

			// LOG: Atualização de quali_modulo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Quali_modulo (ID: " + quali_modulo.getCodigo() + ") do semestre " + quali_modulo.getSemestre() + " foi atualizado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
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
				throw new ExceptionDao("Erro ao fechar o statement: " + sq);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar a conexao: " + l);
			}
			
		}
		
	}
	public void apagarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao {

		String sql = "delete from Quali_modulo where codigo=?";
		PreparedStatement apagar = null;
		Connection con = null;

		// Buscar dados do quali_modulo antes de apagar para o log
		String semestre = "";
		PreparedStatement buscar = null;
		try {
			con = new Conexao().getConnection();
			buscar = con.prepareStatement("select semestre from Quali_modulo where codigo = ?");
			buscar.setInt(1, quali_modulo.getCodigo());
			ResultSet rs = buscar.executeQuery();
			if (rs.next()) {
				semestre = rs.getString("semestre");
			}
		} catch (SQLException e) {
			// Se não conseguir buscar, continua com dados vazios
		} finally {
			try {
				if (buscar != null) {
					buscar.close();
				}
			} catch (SQLException e) {
				// Ignorar erro ao fechar
			}
		}
		
		try {
			if (con == null || con.isClosed()) {
				con = new Conexao().getConnection();
			}
			apagar = con.prepareStatement(sql);
			apagar.setInt(1, quali_modulo.getCodigo());
			apagar.executeUpdate();

			// LOG: Exclusão de quali_modulo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Quali_modulo (ID: " + quali_modulo.getCodigo() + ") do semestre " + semestre + " foi removido", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
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
