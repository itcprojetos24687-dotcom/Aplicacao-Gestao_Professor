package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Perfil;
import model.Logs;
import model.Seccao;
import model.Usuario;


public class PerfilDao {
	public ArrayList<Perfil> listarPerfil()throws ExceptionDao{
		String sql = "select * from Perfil";
		Connection con = null;
		PreparedStatement stms = null;
		ArrayList<Perfil> perfis = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			ResultSet rs = stms.executeQuery();
			if(rs != null) {
				perfis = new ArrayList<>();
				while(rs.next()) {
					int id = rs.getInt("id");
					String nome =rs.getString("nome");
					Perfil p = new Perfil(id,nome);
					perfis.add(p);
				}
			}
			
		}catch(SQLException sq) {
			new ExceptionDao("Erro ao selecionar Perfil");
		}finally {
			try {
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException s){
				new ExceptionDao("Erro ao fechar o statement");
				
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException a) {
				new ExceptionDao("Erro ao fechar conexao");
			}
		}
		return perfis;
	}
	public Perfil getPerfil(String username) {
		String sql = "select Perfil.id, Perfil.nome from Usuario join Perfil on idPerfil = id where username = ?";
		Connection con = null;
		PreparedStatement stms = null;
		Perfil p = null;
		
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setString(1, username);
			ResultSet rs = stms.executeQuery();
			//JOptionPane.showMessageDialog(null, "Query executada");
			if(rs != null) {
				//JOptionPane.showMessageDialog(null, "Result set nao e nulu");
				while(rs.next()) {
					p = new Perfil();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					
					//JOptionPane.showMessageDialog(null, "Inicializado:"+p.getId()+p.getNome());
					
				}
			}
			
		}catch(SQLException sq) {
			new ExceptionDao("Erro ao selecionar Perfil");
		}finally {
			try {
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException s){
				new ExceptionDao("Erro ao fechar o statement");
				
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException a) {
				new ExceptionDao("Erro ao fechar conexao");
			}
		}
		
		return p;
	}

	public void cadastrarPerfil(Perfil perfil) throws ExceptionDao {
		String sql = "insert into Perfil(nome) values(?)";
		Connection con = null;
		PreparedStatement insertPerfil = null;
		
		try {
			con = new Conexao().getConnection();
			insertPerfil = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insertPerfil.setString(1, perfil.getNome());
			insertPerfil.execute();
			
			ResultSet rs = insertPerfil.getGeneratedKeys();
			if(rs.next()) {
				perfil.setId(rs.getInt(1));
			}

			// LOG: Cadastro de perfil (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Perfil " + perfil.getNome() + " foi cadastrado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		} catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados: " + e);
		} finally {
			try {
				if(insertPerfil != null) {
					insertPerfil.close();
				}
			} catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}

	public void atualizarPerfil(Perfil perfil) throws ExceptionDao {
		String sql = "update Perfil set nome = ? where id = ?";
		Connection con = null;
		PreparedStatement alterarPerfil = null;
		
		try {
			con = new Conexao().getConnection();
			alterarPerfil = con.prepareStatement(sql);
			alterarPerfil.setString(1, perfil.getNome());
			alterarPerfil.setInt(2, perfil.getId());
			alterarPerfil.executeUpdate();

			// LOG: Atualização de perfil (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Perfil " + perfil.getNome() + " (ID: " + perfil.getId() + ") foi atualizado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		} catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados: " + e);
		} finally {
			try {
				if(alterarPerfil != null) {
					alterarPerfil.close();
				}
			} catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}

	public void apagarPerfil(Perfil perfil) throws ExceptionDao {
		String sql = "delete from Perfil where id=?";
		Connection con = null;
		PreparedStatement apagarPerfil = null;

		// Buscar nome do perfil antes de apagar para o log
		String nomePerfil = "";
		PreparedStatement buscar = null;
		try {
			con = new Conexao().getConnection();
			buscar = con.prepareStatement("select nome from Perfil where id = ?");
			buscar.setInt(1, perfil.getId());
			ResultSet rs = buscar.executeQuery();
			if (rs.next()) {
				nomePerfil = rs.getString("nome");
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
			apagarPerfil = con.prepareStatement(sql);
			apagarPerfil.setInt(1, perfil.getId());
			apagarPerfil.executeUpdate();

			// LOG: Exclusão de perfil (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Perfil " + nomePerfil + " (ID: " + perfil.getId() + ") foi removido", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		} catch(SQLException e) {
			throw new ExceptionDao("Erro ao apagar dados: " + e);
		} finally {
			try {
				if(apagarPerfil != null) {
					apagarPerfil.close();
				}
			} catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}

	public Perfil obterPerfilPorId(int id) throws ExceptionDao {
		String sql = "select * from Perfil where id = ?";
		Connection con = null;
		PreparedStatement stms = null;
		Perfil perfil = null;
		
		try {
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, id);
			ResultSet rs = stms.executeQuery();
			
			if (rs.next()) {
				perfil = new Perfil();
				perfil.setId(rs.getInt("id"));
				perfil.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao buscar perfil: " + e);
		} finally {
			try {
				if (stms != null) {
					stms.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDao("Erro ao fechar statement: " + e);
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDao("Erro ao fechar conexao: " + e);
			}
		}
		return perfil;
	}

	public static void main(String[]args)throws ExceptionDao {
		Perfil p = null;
		try {
			p = new PerfilDao().getPerfil("admin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, p.getNome());
	}
}