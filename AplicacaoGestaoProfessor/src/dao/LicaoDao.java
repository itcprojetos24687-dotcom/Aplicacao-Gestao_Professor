package dao;
import java.sql.*;
import java.time.LocalDateTime;
import model.Licao;
import model.Modulo;
import model.Formador;
import model.Sala;
import model.Turma;
import model.Logs;
import model.Seccao;
import model.Usuario;
import java.util.ArrayList;

public class LicaoDao {
	Connection con = null;

	public void cadastrarLicao(Licao licao) throws ExceptionDao {
		String sql = "insert into Licao(cod_Modulo, cod_Formador, cod_Sala, cod_Turma, data, hora_inicio, hora_fim) values(?,?,?,?,?,?,?)";
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setInt(1, licao.getModulo().getCodigo());
			inserir.setInt(2, licao.getFormador().getCodigo());
			inserir.setInt(3, licao.getSala().getCodigo());
			inserir.setInt(4, licao.getTurma().getCodigo());
			inserir.setDate(5, Date.valueOf(licao.getData()));
			inserir.setTime(6, Time.valueOf(licao.getHora_inicio() + ":00"));
			inserir.setTime(7, Time.valueOf(licao.getHora_fim() + ":00"));
			inserir.execute();

			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Lição do módulo " + licao.getModulo().getNome() + " foi cadastrada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		} finally {
			try {
				if (inserir != null) inserir.close();
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) con.close();
			} catch (SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}

	public ArrayList<Licao> listarLicao(String texto) throws ExceptionDao {
		String sql = "select l.codigo, l.data, l.hora_inicio, l.hora_fim, " +
				"m.codigo as cod_modulo, m.nome_modulo, " +
				"f.codigo as cod_formador, f.nome as nome_formador, " +
				"s.codigo as cod_sala, s.designacao, " +
				"t.codigo as cod_turma, t.nome as nome_turma " +
				"from Licao l " +
				"join Modulo m on m.codigo = l.cod_Modulo " +
				"join Formador f on f.codigo = l.cod_Formador " +
				"join Sala s on s.codigo = l.cod_Sala " +
				"join Turma t on t.codigo = l.cod_Turma " +
				"where m.nome_modulo like ? or f.nome like ? or t.nome like ?";
		PreparedStatement select = null;
		ArrayList<Licao> licoes = null;

		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			String filtro = "%" + texto + "%";
			select.setString(1, filtro);
			select.setString(2, filtro);
			select.setString(3, filtro);
			ResultSet rs = select.executeQuery();

			if (rs != null) {
				licoes = new ArrayList<>();
				while (rs.next()) {
					Licao licao = new Licao();
					Modulo m = new Modulo();
					Formador f = new Formador();
					Sala s = new Sala();
					Turma t = new Turma();

					licao.setCodigo(rs.getInt("codigo"));
					Date data = rs.getDate("data");
					licao.setData(data != null ? data.toString() : "");
					Time horaInicio = rs.getTime("hora_inicio");
					licao.setHora_inicio(horaInicio != null ? horaInicio.toString().substring(0,5) : "");
					Time horaFim = rs.getTime("hora_fim");
					licao.setHora_fim(horaFim != null ? horaFim.toString().substring(0,5) : "");

					m.setCodigo(rs.getInt("cod_modulo"));
					m.setNome(rs.getString("nome_modulo"));
					licao.setModulo(m);

					f.setCodigo(rs.getInt("cod_formador"));
					f.setNome(rs.getString("nome_formador"));
					licao.setFormador(f);

					s.setCodigo(rs.getInt("cod_sala"));
					s.setDesignacao(rs.getString("designacao"));
					licao.setSala(s);

					t.setCodigo(rs.getInt("cod_turma"));
					t.setNome(rs.getString("nome_turma"));
					licao.setTurma(t);

					licoes.add(licao);
				}
			}
		} catch (SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		} finally {
			try {
				if (select != null) select.close();
			} catch (Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}
			try {
				if (con != null) con.close();
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
			}
		}
		return licoes;
	}

	public void apagarLicao(Licao licao) throws ExceptionDao {
		String sql = "delete from Licao where codigo=?";
		PreparedStatement apagar = null;
		try {
			con = new Conexao().getConnection();
			apagar = con.prepareStatement(sql);
			apagar.setInt(1, licao.getCodigo());
			apagar.executeUpdate();

			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Lição (ID: " + licao.getCodigo() + ") foi removida", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao apagar dados :" + e);
		} finally {
			try {
				if (apagar != null) apagar.close();
			} catch (SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) con.close();
			} catch (SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}
}