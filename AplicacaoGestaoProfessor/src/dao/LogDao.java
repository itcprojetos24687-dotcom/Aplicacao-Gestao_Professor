package dao;
import model.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class LogDao {
	public void salvar(Logs log) throws ExceptionDao {
		String sql = "insert into Log (id_Usuario,acao,descricao, data) values (?,?,?,?)";
		Connection con = null;
		PreparedStatement stms = null;
		try {
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, log.getUsuario().getCodigo());
			stms.setString(2, log.getAcao());
			stms.setString(3, log.getDescricao());
			stms.setObject(4, log.getData());
			
			stms.execute();
		} catch (SQLException s) {
			throw new ExceptionDao("Erro ao inserir log: " + s);
		} finally {
			try {
				if (stms != null) {
					stms.close();
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
	public ArrayList<Logs> listarlogs() throws ExceptionDao{
		String sql = "select Usuario.username,Perfil.nome,acao, descricao,data from Log "
				+ "join Usuario on id_Usuario = idUser "
				+ "join Perfil on idPerfil = id "
				+ "order by data desc";
		Connection con = null;
		PreparedStatement stms = null;
		ArrayList<Logs> logs = null;
		
		try {
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			ResultSet rs = stms.executeQuery();
			if (rs != null) {
				logs = new ArrayList<Logs>();
				
				while (rs.next()) {
					Logs log = new Logs();
					Usuario u = new Usuario();
					Perfil p = new Perfil();
					u.setUsername(rs.getString("username"));
					
					p.setNome(rs.getString("nome"));
					
					u.setPerfil(p);
					log.setAcao(rs.getString("acao"));
					
					log.setDescricao(rs.getString("descricao"));
					
					
					log.setData((LocalDateTime) rs.getObject("data"));
					
					
					log.setUsuario(u);
					
					logs.add(log);
				}
			}
		}catch(SQLException s) {
			throw new ExceptionDao("Erro ao fazer select de logs: " + s);
		}finally {
			try {
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar statement: "+ s);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException d) {
				throw new ExceptionDao("Erro ao fechar conexao " +d);
			}
		}
		return logs;
	}

	public ArrayList<Logs> listarlogs(String filtro) throws ExceptionDao{
		String sql = "select Usuario.username, Perfil.nome, acao, descricao, data from Log "
				+ "join Usuario on id_Usuario = idUser "
				+ "join Perfil on idPerfil = id "
				+ "where Usuario.username like ? "
				+ "or Perfil.nome like ? "
				+ "or acao like ? "
				+ "or descricao like ? "
				+ "or CAST(data as CHAR) like ? "
				+ "order by data desc";
		Connection con = null;
		PreparedStatement stms = null;
		ArrayList<Logs> logs = null;
		
		try {
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			String termo = "%" + filtro + "%";
			stms.setString(1, termo);
			stms.setString(2, termo);
			stms.setString(3, termo);
			stms.setString(4, termo);
			stms.setString(5, termo);
			
			ResultSet rs = stms.executeQuery();
			if (rs != null) {
				logs = new ArrayList<Logs>();
				
				while (rs.next()) {
					Logs log = new Logs();
					Usuario u = new Usuario();
					Perfil p = new Perfil();
					u.setNome(rs.getString("username"));
					
					p.setNome(rs.getString("nome"));
					
					u.setPerfil(p);
					log.setAcao(rs.getString("acao"));
					log.setDescricao(rs.getString("descricao"));
					log.setData((LocalDateTime) rs.getObject("data"));
					log.setUsuario(u);
					
					logs.add(log);
				}
			}
		}catch(SQLException s) {
			throw new ExceptionDao("Erro ao fazer select de logs: " + s);
		}finally {
			try {
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar statement: "+ s);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException d) {
				throw new ExceptionDao("Erro ao fechar conexao " +d);
			}
		}
		return logs;
	}
}