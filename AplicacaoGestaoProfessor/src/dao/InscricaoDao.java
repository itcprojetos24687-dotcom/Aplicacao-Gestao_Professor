package dao;
import java.sql.*;
import java.time.LocalDateTime;
import model.Inscricao;
import model.Modulo;
import model.Formando;
import model.Logs;
import model.Seccao;
import model.Usuario;
import java.util.ArrayList;

public class InscricaoDao {
	Connection con = null;

	public void cadastrarInscricao(Inscricao inscricao) throws ExceptionDao {

	    String sql = "insert into Inscricao(codigo_formando, codigo_modulo, semestre, data_inscricao) values(?,?,?,?)";
	    PreparedStatement InsertInscricao = null;
	    try {
	        con = new Conexao().getConnection();
	        InsertInscricao = con.prepareStatement(sql);
	        InsertInscricao.setInt(1, inscricao.getFormando().getCodigo());
	        InsertInscricao.setInt(2, inscricao.getModulo().getCodigo());
	        InsertInscricao.setString(3, inscricao.getSemestre());
	        InsertInscricao.setDate(4, Date.valueOf(inscricao.getData_inscricao()));
	        InsertInscricao.execute();

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

	    String sql = "select i.codigo_inscricao, i.codigo_formando, i.codigo_modulo, i.semestre, i.data_inscricao, " +
	    		"f.nome_formando, f.apelido_formando, m.nome_modulo " +
	    		"from Inscricao i " +
	    		"join Formando f on f.codigo_formando = i.codigo_formando " +
	    		"join Modulo m on m.codigo = i.codigo_modulo " +
	    		"where i.semestre like ?";
	    PreparedStatement listarInscricao = null;
	    ArrayList<Inscricao> inscricoes = null;

	    try {
	        con = new Conexao().getConnection();
	        listarInscricao = con.prepareStatement(sql);
	        listarInscricao.setString(1, "%" + semestre + "%");
	        ResultSet rs = listarInscricao.executeQuery();

	        if (rs != null) {
	            inscricoes = new ArrayList<>();
	            while (rs.next()) {
	                Inscricao inscricao = new Inscricao();
	                Formando formando = new Formando();
	                Modulo modulo = new Modulo();

	                inscricao.setCodigo(rs.getInt("codigo_inscricao"));
	                inscricao.setSemestre(rs.getString("semestre"));
	                Date data = rs.getDate("data_inscricao");
	                inscricao.setData_inscricao(data != null ? data.toString() : "");

	                formando.setCodigo(rs.getInt("codigo_formando"));
	                formando.setNome(rs.getString("nome_formando"));
	                formando.setApelido(rs.getString("apelido_formando"));
	                inscricao.setFormando(formando);

	                modulo.setCodigo(rs.getInt("codigo_modulo"));
	                modulo.setNome(rs.getString("nome_modulo"));
	                inscricao.setModulo(modulo);

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
	            throw new ExceptionDao("Erro ao fechar conexao: " + sq);
	        }
	    }
	    return inscricoes;
	}

	public void atualizarInscricao(Inscricao inscricao) throws ExceptionDao {
	    String sql = "update Inscricao set codigo_formando = ?, codigo_modulo = ?, semestre = ?, data_inscricao = ? where codigo_inscricao = ?";
	    PreparedStatement alterarInscricao = null;

	    try {
	        con = new Conexao().getConnection();
	        alterarInscricao = con.prepareStatement(sql);
	        alterarInscricao.setInt(1, inscricao.getFormando().getCodigo());
	        alterarInscricao.setInt(2, inscricao.getModulo().getCodigo());
	        alterarInscricao.setString(3, inscricao.getSemestre());
	        alterarInscricao.setDate(4, Date.valueOf(inscricao.getData_inscricao()));
	        alterarInscricao.setInt(5, inscricao.getCodigo());
	        alterarInscricao.executeUpdate();

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

	    String sql = "delete from Inscricao where codigo_inscricao=?";
	    PreparedStatement apagarInscricao = null;

	    String semestre = "";
	    PreparedStatement buscar = null;
	    try {
	        con = new Conexao().getConnection();
	        buscar = con.prepareStatement("select semestre from Inscricao where codigo_inscricao = ?");
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