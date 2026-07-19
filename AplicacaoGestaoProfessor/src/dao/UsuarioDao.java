package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.*;

public class UsuarioDao {

	public Usuario login(String username, String password) throws ExceptionDao {

		String sql = "select * from Usuario "
				+ "join Perfil on idPerfil = id "
				+ "where username=? and password=?";
		Connection con = null;
		PreparedStatement lg = null;
		Usuario u = null;
		Perfil p = null;

		try {
			con = new Conexao().getConnection();
			
			lg = con.prepareStatement(sql);
			lg.setString(1, username);
			lg.setString(2, password);
			ResultSet rs = lg.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					u = new Usuario();
					p = new Perfil();
					u.setCodigo(rs.getInt("idUser"));
					u.setUsername(rs.getString("username"));
					u.setApelido(rs.getString("apelido"));
					u.setPassword(rs.getString("password"));
					u.setPrimeiroAcesso(rs.getBoolean("primeiroAcesso"));
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("Perfil.nome"));
					u.setPerfil(p);
				}
			}

			if (u != null) {
				Logs log = new Logs("LOGIN", "Utilizador " + u.getUsername() + " iniciou sessão", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
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
		String sql = "insert into Usuario(nome, username, password, apelido, idPerfil,primeiroAcesso) values(?,?,?,?,?,1)";
		Connection con = null;
		PreparedStatement insertUsuario = null;

		try {

			con = new Conexao().getConnection();
			insertUsuario = con.prepareStatement(sql);
			insertUsuario.setString(1, usuario.getNome());
			insertUsuario.setString(2, usuario.getUsername());
			insertUsuario.setString(3, usuario.getPassword());
			insertUsuario.setString(4, usuario.getApelido());
			
			int idPerfil = usuario.getPerfil().getId();
			insertUsuario.setInt(5, idPerfil);
			insertUsuario.execute();
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				try {
					Logs log = new Logs("INSERT", usuario.getPerfil().getNome() + " " + usuario.getNome() + " foi cadastrado", u);
					log.setData(LocalDateTime.now());
					log.salvar(log);
				} catch (ExceptionDao logEx) {
					System.err.println("Aviso: falha ao gravar log de auditoria: " + logEx.getMessage());
				}
			}
			
		} catch (SQLException e) {
			if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
				throw new ExceptionDao("Esse nome de utilizador já existe. Escolha outro username.");
			}
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
		
		String sql = "select idUser,Usuario.nome,username,apelido,Perfil.nome from Usuario "
				+ " join Perfil on idPerfil = id "
				+ "where username like '%"+username+"%'";
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
					Perfil p = new Perfil();
					usuario.setCodigo(rs.getInt("idUser"));
					usuario.setNome(rs.getString("nome"));
					usuario.setUsername(rs.getString("username"));
					p.setNome(rs.getString("Perfil.nome"));
					usuario.setPerfil(p);
					usuario.setApelido(rs.getString("apelido"));
					

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
	public void refinirSenha(String novapassword,int codigo) throws ExceptionDao {
		String sql = "update Usuario set password=?, primeiroAcesso = 0 where idUser = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			
			alterar.setString(1, novapassword);
			alterar.setInt(2, codigo);
			
			alterar.executeUpdate();
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar senha"+ e);
		}
		finally {
			try {
				if(alterar != null) {
					alterar.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ sq);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ l);
			}
			
		}
		
	}

	public Usuario autenticar( String password) throws ExceptionDao {


		String sql = "select idUser, password from Usuario "
				+ " where password =?";
		Connection con = null;
		PreparedStatement lg = null;
		Usuario u = null;

		try {
			con = new Conexao().getConnection();
			lg = con.prepareStatement(sql);
			lg.setString(1, password);
			ResultSet rs = lg.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					u = new Usuario();
					u.setPassword(rs.getString("password"));
					u.setCodigo(rs.getInt("idUser"));
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

	public void resetarSenha(String senhaResetada, int codigo) throws ExceptionDao{
		String sql = "update Usuario set password = ?, primeiroAcesso = 1 where idUser ="+codigo;
		Connection con = null;
		PreparedStatement stms = null;
		try {
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setString(1, senhaResetada);
			stms.executeUpdate();

			Usuario logUser = Seccao.obterUtilizador();
			if (logUser != null) {
				try {
					Logs log = new Logs("UPDATE", "Senha do utilizador (ID: " + codigo + ") foi resetada", logUser);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				} catch (ExceptionDao logEx) {
					System.err.println("Aviso: falha ao gravar log: " + logEx.getMessage());
				}
			}
			
		}catch(SQLException sq) {
			throw new ExceptionDao("Erro ao atualizar senha: " + sq);
		}finally {
			try {
				if (stms != null) {
					stms.close();
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
	}

	public ArrayList<Usuario> obterTodosUsuarios() throws ExceptionDao {

		String sql = "select idUser,nome_completo,username,email,Perfil.nome from Usuario "
				+ " join Perfil on idPerfil = id ";
				
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
					Perfil p =new Perfil();
					usuario.setCodigo(rs.getInt("idUser"));
					usuario.setNome(rs.getString("nome"));
					usuario.setUsername(rs.getString("username"));
					p.setNome(rs.getString("nome"));
					usuario.setPerfil(p);
					usuario.setApelido(rs.getString("apelido"));
					

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
				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
			}
		}

		return usuario;
	}
	public void atualizarUsuario(Usuario usuario) throws ExceptionDao {
		String sql = "update Usuario set idPerfil = ? where idUser = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		// Buscar o username atual antes de atualizar, para a mensagem do log ficar completa
		String usernameAtual = "";
		try {
			Usuario existente = obterUsuarioPorCodigo(usuario.getCodigo());
			if (existente != null) {
				usernameAtual = existente.getUsername();
			}
		} catch (Exception ex) {
			// se não conseguir buscar, segue com nome vazio
		}
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			
			alterar.setInt(1,usuario.getPerfil().getId());
			alterar.setInt(2, usuario.getCodigo());
			
			alterar.executeUpdate();

			Usuario logUser = Seccao.obterUtilizador();
			if (logUser != null) {
				try {
					Logs log = new Logs("UPDATE", "Utilizador " + usernameAtual + " (ID: " + usuario.getCodigo() + ") teve o perfil atualizado para " + usuario.getPerfil().getNome(), logUser);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				} catch (ExceptionDao logEx) {
					System.err.println("Aviso: falha ao gravar log: " + logEx.getMessage());
				}
			}
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados"+ e);
		}
		finally {
			try {
				if(alterar != null) {
					alterar.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ sq);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ l);
			}
			
		}
	}
	public void apagarUsuario(Usuario usuario) throws ExceptionDao {
		String sql = "delete from Usuario where idUser=?";
		PreparedStatement apagar = null;
		Connection con = null;
		try {
			
			con = new Conexao().getConnection();
			apagar = con.prepareStatement(sql);
			apagar.setInt(1, usuario.getCodigo());
			apagar.executeUpdate();

			Usuario logUser = Seccao.obterUtilizador();
			if (logUser != null) {
				try {
					Logs log = new Logs("DELETE", "Utilizador (ID: " + usuario.getCodigo() + ") foi removido", logUser);
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

	public void registarLogout(Usuario usuario) throws ExceptionDao {
		if (usuario != null) {
			Logs log = new Logs("LOGOUT", "Utilizador " + usuario.getUsername() + " terminou sessão", usuario);
			log.setData(LocalDateTime.now());
			new LogDao().salvar(log);
		}
	}
}