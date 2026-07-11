package dao;
import java.sql.*;

import java.util.ArrayList;
import model.*;
public class QualificacaoDao {

	public void cadastrarQualificacao(Qualificacao qc) throws ExceptionDao{

		String sql = "insert into Qualificacao(titulo, cod_Coordenador) values(?,?)";
		Connection con = null;
		PreparedStatement InsertQualificacao = null;
		try {

			con = new Conexao().getConnection();
			InsertQualificacao = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			InsertQualificacao.setString(1, qc.getTitulo());
			InsertQualificacao.setInt(2, qc.getCoordenador().getFormador().getCodigo());
			InsertQualificacao.execute();
			ResultSet rs = InsertQualificacao.getGeneratedKeys();
			if(rs.next()) {
				qc.setCodigo(rs.getInt(1));
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(InsertQualificacao != null) {
					InsertQualificacao.close();
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

	public ArrayList<Qualificacao> comboQualificacao() throws ExceptionDao{

		String sql = "select * from Qualificacao";
		Connection con = null;
		PreparedStatement comboQualificacao = null;
		ArrayList<Qualificacao> qualificacoes = null;

		try {
			con = new Conexao().getConnection();
			comboQualificacao = con.prepareStatement(sql);
			ResultSet rs = comboQualificacao.executeQuery();

			if (rs != null) {
				qualificacoes = new ArrayList();
				while(rs.next()) {
					Qualificacao qc = new Qualificacao();
					qc.setCodigo(rs.getInt("cod_Quali"));
					qc.setTitulo(rs.getString("titulo"));
					qualificacoes.add(qc);
				}
			}
		}catch(SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(comboQualificacao != null) {
					comboQualificacao.close();
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
		return qualificacoes;
	}

	public ArrayList<Qualificacao> listarQualificacao(String titulo) throws ExceptionDao{

		String sql = "select Qualificacao.cod_Quali,titulo,Formador.nome as Coordenador,Nivel.nome as Nivel "
				+ ",Campo.nome as Campo from Qualificacao"
				+ " join Coordenador on cod_Coordenador = cod_Formador "
				+ "join Formador on Coordenador.cod_Formador = Formador.codigo "
				+ "join Quali_Nivel on Quali_Nivel.cod_Quali = Qualificacao.cod_Quali "
				+ "join Nivel on cod_Nivel=Nivel.codigo "
				+ "join Classificacao on cod_Qualificacao = Qualificacao.cod_Quali "
				+ "join Campo on Campo.codigo = cod_Qualificacao where titulo like '%"+titulo+"%'";
		Connection con = null;
		PreparedStatement listarQualificacao = null;
		ArrayList<Qualificacao> qualificacoes = null;

		try {
			con = new Conexao().getConnection();
			listarQualificacao = con.prepareStatement(sql);
			ResultSet rs = listarQualificacao.executeQuery();

			if (rs != null) {
				qualificacoes = new ArrayList();
				while(rs.next()) {
					Qualificacao qc = new Qualificacao();
					Coordenador c = new Coordenador();
					Formador f = new Formador();
					Nivel n = new Nivel();
					Campo campo = new Campo();
					qc.setCodigo(rs.getInt("cod_Quali"));
					qc.setTitulo(rs.getString("titulo"));
					f.setNome(rs.getString("Coordenador"));
					c.setFormador(f);
					qc.setCoordenador(c);
					n.setNome(rs.getNString("Nivel"));
					qc.setNivel(n);
					campo.setNome(rs.getString("Campo"));
					qc.setCampo(campo);
					qualificacoes.add(qc);
				}
			}
		}catch(SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(listarQualificacao != null) {
					listarQualificacao.close();
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
		return qualificacoes;
	}

	public void atualizarQualificacao(Qualificacao qc) throws ExceptionDao{

		String sql = "update Qualificacao set titulo = ? where codigo = ?";
		Connection con = null;
		PreparedStatement alterarQualificacao = null;

		try {
			con = new Conexao().getConnection();
			alterarQualificacao = con.prepareStatement(sql);
			alterarQualificacao.setString(1, qc.getTitulo());
			alterarQualificacao.setInt(2, qc.getCodigo());
			alterarQualificacao.executeUpdate();
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados :" + e);
		}finally {
			try {
				if(alterarQualificacao != null) {
					alterarQualificacao.close();
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

	public void apagarQualificacao(Qualificacao qc) throws ExceptionDao{

		String sql = "delete from Qualificacao where codigo=?";
		Connection con = null;
		PreparedStatement apagarQualificacao = null;
		try {

			con = new Conexao().getConnection();
			apagarQualificacao = con.prepareStatement(sql);
			apagarQualificacao.setInt(1, qc.getCodigo());
			apagarQualificacao.executeUpdate();

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao apagar dados :" + e);
		}finally {
			try {
				if(apagarQualificacao != null) {
					apagarQualificacao.close();
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