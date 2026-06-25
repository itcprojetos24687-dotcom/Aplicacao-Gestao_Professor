package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	//classe para estabelecer conexao com a base de dados
	public Connection getConnection() {
		Connection con = null;
		
		//Declaracao do drive, url, nome do usuario e senha da base de dados
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/GestaoProfessor";
		final String user = "root";
		final String senha = "Malikdb123!";
		
		//Carregando o driver com a classe class
		try {
			Class.forName(driver);
			JOptionPane.showMessageDialog(null, "Driver carregado com sucessso");
		}catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Class nao encontrada");
			System.out.print(e.getMessage());
		}
		
		//Estabelenco conexao com a base dados
		try {
			con = DriverManager.getConnection(url,user,senha);
			JOptionPane.showMessageDialog(null, "Conexao Estabelecida");
		}catch(SQLException f) {
			JOptionPane.showMessageDialog(null, "Class nao encontrada");
			System.out.print(f.getMessage());
		}
		return con;
	}
}
