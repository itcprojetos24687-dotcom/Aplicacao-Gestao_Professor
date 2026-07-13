package dao;
import java.sql.*;
import java.time.LocalDateTime;

import model.Quali_modulo;
import model.Logs;
import model.Seccao;
import model.Usuario;
import java.util.ArrayList;

public class Quali_moduloDao {
	
		public void cadastrarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
			String sql = "insert into Quali_modulo (semestre, ano_curricular) values(?,?)";
			Connection con = null;
			PreparedStatement inserir = null;
			try {
				con = new Conexao().getConnection();
				inserir = con.prepareStatement(sql);
				inserir.setString(1, quali_modulo.getSemestre());
				inserir.setInt(2, quali_modulo.getAno_curricular());
				inserir.execute();

				// LOG: Cadastro de quali_modulo (estilo UsuarioDao)
				Usuario u = Seccao.obterUtilizador();
				if (u != null) {
					Logs log = new Logs("INSERT", "Quali_modulo do semestre " + quali_modulo.getSemestre() + " e ano " + quali_modulo.getAno_curricular() + " foi cadastrado", u);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				}

			}catch(SQLException e) {
				throw new ExceptionDao("Erro ao inserir dados :" + e);
			}finally {
				try {
					if(inserir != null) {
						inserir.close();
					}
				}catch(SQLException ql) {
					throw new ExceptionDao("Erro ao fechar o statement: " + ql);
				}
				try {
					if (con != null) {
						con.close();
					}
				}catch(SQLException sq) {
					throw new ExceptionDao("Erro ao fechar conexao: " + sq);
				}
			}
			
		}
		public ArrayList<Quali_modulo> listarQuali_modulo(String semestre) throws ExceptionDao{
			String sql = "select * from Quali_modulo where semestre = ?";
			Connection con = null;
			PreparedStatement select = null;
			ArrayList<Quali_modulo> quali_modulos = null;
			try {
				con = new Conexao().getConnection();
				select = con.prepareStatement(sql);
				select.setString(1, semestre);
				ResultSet rs = select.executeQuery();
				
				if (rs != null) {
					quali_modulos = new ArrayList<>();
					while (rs.next()) {
						Quali_modulo quali_modulo = new Quali_modulo();
						quali_modulo.setCodigo(rs.getInt("codigo"));
						quali_modulo.setSemestre(rs.getString("semestre"));
						quali_modulo.setAno_curricular(rs.getInt("ano_curricular"));
						quali_modulos.add(quali_modulo);
						
					}
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao selecionar dados: " + sq);
			}finally {
				try{
					if(select != null) {
						select.close();
					}
				}catch(SQLException e) {
					throw new ExceptionDao("Erro ao fechar o statement: " + e);
				}
				try {
					if(con != null) {
						con.close();
					}
				}catch(SQLException s) {
					throw new ExceptionDao("Erro ao fechar a conexao: " + s);
				}
			}
			return quali_modulos;
		}
		public void atualizarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
			String sql = "update Quali_modulo set semestre = ?, ano_curricular = ? where codigo = ?";
			PreparedStatement alterar = null;
			Connection con = null;
			
			try {
				con = new Conexao().getConnection();
				alterar = con.prepareStatement(sql);
				alterar.setString(1, quali_modulo.getSemestre());
				alterar.setInt(2, quali_modulo.getAno_curricular());
				alterar.setInt(3, quali_modulo.getCodigo());
				alterar.executeUpdate();

				// LOG: Atualização de quali_modulo (estilo UsuarioDao)
				Usuario u = Seccao.obterUtilizador();
				if (u != null) {
					Logs log = new Logs("UPDATE", "Quali_modulo (ID: " + quali_modulo.getCodigo() + ") do semestre " + quali_modulo.getSemestre() + " e ano " + quali_modulo.getAno_curricular() + " foi atualizado", u);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				}
				//JOptionPane.showMessageDialog(null,"Alterado com sucesso");
			}catch(SQLException e) {
				//JOptionPane.showMessageDialog(null,"Erro ao alterar");
				throw new ExceptionDao("Erro ao alterar dados: " + e);
			}
			finally {
				try {
					if(alterar != null) {
						alterar.close();
						//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
					}
				}catch(SQLException sq) {
					throw new ExceptionDao("Erro ao fechar o statement: " + sq);
				}
				try {
					if(con != null) {
						con.close();
						//JOptionPane.showMessageDialog(null, "Fechado com sucesso");
					}
				}catch(SQLException l) {
					throw new ExceptionDao("Erro ao fechar a conexao: " + l);
					//JOptionPane.showMessageDialog(null, "Falha de fechado ");
				}
				
			}
			
		}
		public void apagarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao {

			String sql = "delete from Quali_modulo where codigo=?";
			PreparedStatement apagar = null;
			Connection con = null;

			// Buscar dados do quali_modulo antes de apagar para o log
			String semestre = "";
			int anoCurricular = 0;
			PreparedStatement buscar = null;
			try {
				con = new Conexao().getConnection();
				buscar = con.prepareStatement("select semestre, ano_curricular from Quali_modulo where codigo = ?");
				buscar.setInt(1, quali_modulo.getCodigo());
				ResultSet rs = buscar.executeQuery();
				if (rs.next()) {
					semestre = rs.getString("semestre");
					anoCurricular = rs.getInt("ano_curricular");
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
				apagar = con.prepareStatement(sql);
				apagar.setInt(1, quali_modulo.getCodigo());
				apagar.executeUpdate();

				// LOG: Exclusão de quali_modulo (estilo UsuarioDao)
				Usuario u = Seccao.obterUtilizador();
				if (u != null) {
					Logs log = new Logs("DELETE", "Quali_modulo (ID: " + quali_modulo.getCodigo() + ") do semestre " + semestre + " e ano " + anoCurricular + " foi removido", u);
					log.setData(LocalDateTime.now());
					new LogDao().salvar(log);
				}
				//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
				
			}catch(SQLException e) {
				//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
				 throw new ExceptionDao("Erro ao apagar dados :" + e);
			}finally {
				try {
					if(apagar != null) {
						apagar.close();
						//JOptionPane.showMessageDialog(null, "Statemente fechado com sucesso");
					}
				}catch(SQLException sq) {
					throw new ExceptionDao("Erro ao fechar o statement");
				}
				try {
					if (con != null) {
						con.close();
						//JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
					}
				}catch(SQLException f) {
					throw new ExceptionDao("Erro ao fechar a conexao ");
				}
			}
		}
	}