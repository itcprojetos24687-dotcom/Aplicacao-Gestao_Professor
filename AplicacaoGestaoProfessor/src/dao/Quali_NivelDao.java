package dao;
import model.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Quali_NivelDao {
	public void cadastrarQuali_Nivel(Quali_Nivel qn) throws ExceptionDao{
		String sql = "insert into Quali_Nivel (cod_Quali,cod_Nivel) values (?,?)";
		Connection con = null;
		PreparedStatement stms = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, qn.getQualificacao().getCodigo());
			stms.setInt(2, qn.getNivel().getCodigo());
			stms.execute();

			// LOG: Cadastro de Quali_Nivel (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Quali_Nivel: Qualificação " + qn.getQualificacao().getTitulo() + 
									" associada ao Nível " + qn.getNivel().getNome() + " foi cadastrada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		}catch(SQLException s) {
			throw new ExceptionDao("Erro ao inserir: " + s);
		}finally {
			try {
				
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException p) {
				throw new ExceptionDao("Erro ao fechar statement: " + p);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar conexao: " + s);
			}
		}
	
	}

	public void atualizarQuali_Nivel(Quali_Nivel qn) throws ExceptionDao{
		String sql = "update Quali_Nivel set cod_Quali = ?, cod_Nivel = ? where codigo = ?";
		Connection con = null;
		PreparedStatement stms = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, qn.getQualificacao().getCodigo());
			stms.setInt(2, qn.getNivel().getCodigo());
			stms.setInt(3, qn.getCodigo());
			stms.executeUpdate();

			// LOG: Atualização de Quali_Nivel (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Quali_Nivel (ID: " + qn.getCodigo() + 
									") - Qualificação " + qn.getQualificacao().getTitulo() + 
									" associada ao Nível " + qn.getNivel().getNome() + " foi atualizada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		}catch(SQLException s) {
			throw new ExceptionDao("Erro ao atualizar: " + s);
		}finally {
			try {
				
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException p) {
				throw new ExceptionDao("Erro ao fechar statement: " + p);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar conexao: " + s);
			}
		}
	
	}

	public void apagarQuali_Nivel(Quali_Nivel qn) throws ExceptionDao{
		String sql = "delete from Quali_Nivel where codigo = ?";
		Connection con = null;
		PreparedStatement stms = null;

		// Buscar dados do Quali_Nivel antes de apagar para o log
		String tituloQualificacao = "";
		String nomeNivel = "";
		PreparedStatement buscar = null;
		try {
			con = new Conexao().getConnection();
			buscar = con.prepareStatement("select q.titulo, n.nome from Quali_Nivel qn " +
										  "join Qualificacao q on qn.cod_Quali = q.cod_Quali " +
										  "join Nivel n on qn.cod_Nivel = n.codigo " +
										  "where qn.codigo = ?");
			buscar.setInt(1, qn.getCodigo());
			ResultSet rs = buscar.executeQuery();
			if (rs.next()) {
				tituloQualificacao = rs.getString("titulo");
				nomeNivel = rs.getString("nome");
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

		try{
			if (con == null || con.isClosed()) {
				con = new Conexao().getConnection();
			}
			stms = con.prepareStatement(sql);
			stms.setInt(1, qn.getCodigo());
			stms.executeUpdate();

			// LOG: Exclusão de Quali_Nivel (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Quali_Nivel (ID: " + qn.getCodigo() + 
									") - Qualificação " + tituloQualificacao + 
									" associada ao Nível " + nomeNivel + " foi removida", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		}catch(SQLException s) {
			throw new ExceptionDao("Erro ao apagar: " + s);
		}finally {
			try {
				
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException p) {
				throw new ExceptionDao("Erro ao fechar statement: " + p);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar conexao: " + s);
			}
		}
	
	}

	public Quali_Nivel obterQuali_NivelPorCodigo(int codigo) throws ExceptionDao{
		String sql = "select qn.codigo, qn.cod_Quali, qn.cod_Nivel, q.titulo, n.nome " +
					 "from Quali_Nivel qn " +
					 "join Qualificacao q on qn.cod_Quali = q.cod_Quali " +
					 "join Nivel n on qn.cod_Nivel = n.codigo " +
					 "where qn.codigo = ?";
		Connection con = null;
		PreparedStatement stms = null;
		Quali_Nivel qn = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, codigo);
			ResultSet rs = stms.executeQuery();
			
			if (rs.next()) {
				qn = new Quali_Nivel();
				qn.setCodigo(rs.getInt("codigo"));
				
				Qualificacao qualificacao = new Qualificacao();
				qualificacao.setCodigo(rs.getInt("cod_Quali"));
				qualificacao.setTitulo(rs.getString("titulo"));
				qn.setQualificacao(qualificacao);
				
				Nivel nivel = new Nivel();
				nivel.setCodigo(rs.getInt("cod_Nivel"));
				nivel.setNome(rs.getString("nome"));
				qn.setNivel(nivel);
			}
			
		}catch(SQLException s) {
			throw new ExceptionDao("Erro ao buscar: " + s);
		}finally {
			try {
				
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException p) {
				throw new ExceptionDao("Erro ao fechar statement: " + p);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException s) {
				throw new ExceptionDao("Erro ao fechar conexao: " + s);
			}
		}
		return qn;
	}
}