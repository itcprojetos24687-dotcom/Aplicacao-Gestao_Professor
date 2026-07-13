package dao;

import model.Campo;
import model.Classificacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Logs;
import model.Seccao;
import model.Usuario;

public class ClassificacaoDao {

	Connection con = null;

	public void cadastrarClassificacao(Classificacao classificacao) throws ExceptionDao {
		String sql = "insert into Classificacao(cod_Campo,cod_Qualificacao) values(?,?)";
		PreparedStatement insertClassificacao = null;

		try {
			con = new Conexao().getConnection();
			insertClassificacao = con.prepareStatement(sql);
			insertClassificacao.setInt(1, classificacao.getCampo().getCodigo());
			insertClassificacao.setInt(2, classificacao.getQualificacao().getCodigo());
			insertClassificacao.execute();

			
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Classificação " + classificacao.getCodigo() + " foi cadastrada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		} finally {
			try {
				if (insertClassificacao != null) {
					insertClassificacao.close();
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

	public ArrayList<Classificacao> listarClassificacao(Campo campo) throws ExceptionDao {

		String sql = "select * from Classificacao where campo = ?";
		PreparedStatement listarClassificacao = null;
		ArrayList<Classificacao> listaClassificacao = new ArrayList<>();

		try {
			con = new Conexao().getConnection();
			listarClassificacao = con.prepareStatement(sql);
			listarClassificacao.setInt(1, campo.getCodigo());

			ResultSet rs = listarClassificacao.executeQuery();

			while (rs.next()) {
				Classificacao c = new Classificacao();
				c.setCodigo(rs.getInt("codigo"));

				Campo campoEncontrado = new Campo();
				campoEncontrado.setCodigo(rs.getInt("campo"));
				c.setCampo(campoEncontrado);

				listaClassificacao.add(c);
			}

		} catch (SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados" + ex);

		} finally {
			try {
				if (listarClassificacao != null) {
					listarClassificacao.close();
				}
			} catch (SQLException es) {
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
		return listaClassificacao;
	}

	public void atualizarClassificacao(Classificacao classificacao) throws ExceptionDao {
		String sql = "update Classificacao set campo = ? where codigo = ?";
		PreparedStatement alterarClassificacao = null;

		try {
			con = new Conexao().getConnection();
			alterarClassificacao = con.prepareStatement(sql);
			alterarClassificacao.setInt(1, classificacao.getCampo().getCodigo());
			alterarClassificacao.setInt(2, classificacao.getCodigo());
			alterarClassificacao.executeUpdate();

			// LO
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Classificação " + classificacao.getCodigo() + " foi atualizada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados: " + e);
		} finally {
			try {
				if (alterarClassificacao != null) {
					alterarClassificacao.close();
				}
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException l) {
				throw new ExceptionDao("Erro ao fechar a conexao");
			}
		}
	}

	public void apagarClassificacao(Classificacao classificacao) throws ExceptionDao {
		String sql = "delete from Classificacao where codigo=?";
		PreparedStatement apagarClassificacao = null;

		try {
			con = new Conexao().getConnection();
			apagarClassificacao = con.prepareStatement(sql);
			apagarClassificacao.setInt(1, classificacao.getCodigo());
			apagarClassificacao.executeUpdate();

			// LOG: Exclusão de classificação
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Classificação " + classificacao.getCodigo() + " foi removida", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao apagar dados :" + e);
		} finally {
			try {
				if (apagarClassificacao != null) {
					apagarClassificacao.close();
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