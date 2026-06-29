package dao;
import model.Qualificacao;
import java.sql.*;
import java.util.ArrayList;
public class QualificacaoDao {
	public void cadastrarQualificacao(Qualificacao qc)throws ExceptionDao {
		String sql = "insert into Qualificacao(titulo) values(?)";
		Connection con = null;
		PreparedStatement inserir = null;
		try {
			con = new Conexao().getConnection();
			inserir = con.prepareStatement(sql);
			inserir.setString(1, qc.getTitulo());
			inserir.execute();
		}catch(SQLException sq) {
			new ExceptionDao("Erro ao inserir"+ sq);
		}finally {
			try {
				if(inserir != null) {
					inserir.close();
				}
			}catch(SQLException q) {
				new ExceptionDao("Erro ao fechar statement"+q);
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException ex) {
				new ExceptionDao("Erro ao fechar ao conexao"+ ex);
			}
		}
	}
	public ArrayList<Qualificacao> comboQualificacao() throws ExceptionDao{
		String sql = "select * from Qualificacao";
		Connection con = null;
		PreparedStatement pre = null;
		ArrayList<Qualificacao> qcs = null;
		try {
			con = new Conexao().getConnection();
			pre = con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if(rs != null) {
				qcs = new ArrayList();
				while(rs.next()) {
					Qualificacao qc = new Qualificacao();
					qc.setCodigo(rs.getInt("cod_Quali"));
					qc.setTitulo(rs.getString("titulo"));
					qcs.add(qc);
				}
			}
		}catch(SQLException sq) {
			new ExceptionDao("Erro ao selecionar as QUalificacao"+sq);
		}finally {
			try {
				if(pre != null) {
					pre.close();
				}
			}catch(SQLException e) {
				new ExceptionDao("Erro ao fechar statement" + e);
			}try {
				if(con != null) {
					con.close();
				
				}
			}catch(SQLException q) {
				new ExceptionDao("Erro ao fechar conexao"+ q);
			}
			
			
		}
		return qcs;
	}
}
