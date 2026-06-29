package dao;
import servico.UsuarioServico;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Usuario;
public class UsuarioDao {
	public Usuario login(String username,String password) throws ExceptionDao{
		String sql = "select * from Usuario where username=? and password=?";
		Connection con = null;
		PreparedStatement lg = null;
		Usuario u = null;
		try {
			con = new Conexao().getConnection();
			JOptionPane.showMessageDialog(null, "Conexao boa");
			lg = con.prepareStatement(sql);
			JOptionPane.showMessageDialog(null, "Prepared Statement guardado");
			lg.setString(1,username);
			lg.setString(2, password);
			JOptionPane.showMessageDialog(null, "Parametros adicionados");
			ResultSet rs = lg.executeQuery();
			JOptionPane.showMessageDialog(null, "Query executada com sucesso");
			
			if(rs != null) {
				while(rs.next()) {
					 u = new Usuario();
					 u.setUsername(rs.getString("username"));
					 u.setPassword(rs.getString("password"));
					 JOptionPane.showMessageDialog(null, "Dados guardados no usuario");
					
				}
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao fazer login");
			new ExceptionDao("Erro ao fazer login"+e);
			
		}finally {
			
				try {
					if(lg != null) {
						lg.close();
						JOptionPane.showMessageDialog(null, "statement fechado com sucesso");
					}
				}catch(Exception es) {
					JOptionPane.showMessageDialog(null, "Erro ao fechar statement");
					throw new ExceptionDao("Erro ao fechar o statement: "+ es);
				}
				try {
					if(con != null) {
						con.close();
						JOptionPane.showMessageDialog(null, "Conexao fechado com sucesso");
					}			
				}
				catch(SQLException sq) {
					JOptionPane.showMessageDialog(null, "Erro ao fechar conexao");
					throw new ExceptionDao("Erro ao fechar conexao: " + sq);
				}
			
			}
		
		
		return u;
	}
}
