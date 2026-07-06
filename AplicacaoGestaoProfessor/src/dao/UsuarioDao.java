package dao;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioDao {

	public Usuario login(String username, String password) throws ExceptionDao {

		String sql = "select * from Usuario where username=? and password=?";
		Connection con = null;
		PreparedStatement lg = null;
		Usuario u = null;

		try {
			con = new Conexao().getConnection();
			lg = con.prepareStatement(sql);
			lg.setString(1, username);
			lg.setString(2, password);
			ResultSet rs = lg.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					u = new Usuario();
					u.setCodigo(rs.getInt("codigo"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao fazer login" + e);

		} finally {

			try {
				if (lg != null) {
					lg.close();
				}
			} catch (Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
			}
		}

		return u;
	}

	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {

		String sql = "insert into Usuario(nome_completo, username, password, email, idPerfil) values(?,?,?,?,?)";
		Connection con = null;
		PreparedStatement insertUsuario = null;

		try {

			con = new Conexao().getConnection();
			insertUsuario = con.prepareStatement(sql);
			insertUsuario.setString(1, usuario.getNome_completo());
			insertUsuario.setString(2, usuario.getUsername());
			insertUsuario.setString(3, usuario.getPassword());
			insertUsuario.setString(4, usuario.getEmail());
			insertUsuario.setInt(5, usuario.getIdPerfil());
			insertUsuario.execute();
			

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);

		} finally {

			try {
				if (insertUsuario != null) {
					insertUsuario.close();
				}
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}

	public ArrayList<Usuario> listarUsuario(String username) throws ExceptionDao {

		String sql = "select * from Usuario where username like '%" + username + "%'";
		Connection con = null;
		PreparedStatement listarUsuario = null;
		ArrayList<Usuario> usuarios = null;

		try {

			con = new Conexao().getConnection();
			listarUsuario = con.prepareStatement(sql);
			ResultSet rs = listarUsuario.executeQuery();

			if (rs != null) {
				usuarios = new ArrayList<>();

				while (rs.next()) {

					Usuario usuario = new Usuario();

					usuario.setCodigo(rs.getInt("codigo"));
					usuario.setUsername(rs.getString("username"));
					usuario.setPassword(rs.getString("password"));

					usuarios.add(usuario);
				}
			}

		} catch (SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);

		} finally {

			try {
				if (listarUsuario != null) {
					listarUsuario.close();
				}
			} catch (Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}

		return usuarios;
	}

	public ArrayList<Usuario> obterTodosUsuarios() throws ExceptionDao {

		String sql = "select * from Usuario";
		Connection con = null;
		PreparedStatement obterTodos = null;
		ArrayList<Usuario> usuarios = null;

		try {

			con = new Conexao().getConnection();
			obterTodos = con.prepareStatement(sql);
			ResultSet rs = obterTodos.executeQuery();

			if (rs != null) {

				usuarios = new ArrayList<>();

				while (rs.next()) {

					Usuario usuario = new Usuario();

					usuario.setCodigo(rs.getInt("codigo"));
					usuario.setUsername(rs.getString("username"));
					usuario.setPassword(rs.getString("password"));

					usuarios.add(usuario);
				}
			}

		} catch (SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);

		} finally {

			try {
				if (obterTodos != null) {
					obterTodos.close();
				}
			} catch (Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}

		return usuarios;
	}

	public Usuario obterUsuarioPorCodigo(int codigo) throws ExceptionDao {

		String sql = "select * from Usuario where codigo=?";
		Connection con = null;
		PreparedStatement obterPorCodigo = null;
		Usuario usuario = null;

		try {

			con = new Conexao().getConnection();
			obterPorCodigo = con.prepareStatement(sql);
			obterPorCodigo.setInt(1, codigo);

			ResultSet rs = obterPorCodigo.executeQuery();

			if (rs != null) {

				while (rs.next()) {

					usuario = new Usuario();

					usuario.setCodigo(rs.getInt("codigo"));
					usuario.setUsername(rs.getString("username"));
					usuario.setPassword(rs.getString("password"));
				}
			}

		} catch (SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);

		} finally {

			try {
				if (obterPorCodigo != null) {
					obterPorCodigo.close();
				}
			} catch (Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}

		return usuario;
	}

	public void atualizarUsuario(Usuario usuario) throws ExceptionDao {
	}

	public void apagarUsuario(Usuario usuario) throws ExceptionDao {
	}
}