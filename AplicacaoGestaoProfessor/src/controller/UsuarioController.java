package controller;

import java.util.ArrayList;

import java.util.Scanner;

import javax.swing.JOptionPane;

import dao.ExceptionDao;
import model.*;
import servico.UsuarioServico;

public class UsuarioController {

	static Scanner sc = new Scanner(System.in);
	
	public boolean login(String username,String password) throws ExceptionDao{
		if(username != null && username.length()>0 && password != null && password.length()>0) {
			
			Usuario ul = new Usuario().login(username,password);;
			if(ul != null) {
				//JOptionPane.showMessageDialog(null, ul.getUsername()+ul.getPassword());
				return true;
			}
		}
		return false;
	}
	public boolean cadastrarUsuario(String nome_completo,String username, String password,String email,Perfil perfil)
	throws ExceptionDao {

		if (nome_completo != null && nome_completo.length()>0 &&
			username != null && username.length() > 0 &&
			password != null && password.length() > 0 &&
			email != null && email.length()> 0 &&
			perfil != null) {

			Usuario usuario = new Usuario(nome_completo,username, password,email,perfil);
			usuario.cadastrarUsuario(usuario);
			return true;
		}

		return false;
	}

	public ArrayList<Usuario> listarUsuario(String username) throws ExceptionDao {
		return new Usuario().listarUsuario(username);
	}

	public ArrayList<Usuario> obterTodosUsuarios() throws ExceptionDao {
		return new Usuario().obterTodosUsuarios();
	}

//	public Usuario obterUsuarioPorCodigo(int codigo) throws ExceptionDao {
//
//		if (codigo != 0) {
//			return new Usuario().obterUsuarioPorCodigo(codigo);
//		}
//
//		return null;
//	}

//	public boolean autenticarUsuario(String username, String password)
//	throws ExceptionDao {
//
//		if (username != null && username.length() > 0 &&
//			password != null && password.length() > 0) {
//
//			return new Usuario().autenticarUsuario(username, password);
//		}
//
//		return false;
//	}

	public boolean autenticar(String password) throws ExceptionDao{
		if(password != null && password.length()>0) {
			
			Usuario ul = new Usuario().autenticar(password);
			if(ul != null) {
				//JOptionPane.showMessageDialog(null, ul.getUsername()+ul.getPassword());
				return true;
			}
		}
		return false;
	}

	public void redifinirSenha(String novapassword,String antigapassword) throws ExceptionDao{
		
		new Usuario().refinirSenha(novapassword,antigapassword);
		
	}
	public boolean apagarUsuario(int codigo) throws ExceptionDao {

		if (codigo != 0) {

			Usuario usuario = new Usuario();
			usuario.setCodigo(codigo);
			usuario.apagarUsuario(usuario);

			return true;
		}

		return false;
	}
	public 

//	public Usuario iniciarSessao(String username, String password)
//	throws ExceptionDao {
//
//		if (username != null && username.length() > 0 &&
//			password != null && password.length() > 0) {
//
//			Usuario usuario = new Usuario(username, password);
//			return usuario.login(usuario);
//		}
//
//		return null;
//	}
}