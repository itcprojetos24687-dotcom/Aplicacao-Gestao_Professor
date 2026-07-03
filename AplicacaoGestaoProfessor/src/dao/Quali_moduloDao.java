package dao;
import java.sql.*;

import model. Quali_modulo;
import java.util.ArrayList;

public class Quali_moduloDao {
	
		public void  cadastrarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
			String sql = "insert into Quali_modulo (semestre, ano_curricular) values(?,?)";
			Connection con = null;
			PreparedStatement inserir = null;
			try {
				con = new Conexao().getConnection();
				inserir = con.prepareStatement(sql);
				inserir.setString(1, quali_modulo.getSemestre());
				inserir.setInt(2,quali_modulo.getAno_curricular());
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
		public ArrayList<Quali_modulo> listarQuali_modulo(String semestre) throws ExceptionDao{
			String sql = "select * from Quali_modulo where semestre=?";
			Connection con = null;
			PreparedStatement select = null;
			ArrayList<Quali_modulo> quali_modulos = null;
			try {
				con = new Conexao().getConnection();
				select = con.prepareStatement(sql);
				ResultSet rs = select.executeQuery();
				
			
				
				if(rs != null) {
					while (rs.next()) {
						Quali_modulo quali_modulo = new Quali_modulo();
						quali_modulo.setSemestre(rs.getString("semestre"));
						quali_modulo.setAno_curricular(rs.getInt("ano_curricular"));
						quali_modulos.add(quali_modulo);
						
					}
				}
			}catch(SQLException sq) {
				throw new ExceptionDao("Erro ao inserir dados");
			}finally {
				try{
					if(select != null) {
						select.close();
					}
				}catch(SQLException e) {
					throw new ExceptionDao("Erro ao fechar o statemnt"+e);
				}
				try {
					if(con != null) {
						con.close();
					}
				}catch(SQLException s) {
					throw new ExceptionDao("Erro ao fechar a conexao"+s);
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
		public void apagarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao {

			String sql = "delete from Sala where codigo=?";
			PreparedStatement apagar = null;
			Connection con = null;
			try {
				
				con = new Conexao().getConnection();
				apagar = con.prepareStatement(sql);
				apagar.setInt(1, quali_modulo.getCodigo());
				apagar.executeUpdate();
				//JOptionPane.showMessageDialog(null, "Apagado com sucesso");
				
			}catch(SQLException e) {
				//JOptionPane.showMessageDialog(null, "Erro ao inserir dado");
				 throw new ExceptionDao("Erro ao inserir dados :" + e);
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
