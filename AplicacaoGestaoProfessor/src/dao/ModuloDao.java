package dao;
import model.*;
import model.Modulo;
import model.Logs;
import model.Seccao;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModuloDao {
	
	Connection con = null;
	
	public void cadastrarModulo(Modulo modulo) throws ExceptionDao{
		
		String sql = "insert into Modulo(nome_modulo, carga_horaria, id_Quali_Nivel) values(?,?,?)";
		PreparedStatement InsertModulo = null;
		try {
			
			con = new Conexao().getConnection();
			InsertModulo = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			InsertModulo.setString(1, modulo.getNome());
			InsertModulo.setInt(2, modulo.getCarga_horaria());
			InsertModulo.setInt(3, modulo.getQuali_Nivel().getCodigo());
			InsertModulo.execute();

			// Obter a chave gerada
			ResultSet rs = InsertModulo.getGeneratedKeys();
			if(rs.next()) {
				modulo.setCodigo(rs.getInt(1));
			}

			// LOG: Cadastro de módulo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Módulo " + modulo.getNome() + " foi cadastrado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(InsertModulo != null) {
					InsertModulo.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao");
			}
		}
	}
	
	public ArrayList<Modulo> listarModulo(String nome) throws ExceptionDao{
		String sql = "select Modulo.codigo, Modulo.nome_modulo as nome, Modulo.carga_horaria, " +
				"Qualificacao.titulo, Nivel.nome as nivel " +
				"from Modulo " +
				"join Quali_Nivel on Quali_Nivel.codigo = Modulo.id_Quali_Nivel " +
				"join Qualificacao on Qualificacao.cod_Quali = Quali_Nivel.cod_Quali " +
				"join Nivel on Nivel.codigo = Quali_Nivel.cod_Nivel " +
				"where Modulo.nome_modulo like ?";
		
		PreparedStatement listarModulo = null;
		ArrayList<Modulo> modulos = null;
		
		try {
			con = new Conexao().getConnection();
			listarModulo = con.prepareStatement(sql);
			listarModulo.setString(1, "%" + nome + "%");
			ResultSet rs = listarModulo.executeQuery();
			
			if (rs != null) {
				modulos = new ArrayList<>();
				while(rs.next()) {
					Modulo modulo = new Modulo();
					Qualificacao q = new Qualificacao();
					Nivel n = new Nivel();
					Quali_Nivel qn = new Quali_Nivel();
					
					modulo.setCodigo(rs.getInt("codigo"));
					modulo.setNome(rs.getString("nome"));
					modulo.setCarga_horaria(rs.getInt("carga_horaria"));
					
					q.setTitulo(rs.getString("titulo"));
					qn.setQualificacao(q);
					
					n.setNome(rs.getString("nivel"));
					qn.setNivel(n);
					
					modulo.setQuali_Nivel(qn);
					modulos.add(modulo);
				}
			}
		}catch(SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar: " + ex);
			
		}finally {
			try {
				if(listarModulo != null) {
					listarModulo.close();
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
				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
			}
		}
		return modulos;
	}

	public void atualizarModulo(Modulo modulo) throws ExceptionDao {
		String sql = "update Modulo set nome_modulo = ?, carga_horaria = ?, id_Quali_Nivel = ? where codigo = ?";
		PreparedStatement alterarModulo = null;
		
		try {
			con = new Conexao().getConnection();
			alterarModulo = con.prepareStatement(sql);
			alterarModulo.setString(1, modulo.getNome());
			alterarModulo.setInt(2, modulo.getCarga_horaria());
			alterarModulo.setInt(3, modulo.getQuali_Nivel().getCodigo());
			alterarModulo.setInt(4, modulo.getCodigo());
			alterarModulo.executeUpdate();

			// LOG: Atualização de módulo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Módulo " + modulo.getNome() + " (ID: " + modulo.getCodigo() + ") foi atualizado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados: " + e);
		}
		finally {
			try {
				if(alterarModulo != null) {
					alterarModulo.close();
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
	
	public void apagarModulo(Modulo modulo) throws ExceptionDao {
		String sql = "delete from Modulo where codigo=?";
		PreparedStatement apagarModulo = null;

		// Buscar nome do módulo antes de apagar para o log
		String nomeModulo = "";
		PreparedStatement buscar = null;
		try {
			con = new Conexao().getConnection();
			buscar = con.prepareStatement("select nome_modulo from Modulo where codigo = ?");
			buscar.setInt(1, modulo.getCodigo());
			ResultSet rs = buscar.executeQuery();
			if (rs.next()) {
				nomeModulo = rs.getString("nome_modulo");
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
			apagarModulo = con.prepareStatement(sql);
			apagarModulo.setInt(1, modulo.getCodigo());
			apagarModulo.executeUpdate();

			// LOG: Exclusão de módulo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Módulo " + nomeModulo + " (ID: " + modulo.getCodigo() + ") foi removido", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao apagar dados :" + e);
		}finally {
			try {
				if(apagarModulo != null) {
					apagarModulo.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao");
			}
		}
	}
}
