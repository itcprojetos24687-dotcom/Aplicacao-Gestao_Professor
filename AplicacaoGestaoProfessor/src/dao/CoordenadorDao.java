package dao;

import model.Coordenador;
import model.Formador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CoordenadorDao {

//	Connection con = null;
//
	public void cadastrarCoordenador(Coordenador coordenador) throws ExceptionDao {
		String sql = "insert into Coordenador values(?)";
		PreparedStatement insertCoordenador = null;
		Connection con =  null;

		try {
			con = new Conexao().getConnection();
			insertCoordenador = con.prepareStatement(sql);
			insertCoordenador.setInt(1, coordenador.getFormador().getCodigo());
			insertCoordenador.execute();

			
			

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados :" + e);
		} finally {
			try {
				if (insertCoordenador != null) {
					insertCoordenador.close();
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

	public ArrayList<Coordenador> listarCoordenador() throws ExceptionDao {

		String sql = "select cod_Formador,Formador.nome from Coordenador "
				+ "join Formador on cod_Formador = codigo";
		Connection con = null;
		PreparedStatement listarCoordenador = null;
		ArrayList<Coordenador> listaCoordenador = null;

		try {
			con = new Conexao().getConnection();
			listarCoordenador = con.prepareStatement(sql);
			ResultSet rs = listarCoordenador.executeQuery();
			if(rs != null) {
				listaCoordenador = new ArrayList<Coordenador>();
				while (rs.next()) {
					Formador f = new Formador();
					Coordenador c = new Coordenador();
					f.setCodigo(rs.getInt("cod_Formador"));
					f.setNome(rs.getString("nome"));
					c.setFormador(f);
					listaCoordenador.add(c);
				}
			}

		} catch (SQLException ex) {
			throw new ExceptionDao("Erro ao selecionar dados" + ex);

		} finally {
			try {
				if (listarCoordenador != null) {
					listarCoordenador.close();
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
		return listaCoordenador;
	}

	public void atualizarCoordenador(Coordenador coordenador) throws ExceptionDao {
		String sql = "update Coordenador set cod_formador = ? where codigo = ?";
		Connection con = null;
		PreparedStatement alterarCoordenador = null;
		
		try {
			con = new Conexao().getConnection();
			alterarCoordenador = con.prepareStatement(sql);
			alterarCoordenador.setInt(1, coordenador.getFormador().getCodigo());
			alterarCoordenador.setInt(2, coordenador.getFormador().getCodigo());
			alterarCoordenador.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar dados: " + e);
		} finally {
			try {
				if (alterarCoordenador != null) {
					alterarCoordenador.close();
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
//
//	public void apagarCoordenador(Coordenador coordenador) throws ExceptionDao {
//		String sql = "delete from Coordenador where codigo=?";
//		PreparedStatement apagarCoordenador = null;
//
//		try {
//			con = new Conexao().getConnection();
//			apagarCoordenador = con.prepareStatement(sql);
//			apagarCoordenador.setInt(1, coordenador.getCodigo());
//			apagarCoordenador.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new ExceptionDao("Erro ao apagar dados :" + e);
//		} finally {
//			try {
//				if (apagarCoordenador != null) {
//					apagarCoordenador.close();
//				}
//			} catch (SQLException sq) {
//				throw new ExceptionDao("Erro ao fechar o statement");
//			}
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (SQLException f) {
//				throw new ExceptionDao("Erro ao fechar a conexao ");
//			}
//		}
//	}
}