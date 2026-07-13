package dao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Licao;
import model.Logs;
import model.Seccao;
import model.Usuario;

public class LicaoDao {

	public void cadastrarlicao(Licao licao) throws ExceptionDao{

		String sql = "insert into Licao(horas_inicio, horas_fim)" + "values(?,?)";
		Connection con = null;
		PreparedStatement InsertLicao = null;
		try {

			con = new Conexao().getConnection();
			InsertLicao = con.prepareStatement(sql);
			InsertLicao.setInt(1, licao.getHoras_inicio());
			InsertLicao.setInt(2, licao.getHoras_fim());
			InsertLicao.execute();

			// LOG: Cadastro de lição (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Lição das " + licao.getHoras_inicio() + "h às " + licao.getHoras_fim() + "h foi cadastrada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e );
		}finally {
			try {
				if(InsertLicao != null) {
					InsertLicao.close();
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

	public ArrayList<Licao> listarLicao(int horas_inicio) throws ExceptionDao{

		String sql = "select * from Licao where horas_inicio = " + horas_inicio;
		Connection con = null;
		PreparedStatement listarLicao = null;
		ArrayList<Licao> licoes = null;

		try {
			con = new Conexao().getConnection();
			listarLicao = con.prepareStatement(sql);
			ResultSet rs = listarLicao.executeQuery();

			if (rs != null) {
				licoes = new ArrayList();
				while(rs.next()) {
					Licao licao = new Licao();

					licao.setCodigo(rs.getInt("codigo"));
					licao.setHoras_inicio(rs.getInt("horas_inicio"));
					licao.setHoras_fim(rs.getInt("horas_fim"));
					licoes.add(licao);
				}
			}
		}catch(SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(listarLicao != null) {
					listarLicao.close();
				}
			}catch(Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}
			try {
				if(con != null) {
					con.close();
				}
			}
			catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}
		return licoes;
	}

	public void atualizarLicao(Licao licao) throws ExceptionDao{
		String sql = "update Licao set horas_inicio = ?, horas_fim = ? where codigo = ?";
		Connection con = null;
		PreparedStatement alterarLicao = null;

		try {
			con = new Conexao().getConnection();
			alterarLicao = con.prepareStatement(sql);
			alterarLicao.setInt(1, licao.getHoras_inicio());
			alterarLicao.setInt(2, licao.getHoras_fim());
			alterarLicao.setInt(3, licao.getCodigo());
			alterarLicao.executeUpdate();

			// LOG: Atualização de lição (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Lição (ID: " + licao.getCodigo() + ") das " + licao.getHoras_inicio() + "h às " + licao.getHoras_fim() + "h foi atualizada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados :" + e);
		}finally {
			try {
				if(alterarLicao != null) {
					alterarLicao.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}

	public void apagarLicao(Licao licao) throws ExceptionDao {

		String sql = "delete from Licao where codigo=?";
		Connection con = null;
		PreparedStatement apagarLicao = null;

		// Buscar dados da lição antes de apagar para o log
		int horasInicio = 0;
		int horasFim = 0;
		PreparedStatement buscar = null;
		try {
			con = new Conexao().getConnection();
			buscar = con.prepareStatement("select horas_inicio, horas_fim from Licao where codigo = ?");
			buscar.setInt(1, licao.getCodigo());
			ResultSet rs = buscar.executeQuery();
			if (rs.next()) {
				horasInicio = rs.getInt("horas_inicio");
				horasFim = rs.getInt("horas_fim");
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
			apagarLicao = con.prepareStatement(sql);
			apagarLicao.setInt(1, licao.getCodigo());
			apagarLicao.executeUpdate();

			// LOG: Exclusão de lição (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Lição (ID: " + licao.getCodigo() + ") das " + horasInicio + "h às " + horasFim + "h foi removida", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao apagar dados :" + e);
		}finally {
			try {
				if(apagarLicao != null) {
					apagarLicao.close();
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