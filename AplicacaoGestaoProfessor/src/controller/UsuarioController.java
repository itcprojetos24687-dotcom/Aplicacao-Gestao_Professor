package controller;

import java.util.ArrayList;


import dao.*;


import java.util.Scanner;
import javax.swing.JOptionPane;
import dao.ExceptionDao;
import model.*;
import servico.UsuarioServico;

public class UsuarioController {

	static Scanner sc = new Scanner(System.in);
	


	public Usuario login(String username,String password) throws ExceptionDao{
		if(username != null && username.length()>0 && password != null && password.length()>0) {
			
		return new Usuario().login(username,password);
			

		}
		return null;

	}
//	public void alterarPrimeiroAcesso(Usuario u) throws ExceptionDao {
//		u.setPrimeiroAcesso(false);
//		new UsuarioDao().alterarPrimeiroAcesso(u);
//	}
	public boolean cadastrarUsuario(String nome,String username, String password,String apelido,Perfil perfil)
	throws Exception {

		if (nome != null && nome.length() > 0 &&
			username != null && username.length() > 0 &&
			password != null && password.length() > 0 &&
			apelido != null && apelido.length() > 0 &&
			perfil != null) {

			//JOptionPane.showMessageDialog(null, nome_completo+username+ password+email+perfil);
		

			Usuario usuario = new Usuario(nome,username, password,apelido,perfil);
			usuario.cadastrarUsuario(usuario);
			FicheiroDefault.criarFicheiro(usuario);
			return true;
		}

		return false;
	}
	public boolean atualizarUsuario(int idUser,Perfil p)throws ExceptionDao{
		if(idUser >0 &&
		   p != null) {
			Usuario u = new Usuario();
			u.setCodigo(idUser);
			u.setPerfil(p);
			JOptionPane.showMessageDialog(null,idUser+""+p.getNome()+p.getNome());
			u.atualizarUsuario(u);
			return true;
			
		}
		return false;
	}

	public static boolean resetarSenha(String senhaResetada, int codigo)throws ExceptionDao {
		if(senhaResetada != null && codigo > 0) {
			new UsuarioDao().resetarSenha(senhaResetada,codigo);
			
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


//	public boolean autenticar(String password) throws ExceptionDao {
//		if (password != null && password.length() > 0) {
//			Usuario ul = new Usuario().autenticar(password);
//			if (ul != null) {
//				return true;
//			}

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

	public Usuario autenticar(String password) throws ExceptionDao{
		if(password != null && password.length()>0) {
			
			return new Usuario().autenticar(password);
			

		}
		return null;
		
	}

	public void redifinirSenha(String novapassword, int codigo) throws ExceptionDao {
		new Usuario().refinirSenha(novapassword, codigo);
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


	public boolean atualizarUsuario(int idUser, String nome, String username, String apelido, Perfil p) throws ExceptionDao {
		if (idUser != 0 && 
			nome != null && nome.length() > 0 && 
			username != null && username.length() > 0 && 
			apelido != null && apelido.length() > 0 && 
			p != null) {
			
			Usuario usuario = new Usuario();
			usuario.setCodigo(idUser);
			usuario.setNome(nome);
			usuario.setUsername(username);
			usuario.setApelido(apelido);
			usuario.setPerfil(p);
			
			usuario.atualizarUsuario(usuario);
			return true;
		}
		return false;
	}
}