package dao;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Turma;
import model.*;

public class TurmaDao {

	public void cadastrarTurma(Turma turma) throws ExceptionDao {
		String sql = "insert into Turma(nome,ano_lectivo,turno,id_Diretor_Turma,id_Quali_Nivel) values(?,?,?,?,?)";
		Connection con = null;
		PreparedStatement inserir = null;
		JOptionPane.showMessageDialog(null, turma.getQuali_nivel().getCodigo());
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setString(1, turma.getNome());
			
			inserir.setInt(2, turma.getAno_ingresso());
			
			inserir.setString(3, turma.getTurno());
			
			inserir.setInt(4, turma.getDiretor_turma().getFomador().getCodigo());
			
			inserir.setInt(5, turma.getQuali_nivel().getCodigo());
			
			inserir.execute();

			Usuario u = Seccao.obterUtilizador();
			Logs log = new Logs("INSERT", "Turma: " + turma.getNome() + " cadastrada", u);
			JOptionPane.showMessageDialog(null, log);
			new LogDao().salvar(log);
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		}finally {
			try {
				if(inserir != null) {
					inserir.close();
				}
			}catch(SQLException ql) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao" + sq);
			}
		}
	}

	public void atualizarTurma(Turma turma) throws ExceptionDao {
		String sql = "update Turma set nome = ?, ano_lectivo = ?, turno = ?, id_Diretor_Turma = ?,codigo_Quali_Nivel = ? where codigo = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			alterar.setString(1, turma.getNome());
			alterar.setInt(2, turma.getAno_ingresso());
			alterar.setString(3, turma.getTurno());
			alterar.setInt(4, turma.getDiretor_turma().getFomador().getCodigo());
			alterar.setInt(5, turma.getQuali_nivel().getCodigo());
			alterar.setInt(6, turma.getCodigo());
			alterar.executeUpdate();
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados" + e);
		}finally {
			try {
				if(alterar != null) {
					alterar.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement" + sq);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar a conexao" + l);
			}
		}
	}

	public ArrayList<Turma> listarTurma(String nome) throws ExceptionDao {
		String sql = "select Turma.codigo, Turma.nome, ano_lectivo, turno, Formador.nome as diretor_turma, Qualificacao.titulo as titulo, Nivel.nome as nivel from Turma "
				+ "join Diretor_Turma on cod_Formador = id_Diretor_Turma "
				+ "join Formador on Formador.codigo = cod_Formador "
				+ "join Quali_Nivel on id_Quali_Nivel = codigo_Quali_Nivel "
				+ "join Nivel on cod_Nivel = Nivel.codigo "
				+ "join Qualificacao on Qualificacao.cod_Quali = Quali_Nivel.cod_Quali "
				+ "where Turma.nome like '%" + nome + "%'";
		Connection con = null;
		PreparedStatement listaTurma = null;
		ArrayList<Turma> turmas = null;
		try {
			con = new Conexao().getConnection();
			listaTurma = con.prepareStatement(sql);
			ResultSet rs = listaTurma.executeQuery();
			if (rs != null) {
				turmas = new ArrayList<Turma>();
				while (rs.next()) {
					Turma turma = new Turma();
					Diretor_Turma dt = new Diretor_Turma();
					Formador f = new Formador();
					Qualificacao q = new Qualificacao();
					Nivel n = new Nivel();
					turma.setCodigo(rs.getInt("codigo"));
					turma.setNome(rs.getString("nome"));
					turma.setAno_ingresso(rs.getInt("ano_lectivo"));
					turma.setTurno(rs.getString("turno"));
					
					f.setNome(rs.getString("diretor_turma"));
					dt.setFormador(f);
					
					q.setTitulo(rs.getString("titulo"));
					n.setNome(rs.getString("nivel"));
					
					Quali_Nivel qn = new Quali_Nivel(n,q);
					
					
					
					turma.setDiretor_turma(dt);
					turma.setQuali_nivel(qn);
					turmas.add(turma);
				}
			}
		}catch(SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(listaTurma != null) {
					listaTurma.close();
				}
			}catch(Exception es) {
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}
		return turmas;
	}

	



	public void apagarTurma(int codigo) throws ExceptionDao {
		String sql = "delete from Turma where codigo=?";
		Connection con = null;
		PreparedStatement apagar = null;
		try {
			con = new Conexao().getConnection();
			apagar = con.prepareStatement(sql);
			apagar.setInt(1, codigo);
			apagar.executeUpdate();
		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao apagar dados :" + e);
		}finally {
			try {
				if(apagar != null) {
					apagar.close();
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException f) {
				throw new ExceptionDao("Erro ao fechar a conexao ");
			}
		}
	}
}

