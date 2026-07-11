package dao;
import model.*;

import java.sql.*;

public class LogDao {

	public void salvar(Logs log) throws ExceptionDao {
		String sql = "insert into Log (id_Usuario,acao,descricao) values (?,?,?)";
		Connection con = null;
		PreparedStatement stms = null;
		try {
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, log.getUsuario().getCodigo());
			stms.setString(2, log.getAcao());
			stms.setString(3, log.getDescricao());
			stms.execute();

		} catch (SQLException s) {
			throw new ExceptionDao("Erro ao inserir log: " + s);
		} finally {
			try {
				if (stms != null) {
					stms.close();
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