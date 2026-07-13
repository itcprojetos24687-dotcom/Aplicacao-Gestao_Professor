package dao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Campo;
import model.Logs;
import model.Seccao;
import model.Usuario;
import model.Sala;

public class CampoDao {
	public void cadastrarCampo(Campo campo) throws ExceptionDao{
		String sql = "insert into Campo (nome) values(?)";
		Connection con = null;
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setString(1, campo.getNome());
			inserir.execute();

			// LOG: Cadastro de campo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("INSERT", "Campo " + campo.getNome() + " foi cadastrado", u);
				log.setData(LocalDateTime.now());
				new LogDao().salvar(log);
			}

		}catch(SQLException e) {
			throw new ExceptionDao("Erro ao inserir dados: " + e);
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
	public ArrayList<Campo> listarCampo() throws ExceptionDao{
		String sql = "select * from Campo ";
		Connection con = null;
		PreparedStatement select = null;
		ArrayList<Campo> campos = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			ResultSet rs = select.executeQuery();
			
			if(rs != null) {
				campos = new ArrayList<Campo>();
				while (rs.next()) {
					Campo campo = new Campo();
					campo.setCodigo(rs.getInt("codigo"));
					campo.setNome(rs.getString("nome"));
					campos.add(campo);
					
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
		return campos;
	}
	public void atualizarCampo(Campo campo) throws ExceptionDao{
		String sql = "update Campo set nome = ? where codigo = ?";
		PreparedStatement alterar = null;
		Connection con = null;
		
		try {
			con = new Conexao().getConnection();
			alterar = con.prepareStatement(sql);
			alterar.setString(1, campo.getNome());
			alterar.setInt(2, campo.getCodigo());
			alterar.executeUpdate();

			// LOG: Atualização de campo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("UPDATE", "Campo " + campo.getNome() + " (ID: " + campo.getCodigo() + ") foi atualizado", u);
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
	public void apagarCampo(Campo campo) throws ExceptionDao {

		String sql = "delete from Campo where codigo=?";
		PreparedStatement apagar = null;
		Connection con = null;

		// Buscar nome do campo antes de apagar para o log
		String nomeCampo = "";
		PreparedStatement buscar = null;
		try {
			con = new Conexao().getConnection();
			buscar = con.prepareStatement("select nome from Campo where codigo = ?");
			buscar.setInt(1, campo.getCodigo());
			ResultSet rs = buscar.executeQuery();
			if (rs.next()) {
				nomeCampo = rs.getString("nome");
			}
		} catch (SQLException e) {
			// Se não conseguir buscar, continua com nome vazio
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
			apagar.setInt(1, campo.getCodigo());
			apagar.executeUpdate();

			// LOG: Exclusão de campo (estilo UsuarioDao)
			Usuario u = Seccao.obterUtilizador();
			if (u != null) {
				Logs log = new Logs("DELETE", "Campo " + nomeCampo + " (ID: " + campo.getCodigo() + ") foi removido", u);
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

	public Campo obterCampoPorCodigo(int codigo) throws ExceptionDao{
		String sql = "select * from Campo where codigo = ?";
		Connection con = null;
		PreparedStatement select = null;
		Campo campo = null;
		try {
			con = new Conexao().getConnection();
			select = con.prepareStatement(sql);
			select.setInt(1, codigo);
			ResultSet rs = select.executeQuery();
			if (rs.next()) {
				campo = new Campo();
				campo.setCodigo(rs.getInt("codigo"));
				campo.setNome(rs.getString("nome"));
			}
		}catch(SQLException sq) {
			throw new ExceptionDao("Erro ao buscar campo: " + sq);
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
		return campo;
	}
}