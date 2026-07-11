package dao;

import java.sql.*;


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
			JOptionPane.showMessageDialog(null, "Conexao estabelecida");
			lg = con.prepareStatement(sql);
			lg.setString(1, username);
			lg.setString(2, password);
			ResultSet rs = lg.executeQuery();
			//JOptionPane.showMessageDialog(null, "Execute Query feita com sucesso");
			if (rs != null) {
				//JOptionPane.showMessageDialog(null, "Result set nao e null, pegou algo");
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
					//JOptionPane.showMessageDialog(null, u);
					
					
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
			Logs log = new Logs("INSERT","Operador"+usuario.getNome()+"cadastrado",u);
			log.salvar(log);
			

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
		
		String sql = "select idUser,Usuario.nome,username,apelido,Perfil.nome from Usuario "
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
					usuario.setNome(rs.getString("nome"));
					usuario.setUsername(rs.getString("username"));
					//usuario.setIdPerfil(rs.getInt("idPerfil"));
					p.setNome(rs.getString("Perfil.nome"));
					usuario.setPerfil(p);
					usuario.setApelido(rs.getString("apelido"));
					//usuario.setPrimeiroAcesso(rs.getBoolean("primeiroAcesso"));
					

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
//	public void alterarPrimeiroAcesso(Usuario u) throws ExceptionDao{
//		String sql = "update Usuario set primeiroAcesso = 0 where idUser = ?";
//		Connection con = null;
//		PreparedStatement stms = null;
//		try {
//			con = new Conexao().getConnection();
//			stms = con.prepareStatement(sql);
//			stms.setBoolean(1, u.isPrimeiroAcesso());
//			stms.setInt(2, u.getCodigo());
//			}catch(SQLException s) {
//				new ExceptionDao("Erro ao alterar acesso"+ s);
//		}finally {
//			try {
//				if(stms != null) {
//					stms.close();
//					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
//				}
//			}catch(SQLException sq) {
//				throw new ExceptionDao("Erro ao fechar  a conexao"+ sq);
//			}
//			try {
//				if(con != null) {
//					con.close();
//					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
//				}
//			}catch(SQLException l) {
//				throw new ExceptionDao("Erro ao fechar  a conexao"+ l);
//				//JOptionPane.showMessageDialog(null, "Falha de fechado ");
//			}
//			
//		}
//	}
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
			//JOptionPane.showMessageDialog(null, "Execute Query feita com sucesso");
			if (rs != null) {
				//JOptionPane.showMessageDialog(null, "Result set nao e null, pegou algo");
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
			
		}catch(SQLException sq) {
			new ExceptionDao("Erro ao atualizar senha");
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
					//usuario.setIdPerfil(rs.getInt("idPerfil"));
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
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}

		return usuario;
	}

	public void atualizarUsuario(Usuario usuario) throws ExceptionDao {
		String sql = "update Usuario set idPerfil = ? where idUser = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			
			alterar.setInt(1,usuario.getPerfil().getId());
			alterar.setInt(2, usuario.getCodigo());
			
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