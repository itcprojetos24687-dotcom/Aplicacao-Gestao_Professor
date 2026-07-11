package dao;
import java.sql.*;

import java.util.ArrayList;

import model.Perfil;
import model.Turma;
import model.*;;
public class TurmaDao {
	public void cadastrarTurma(Turma turma) {
		String sql = "insert into(nome,ano_ingresso,turno,id_Diretor_Turma,id_Qualificacao) values(?,?,?,?,?)";
		Connection con = null;
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setString(1, turma.getNome());
			inserir.setInt(2, turma.getAno_ingresso());
			inserir.setString(3, turma.getTurno());
			inserir.setInt(4, turma.getDiretor_turma().getFomador().getCodigo());
			inserir.setInt(5, turma.getQualificacao().getCodigo());
			
			inserir.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(inserir != null) {
					inserir.close();
				}
			}catch(SQLException ql) {
				ql.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			}catch(SQLException sq) {
				new ExceptionDao("Erro ao fechar conexao" +sq);
			}
		}
		
	}

	public void atualizarTurma(Turma turma) throws ExceptionDao{
		String sql = "update Turma set codigo = ?, set nome = ?, set ano_lectivo = ?, set turno = ?, set id_Diretor_Turma = ?, set id_Qualificacao = ? where codigo = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			alterar.setString(1, turma.getNome());
			alterar.setInt(2, turma.getAno_ingresso());
			alterar.setString(3, turma.getTurno());
			alterar.setInt(4, turma.getDiretor_turma().getFomador().getCodigo());
			alterar.setInt(5, turma.getQualificacao().getCodigo());
			alterar.setInt(6, turma.getCodigo());
			alterar.executeUpdate();
			//JOptionPane.showMessageDialog(null,"Alterado com sucesso");
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null,"Erro ao alterar");
			throw new ExceptionDao("Erro ao fechar  a conexao"+ e);
		}
		finally {
			try {
				if(alterar != null) {
					alterar.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ sq);
			}
			try {
				if(con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
				}
			}catch(SQLException l) {
				throw new ExceptionDao("Erro ao fechar  a conexao"+ l);
				//JOptionPane.showMessageDialog(null, "Falha de fechado ");
			}
			
		}
		
	}
	public ArrayList<Turma> listarTurma(String nome) throws ExceptionDao{

		String sql = "select Turna.codigo, Turma.nome,ano_lectivo, turno, Formador.nome as diretor_turma,Qualificacao.titulo as titulo from Turma "
				+ " join Diretor_Turma on cod_Formador = Formador.codigo"
				+ " join Formador on Formador.codigo = cod_Formador "
				+ "join Qualificacao on id_Qualificacao = cod_Quali"
				+ "where Turma.nome like '%"+nome+"%'";
		Connection con = null;
		PreparedStatement listaTurma = null;
		ArrayList<Turma> turmas = null;

		try {

			con = new Conexao().getConnection();
			listaTurma = con.prepareStatement(sql);
			//listarUsuario.setString(1, username);
			ResultSet rs = listaTurma.executeQuery();

			if (rs != null) {
				turmas = new ArrayList<Turma>();

				while (rs.next()) {

					Turma turma = new Turma();
					Diretor_Turma dt = new Diretor_Turma();
					Formador f = new Formador();
					Qualificacao q = new Qualificacao();
					turma.setCodigo(rs.getInt("codigo"));
					turma.setNome(rs.getString("nome"));
					turma.setAno_ingresso(rs.getInt("ano_lectivo"));
					turma.setTurno(rs.getString("turno"));
					f.setNome(rs.getString("diretor_turma"));
					dt.setFormador(f);
					q.setTitulo(rs.getString("titulo"));
					turma.setDiretor_turma(dt);
					turma.setQualificacao(q);
					

					turmas.add(turma);
				}
			}

		} catch (SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados " + ex);

		} finally {

			try {
				if (listaTurma != null) {
					listaTurma.close();
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

		return turmas;
	}
		
	}

