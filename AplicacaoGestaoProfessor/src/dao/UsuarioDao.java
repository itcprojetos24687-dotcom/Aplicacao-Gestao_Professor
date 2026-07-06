package dao;

import java.sql.*;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.*;

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
			//JOptionPane.showMessageDialog(null, "Execute Query feita com sucesso");
			if (rs != null) {
				//JOptionPane.showMessageDialog(null, "Result set nao e null, pegou algo");
				while (rs.next()) {
					u = new Usuario();
					u.setCodigo(rs.getInt("idUser"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					//JOptionPane.showMessageDialog(null, u.getUsername()+""+u.getPassword());
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
			
			int idPerfil = usuario.getPerfil().getId();
			insertUsuario.setInt(5, idPerfil);
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

		String sql = "select idUser,nome_completo,username,email,Perfil.nome from Usuario "
				+ " join Perfil on idPerfil = id "
				+ "where username like '%"+username+"%'";
		Connection con = null;
		PreparedStatement listarUsuario = null;
		ArrayList<Usuario> usuarios = null;

		try {

			con = new Conexao().getConnection();
			listarUsuario = con.prepareStatement(sql);
			//listarUsuario.setString(1, username);
			ResultSet rs = listarUsuario.executeQuery();

			if (rs != null) {
				usuarios = new ArrayList<>();

				while (rs.next()) {

					Usuario usuario = new Usuario();
					Perfil p = new Perfil();
					usuario.setCodigo(rs.getInt("idUser"));
					usuario.setNome_completo(rs.getString("nome_completo"));
					usuario.setUsername(rs.getString("username"));
					//usuario.setIdPerfil(rs.getInt("idPerfil"));
					p.setNome(rs.getString("nome"));
					usuario.setPerfil(p);
					usuario.setEmail(rs.getString("email"));
					

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
	public void refinirSenha(String novapassword,String antigapassword) throws ExceptionDao {
		String sql = "update Usuario set password=? where password = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			
			alterar.setString(1, novapassword);
			alterar.setString(2, antigapassword);
			
			
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
	public Usuario autenticar( String password) throws ExceptionDao {

		String sql = "select * from Usuario where password=?";
		Connection con = null;
		PreparedStatement lg = null;
		Usuario u = null;

		try {
			con = new Conexao().getConnection();
			lg = con.prepareStatement(sql);
			lg.setString(1, password);
			ResultSet rs = lg.executeQuery();
			//JOptionPane.showMessageDialog(null, "Execute Query feita com sucesso");
			if (rs != null) {
				//JOptionPane.showMessageDialog(null, "Result set nao e null, pegou algo");
				while (rs.next()) {
					u = new Usuario();
					u.setPassword(rs.getString("password"));
					//JOptionPane.showMessageDialog(null, u.getUsername()+""+u.getPassword());
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

					usuario.setCodigo(rs.getInt("idUser"));
					usuario.setNome_completo(rs.getString("nome_completo"));
					usuario.setUsername(rs.getString("username"));
					usuario.setEmail(rs.getString("email"));
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
		String sql = "update Usuario set idPerfil = ?,nome_completo=?, username = ?,email = ? where idUser = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			
			alterar.setInt(1,usuario.getPerfil().getId() );
			alterar.setString(2, usuario.getNome_completo());
			alterar.setString(3,usuario.getUsername());
			alterar.setString(4, usuario.getEmail());
			alterar.setInt(5, usuario.getCodigo());
			
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

	public void apagarUsuario(Usuario usuario) throws ExceptionDao {
		String sql = "delete from Usuario where idUser=?";
		PreparedStatement apagar = null;
		Connection con = null;
		try {
			
			con = new Conexao().getConnection();
			apagar = con.prepareStatement(sql);
			apagar.setInt(1, usuario.getCodigo());
			apagar.executeUpdate();
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
}