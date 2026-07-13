package dao;
import model.Matricula;
import model.Logs;
import model.Seccao;
import model.Usuario;
import model.Formando;
import model.Qualificacao;
import model.Nivel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class MatriculaDao {

	Connection con = null;
	public void cadastrarMatricula(Matricula matricula) throws ExceptionDao{
		
		
		String sql = "insert into Matricula(cod_formando,cod_Quali,data)" + " values(?,?,?)";
		PreparedStatement InsertMatricula = null;
		try {
			
			con = new Conexao().getConnection();
			InsertMatricula = con.prepareStatement(sql);
			InsertMatricula.setInt(1, matricula.getFormando().getCodigo());
			InsertMatricula.setInt(2, matricula.getQualificacao().getCodigo());
			InsertMatricula.setString(3, matricula.getData_matricula());
			
			InsertMatricula.execute();

			// LOG: Cadastro de matrícula (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Matrícula para a data " + matricula.getData_matricula() + " foi cadastrada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}
			
		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
			throw new ExceptionDao("Erro ao inserir dados :" + e );
		}finally {
			try {
				if(InsertMatricula != null) {
					InsertMatricula.close();
					//JOptionPane.showMessageDialog(null, "Statemente fechado com sucesso");
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao fechar o statement");
			}
			try {
			if (con != null) {
				con.close();
				//JOptionPane.showMessageDialog(null, "Conexao fechado com Sucesso" );
			}
		}catch(SQLException f) {
			throw new ExceptionDao("Erro ao fechar a conexao ");
		}
	}
}
	public ArrayList<Matricula> listarMatricula(String data) throws ExceptionDao{
		
		String sql = "select Matricula.codigo, nome_formando,apelido_formando, titulo, Nivel.nome as nivel, data from Matricula"
				+ " join Formando on codigo_formando = cod_formando "
				+ "join Qualificacao on Qualificacao.cod_Quali = Matricula.cod_Quali "
				+ "join Quali_Nivel on Qualificacao.cod_Quali= Quali_Nivel.cod_Quali "
				+ "join Nivel on Nivel.codigo= cod_Nivel";
		PreparedStatement listarMatricula = null;
		ArrayList <Matricula> matriculas = null;
		
		try {
		con = new Conexao().getConnection();
			listarMatricula = con.prepareStatement(sql);
				ResultSet rs = listarMatricula.executeQuery();
				
				if (rs != null) {
					matriculas = new ArrayList();
					while(rs.next()) {
					Formando f = new Formando();
					Qualificacao q = new Qualificacao();
					Nivel n = new Nivel();
					int codigo = rs.getInt("codigo");
					f.setNome(rs.getString("nome_formando"));
					f.setApelido(rs.getString("apelido_formando"));
					
					q.setTitulo(rs.getString("titulo"));
					n.setNome(rs.getString("nivel"));
					String data1 = rs.getString("data");
					
					 Matricula matricula = new Matricula(f,q,n,data1); 
					 matricula.setCodigo(codigo);
					matriculas.add(matricula);
				
					
					}
				}
		}catch(SQLException ex) {
			//JOptionPane.showMessageDialog(null, "Erro ao selecionar");
			throw new ExceptionDao("Erro ao selecionar dados " + ex);
		}finally {
			try {
				if(listarMatricula != null) {
					listarMatricula.close();
					//JOptionPane.showMessageDialog(null, "statement fechado com sucesso");
				}
			}catch(Exception es) {
				//JOptionPane.showMessageDialog(null, "Erro ao fechar statement");
				throw new ExceptionDao("Erro ao fechar o statement: " + es);
			}
			try {
				if(con != null) {
					con.close();
					//JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
				}
			}
			catch(SQLException sq) {
				//JOptionPane.showMessageDialog(null, "Erro ao fechar conexao");
				throw new ExceptionDao("Erro ao fechar conexao: " + sql);
			}
		}
		return matriculas;
			
	}
	public void atualizarMatricula(Matricula matricula) throws ExceptionDao {
		String  sql = "update Matricula set cod_formando = ?, cod_Quali = ? ,data_matricula = ? where codigo = ?";
		PreparedStatement alterarMatricula = null;
		
		try {
			con = new Conexao().getConnection();
			alterarMatricula = con.prepareStatement(sql);
			alterarMatricula.setInt(1, matricula.getFormando().getCodigo());
			alterarMatricula.setInt(2, matricula.getQualificacao().getCodigo());
			alterarMatricula.setString(3, matricula.getData_matricula());
			
			alterarMatricula.setInt(4, matricula.getCodigo());
			alterarMatricula.executeUpdate();

			// LOG: Atualização de matrícula (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Matrícula (ID: " + matricula.getCodigo() + ") para a data " + matricula.getData_matricula() + " foi atualizada", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			//JOptionPane.showMessageDialog(null,"Erro ao alterar");
			throw new ExceptionDao("Erro ao alterar dados: " + e);
		}
		finally {
			try {
				if(alterarMatricula != null) {
					alterarMatricula.close();
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
			}
		}
	}

public void apagarMatricula(Matricula matricula) throws ExceptionDao {

	String sql = "delete from Matricula where codigo=?";
	PreparedStatement apagarMatricula = null;

	// Buscar dados da matrícula antes de apagar para o log
	String data = "";
	PreparedStatement buscar = null;
	try {
		con = new Conexao().getConnection();
		buscar = con.prepareStatement("select data from Matricula where codigo = ?");
		buscar.setInt(1, matricula.getCodigo());
		ResultSet rs = buscar.executeQuery();
		if (rs.next()) {
			data = rs.getString("data");
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
		apagarMatricula = con.prepareStatement(sql);
		apagarMatricula.setInt(1, matricula.getCodigo());
		apagarMatricula.executeUpdate();

		// LOG: Exclusão de matrícula (estilo UsuarioDao)
		Usuario u = Seccao.obterUtilizador();
		if (u != null) {
			Logs log = new Logs("DELETE", "Matrícula (ID: " + matricula.getCodigo() + ") da data " + data + " foi removida", u);
			log.setData(LocalDateTime.now());
			new LogDao().salvar(log);
		}
		//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		
	}catch(SQLException e) {
		//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
		 throw new ExceptionDao("Erro ao apagar dados :" + e);
	}finally {
		try {
			if(apagarMatricula != null) {
				apagarMatricula.close();
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