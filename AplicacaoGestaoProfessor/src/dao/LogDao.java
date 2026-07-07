package dao;
import model.*;

import java.sql.*;
import java.util.ArrayList;




public class LogDao {
	public void salvar(Logs log) throws ExceptionDao
	
	{
		String sql = "inserto into Log (id_Usuario,acao,descricao) values (?,?,?)";
		Connection con = null;
		PreparedStatement stms = null;
		try {
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, log.getUsuario().getCodigo());
			stms.setString(2, log.getAcao());
			stms.setString(3, log.getDescricao());
			stms.execute();
			
		}catch(SQLException s) {
			new ExceptionDao("Erro ao inserir log" +s);
		}finally {
			try {
				if(stms != null) {
					stms.close();
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
}
