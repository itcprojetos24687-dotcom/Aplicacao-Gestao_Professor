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
//	public void cadastrarCoordenador(Coordenador coordenador) throws ExceptionDao {
//		String sql = "insert into Coordenador(formador) values(?)";
//		PreparedStatement insertCoordenador = null;
//
//		try {
//			con = new Conexao().getConnection();
//			insertCoordenador = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			insertCoordenador.setInt(1, coordenador.getFormador().getCodigo());
//			insertCoordenador.execute();
//
//			ResultSet rs = insertCoordenador.getGeneratedKeys();
//			if (rs != null && rs.next()) {
//				coordenador.setCodigo(rs.getInt(1));
//			}
//
//		} catch (SQLException e) {
//			throw new ExceptionDao("Erro ao inserir dados :" + e);
//		} finally {
//			try {
//				if (insertCoordenador != null) {
//					insertCoordenador.close();
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
//
//	public ArrayList<Coordenador> listarCoordenador(Formador formador) throws ExceptionDao {
//
//		String sql = "select * from Coordenador where formador = ?";
//		PreparedStatement listarCoordenador = null;
//		ArrayList<Coordenador> listaCoordenador = new ArrayList<>();
//
//		try {
//			con = new Conexao().getConnection();
//			listarCoordenador = con.prepareStatement(sql);
//			listarCoordenador.setInt(1, formador.getCodigo());
//
//			ResultSet rs = listarCoordenador.executeQuery();
//
//			while (rs.next()) {
//				Coordenador c = new Coordenador();
//				c.setCodigo(rs.getInt("codigo"));
//
//				Formador formadorEncontrado = new Formador();
//				formadorEncontrado.setCodigo(rs.getInt("formador"));
//				c.setFormador(formadorEncontrado);
//
//				listaCoordenador.add(c);
//			}
//
//		} catch (SQLException ex) {
//			throw new ExceptionDao("Erro ao selecionar dados" + ex);
//
//		} finally {
//			try {
//				if (listarCoordenador != null) {
//					listarCoordenador.close();
//				}
//			} catch (SQLException es) {
//				throw new ExceptionDao("Erro ao fechar o statement: " + es);
//			}
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (SQLException sq) {
//				throw new ExceptionDao("Erro ao fechar conexao: " + sq);
//			}
//		}
//		return listaCoordenador;
//	}
//
//	public void atualizarCoordenador(Coordenador coordenador) throws ExceptionDao {
//		String sql = "update Coordenador set formador = ? where codigo = ?";
//		PreparedStatement alterarCoordenador = null;
//
//		try {
//			con = new Conexao().getConnection();
//			alterarCoordenador = con.prepareStatement(sql);
//			alterarCoordenador.setInt(1, coordenador.getFormador().getCodigo());
//			alterarCoordenador.setInt(2, coordenador.getCodigo());
//			alterarCoordenador.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new ExceptionDao("Erro ao alterar dados: " + e);
//		} finally {
//			try {
//				if (alterarCoordenador != null) {
//					alterarCoordenador.close();
//				}
//			} catch (SQLException sq) {
//				throw new ExceptionDao("Erro ao fechar o statement");
//			}
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (SQLException l) {
//				throw new ExceptionDao("Erro ao fechar a conexao");
//			}
//		}
//	}
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