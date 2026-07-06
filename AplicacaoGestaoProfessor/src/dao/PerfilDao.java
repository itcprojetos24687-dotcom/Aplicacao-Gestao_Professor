package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Perfil;


public class PerfilDao {
	public ArrayList<Perfil> listarPerfil()throws ExceptionDao{
		String sql = "select * from Perfil";
		Connection con = null;
		PreparedStatement stms = null;
		ArrayList<Perfil> perfis = null;
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			ResultSet rs = stms.executeQuery();
			if(rs != null) {
				perfis = new ArrayList<>();
				while(rs.next()) {
					int id = rs.getInt("id");
					String nome =rs.getString("nome");
					Perfil p = new Perfil(id,nome);
					perfis.add(p);
				}
			}
			
		}catch(SQLException sq) {
			new ExceptionDao("Erro ao selecionar Perfil");
		}finally {
			try {
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException s){
				new ExceptionDao("Erro ao fechar o statement");
				
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException a) {
				new ExceptionDao("Erro ao fechar conexao");
			}
		}
		return perfis;
	}
	public Perfil getPerfil(String username) {
		String sql = "select Perfil.id, Perfil.nome from Usuario join Perfil on idPerfil = id where username = ?";
		Connection con = null;
		PreparedStatement stms = null;
		Perfil p = null;
		
		try{
			con = new Conexao().getConnection();
			stms = con.prepareStatement(sql);
			stms.setString(1, username);
			ResultSet rs = stms.executeQuery();
			JOptionPane.showMessageDialog(null, "Query executada");
			if(rs != null) {
				JOptionPane.showMessageDialog(null, "Result set nao e nulu");
				while(rs.next()) {
					p = new Perfil();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					
					JOptionPane.showMessageDialog(null, "Inicializado:"+p.getId()+p.getNome());
					
				}
			}
			
		}catch(SQLException sq) {
			new ExceptionDao("Erro ao selecionar Perfil");
		}finally {
			try {
				if(stms != null) {
					stms.close();
				}
			}catch(SQLException s){
				new ExceptionDao("Erro ao fechar o statement");
				
			}
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException a) {
				new ExceptionDao("Erro ao fechar conexao");
			}
		}
		
		return p;
	}
	public static void main(String[]args)throws ExceptionDao {
		Perfil p = null;
		try {
			p = new PerfilDao().getPerfil("admin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, p.getNome());
	}
}
