package dao;
import model.*;
import java.sql.*;
public class Quali_NivelDao {
	public void cadastrarQuali_Nivel(Quali_Nivel qn) throws ExceptionDao{
		String sql = "insert into Quali_Nivel (cod_Quali,cod_Nivel) values (?,?)";
		Connection con = null;
		PreparedStatement stms = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, qn.getQualificacao().getCodigo());
			stms.setInt(2, qn.getNivel().getCodigo());
			stms.execute();
			
		}catch(SQLException s) {
			new ExceptionDao("Erro ao inserir"+s);
		}finally {
			try {
				
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException p) {
				new ExceptionDao("Erro ao fechar statement "+p);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException s) {
				new ExceptionDao("Erro ao fechar conexao");
			}
		}
	
	}
}
