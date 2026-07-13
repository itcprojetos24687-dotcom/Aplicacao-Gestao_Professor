package dao;
import java.sql.*;
import java.time.LocalDateTime;
import model.Inscricao;
import model.Modulo;
import model.Formando;
import model.Quali_modulo;
import model.Logs;
import model.Seccao;
import model.Usuario;
import java.util.ArrayList;


public class InscricaoDao {
	Connection con = null;

	public void cadastrarInscricao(Inscricao inscricao) throws ExceptionDao {

	    String sql = "insert into Inscricao(data_inscricao, semestre) values(?,?)";
	    PreparedStatement InsertInscricao = null;
	    try {
	        con = new Conexao().getConnection();
	        InsertInscricao = con.prepareStatement(sql);
	        InsertInscricao.setInt(1, inscricao.getData_inscricao());
	        InsertInscricao.setString(2, inscricao.getSemestre());
	        InsertInscricao.execute();

	        // LOG: Cadastro de inscrição (estilo UsuarioDao)
	        Usuario u = Seccao.obterUtilizador();
	        if (u != null) {
	            Logs log = new Logs("INSERT", "Inscrição para o semestre " + inscricao.getSemestre() + " foi cadastrada", u);
	            log.setData(LocalDateTime.now());
	            new LogDao().salvar(log);
	        }

	    } catch (SQLException e) {
	        throw new ExceptionDao("Erro ao inserir dados :" + e);
	    } finally {
	        try {
	            if (InsertInscricao != null) {
	                InsertInscricao.close();
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

	public ArrayList<Inscricao> listarInscricao(String semestre) throws ExceptionDao {

	    String sql = "select * from Inscricao where semestre like '%" + semestre + "%'";
	    PreparedStatement listarInscricao = null;
	    ArrayList<Inscricao> inscricoes = null;

	    try {
	        con = new Conexao().getConnection();
	        listarInscricao = con.prepareStatement(sql);
	        ResultSet rs = listarInscricao.executeQuery();

	        if (rs != null) {
	            inscricoes = new ArrayList();
	            while (rs.next()) {
	                Inscricao inscricao = new Inscricao();

	                inscricao.setCodigo(rs.getInt("codigo"));
	                inscricao.setData_inscricao(rs.getInt("data_inscricao"));
	                inscricao.setSemestre(rs.getString("semestre"));
	                inscricoes.add(inscricao);
	            }
	        }
	    } catch (SQLException ex) {
	        throw new ExceptionDao("Erro ao selecionar dados " + ex);
	    } finally {
	        try {
	            if (listarInscricao != null) {
	                listarInscricao.close();
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
	    return inscricoes;
	}

	public void atualizarInscricao(Inscricao inscricao) throws ExceptionDao {
	    String sql = "update Inscricao set data_inscricao = ?, semestre = ? where codigo = ?";
	    PreparedStatement alterarInscricao = null;

	    try {
	        con = new Conexao().getConnection();
	        alterarInscricao = con.prepareStatement(sql);
	        alterarInscricao.setInt(1, inscricao.getData_inscricao());
	        alterarInscricao.setString(2, inscricao.getSemestre());
	        alterarInscricao.setInt(3, inscricao.getCodigo());
	        alterarInscricao.executeUpdate();

	        // LOG: Atualização de inscrição (estilo UsuarioDao)
	        Usuario u = Seccao.obterUtilizador();
	        if (u != null) {
	            Logs log = new Logs("UPDATE", "Inscrição (ID: " + inscricao.getCodigo() + ") para o semestre " + inscricao.getSemestre() + " foi atualizada", u);
	            log.setData(LocalDateTime.now());
	            new LogDao().salvar(log);
	        }

	    } catch (SQLException e) {
	        throw new ExceptionDao("Erro ao alterar dados: " + e);
	    } finally {
	        try {
	            if (alterarInscricao != null) {
	                alterarInscricao.close();
	            }
	        } catch (SQLException sq) {
	            throw new ExceptionDao("Erro ao fechar o statement: " + sq);
	        }
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException l) {
	            throw new ExceptionDao("Erro ao fechar a conexao: " + l);
	        }
	    }
	}

	public void apagarInscricao(Inscricao inscricao) throws ExceptionDao {

	    String sql = "delete from Inscricao where codigo=?";
	    PreparedStatement apagarInscricao = null;

	    // Buscar dados da inscrição antes de apagar para o log
	    String semestre = "";
	    PreparedStatement buscar = null;
	    try {
	        con = new Conexao().getConnection();
	        buscar = con.prepareStatement("select semestre from Inscricao where codigo = ?");
	        buscar.setInt(1, inscricao.getCodigo());
	        ResultSet rs = buscar.executeQuery();
	        if (rs.next()) {
	            semestre = rs.getString("semestre");
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
	        apagarInscricao = con.prepareStatement(sql);
	        apagarInscricao.setInt(1, inscricao.getCodigo());
	        apagarInscricao.executeUpdate();

	        // LOG: Exclusão de inscrição (estilo UsuarioDao)
	        Usuario u = Seccao.obterUtilizador();
	        if (u != null) {
	            Logs log = new Logs("DELETE", "Inscrição (ID: " + inscricao.getCodigo() + ") para o semestre " + semestre + " foi removida", u);
	            log.setData(LocalDateTime.now());
	            new LogDao().salvar(log);
	        }

	    } catch (SQLException e) {
	        throw new ExceptionDao("Erro ao apagar dados :" + e);
	    } finally {
	        try {
	            if (apagarInscricao != null) {
	                apagarInscricao.close();
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
}