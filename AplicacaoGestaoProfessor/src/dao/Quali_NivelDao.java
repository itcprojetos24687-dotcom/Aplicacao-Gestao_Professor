package dao;
import model.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class Quali_NivelDao {
	public Quali_Nivel cadastrarQuali_Nivel(Quali_Nivel qn) throws ExceptionDao{
		String sql = "insert into Quali_Nivel (cod_Quali,cod_Nivel) values (?,?)";
		Connection con = null;
		PreparedStatement stms = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stms.setInt(1, qn.getQualificacao().getCodigo());
			stms.setInt(2, qn.getNivel().getCodigo());
			stms.execute();
			ResultSet rs = stms.getGeneratedKeys();
			if(rs.next()) {
				qn.setCodigo(rs.getInt(1));
				JOptionPane.showMessageDialog(null, qn.getCodigo());
			}
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
	return qn;
	}

	public ArrayList<Nivel> getQualificacao_Nivel(Qualificacao q) throws ExceptionDao{
		String sql = "select Nivel.codigo,Nivel.nome from Quali_Nivel "
				+ " join Qualificacao on Quali_Nivel.cod_Quali = Qualificacao.cod_Quali "
				+ "join Nivel on Nivel.codigo = Quali_Nivel.cod_Nivel where Qualificacao.cod_Quali = ?";
		Connection con = null;
		PreparedStatement stms = null;
		ArrayList<Nivel> niveis = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, q.getCodigo());
			ResultSet rs = stms.executeQuery();
			if(rs != null) {
				niveis = new ArrayList<Nivel>();
				while(rs.next()) {
					Nivel n = new Nivel(rs.getInt("codigo"),
							            rs.getString("nome"));
					niveis.add(n);
					
				}
			}
			
		}catch(SQLException s) {
			new ExceptionDao("Erro ao Selecionar"+s);
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
	
	
		return niveis;
	}

	public int buscarCodigo(Qualificacao q, Nivel n)throws ExceptionDao {
		String sql = "select codigo_Quali_Nivel from Quali_Nivel where cod_Quali = ? and cod_Nivel = ?";
		Connection con = null;
		PreparedStatement stms = null;
		int codigo = 0;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setInt(1, q.getCodigo());
			stms.setInt(2, n.getCodigo());
			ResultSet rs = stms.executeQuery();
			if(rs.next()) {
					codigo = rs.getInt("codigo_Quali_Nivel");
			}
			
			
		}catch(SQLException s) {
			new ExceptionDao("Erro ao Selecionar"+s);
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
	
	
		
		return codigo;
	}

	
}
