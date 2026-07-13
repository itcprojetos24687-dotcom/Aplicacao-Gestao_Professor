package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Nivel;
import model.Logs;
import model.Seccao;
import model.Usuario;

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

			// LOG: Cadastro de nível (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Nível " + nivel.getNome() + " foi cadastrado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados: " + e);
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
	public ArrayList<Nivel> listarNivel() throws ExceptionDao{
		String sql = "select * from Nivel ";
		Connection con = null;
		PreparedStatement select = null;
		ArrayList<Nivel> niveis = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			ResultSet rs = select.executeQuery();
			if(rs != null) {
				niveis = new ArrayList<Nivel>();
				while (rs.next()) {
					Nivel nivel = new Nivel();
					nivel.setCodigo(rs.getInt("codigo"));
					nivel.setNome(rs.getString("nome"));
					//JOptionPane.showMessageDialog(null, nivel.getCodigo()+" "+nivel.getNome());
					niveis.add(nivel);
					
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

			// LOG: Atualização de nível (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Nível " + nivel.getNome() + " (ID: " + nivel.getCodigo() + ") foi atualizado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			//JOptionPane.showMessageDialog(null,"Alterado com sucesso");
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null,"Erro ao alterar");
			throw new ExceptionDao("Erro ao alterar dados: " + e);
		}
		finally {
			try {
				if(alterar != null) {
					alterar.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement: " + sq);
			}
			try {
				if(con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar a conexao: " + l);
				//JOptionPane.showMessageDialog(null, "Falha de fechado ");
			}
			
		}
		
	}
	public void apagarNivel(Nivel nivel) throws ExceptionDao {

		String sql = "delete from Nivel where codigo=?";
		PreparedStatement apagar = null;
		Connection con = null;

		// Buscar nome do nível antes de apagar para o log
		String nomeNivel = "";
		PreparedStatement buscar = null;
		try {
			con = new Conexao().getConnection();
			buscar = con.prepareStatement("select nome from Nivel where codigo = ?");
			buscar.setInt(1, nivel.getCodigo());
			ResultSet rs = buscar.executeQuery();
			if (rs.next()) {
				nomeNivel = rs.getString("nome");
			}
		} catch (SQLException e) {
			// Se não conseguir buscar, continua com nome vazio
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
			apagar.setInt(1, nivel.getCodigo());
			apagar.executeUpdate();

			// LOG: Exclusão de nível (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Nível " + nomeNivel + " (ID: " + nivel.getCodigo() + ") foi removido", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			 throw new ExceptionDao("Erro ao apagar dados :" + e);
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
	public ArrayList<Nivel> comboNivel() throws ExceptionDao{
		String sql = "select * from Nivel ";
		Connection con = null;
		PreparedStatement select = null;
		ArrayList<Nivel> niveis = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			ResultSet rs = select.executeQuery();
			if(rs != null) {
				niveis = new ArrayList<Nivel>();
				while (rs.next()) {
					Nivel nivel = new Nivel();
					nivel.setCodigo(rs.getInt("codigo"));
					nivel.setNome(rs.getString("nome"));
					//JOptionPane.showMessageDialog(null, nivel.getCodigo()+" "+nivel.getNome());
					niveis.add(nivel);
					
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
		return niveis;
	}

	public Nivel obterNivelPorCodigo(int codigo) throws ExceptionDao{
		String sql = "select * from Nivel where codigo = ?";
		Connection con = null;
		PreparedStatement select = null;
		Nivel nivel = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			select.setInt(1, codigo);
			ResultSet rs = select.executeQuery();
			if (rs.next()) {
				nivel = new Nivel();
				nivel.setCodigo(rs.getInt("codigo"));
				nivel.setNome(rs.getString("nome"));
			}
		}catch(SQLException sq) {
			throw new ExceptionDao("Erro ao buscar nível: " + sq);
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
		return nivel;
	}
}