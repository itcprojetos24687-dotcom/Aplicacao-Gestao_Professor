package dao;
import java.sql.*;
import model.Inscricao;
import model.Modulo;
import model.Formando;
import model. Quali_modulo;
import java.util.ArrayList;


public class InscricaoDao {
	Connection con = null;

	public void cadastrarInscricao(Inscricao inscricao) throws ExceptionDao {

	    String sql = "insert into Inscricao( data_inscricao, semestre)" + "values(?,?,?)";
	    PreparedStatement InsertInscricao = null;
	    try {
	        con = new Conexao().getConnection();
	        InsertInscricao = con.prepareStatement(sql);
	        InsertInscricao.setInt(1, inscricao.getData_inscricao());
	        InsertInscricao.setString(2, inscricao.getSemestre());
	        InsertInscricao.execute();

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

	public void atualizarInscricao(Inscricao inscricao) {
	    String sql = "update Inscricao set modulo = ?, data_inscricao = ?, semestre = ? where codigo = ?";
	    PreparedStatement alterarInscricao = null;

	    try {
	        con = new Conexao().getConnection();
	        alterarInscricao = con.prepareStatement(sql);
	        alterarInscricao.setInt(1, inscricao.getData_inscricao());
	        alterarInscricao.setString(2, inscricao.getSemestre());
	        alterarInscricao.setInt(3, inscricao.getCodigo());
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (alterarInscricao != null) {
	                alterarInscricao.close();
	            }
	        } catch (SQLException sq) {
	            sq.printStackTrace();
	        }
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException l) {
	        }
	    }
	}

	public void apagarInscricao(Inscricao inscricao) throws ExceptionDao {

	    String sql = "delete from Inscricao where codigo=?";
	    PreparedStatement apagarInscricao = null;
	    try {
	        con = new Conexao().getConnection();
	        apagarInscricao = con.prepareStatement(sql);
	        apagarInscricao.setInt(1, inscricao.getCodigo());
	        apagarInscricao.executeUpdate();

	    } catch (SQLException e) {
	        throw new ExceptionDao("Erro ao inserir dados :" + e);
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