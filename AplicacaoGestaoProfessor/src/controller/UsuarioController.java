package controller;

import java.util.ArrayList;
import java.util.Scanner;
import dao.ExceptionDao;
import model.Usuario;

public class UsuarioController {

	static Scanner sc = new Scanner(System.in);

	public boolean cadastrarUsuario(String username, String password)
	throws ExceptionDao {

		if (username != null && username.length() > 0 &&
			password != null && password.length() > 0) {

			Usuario usuario = new Usuario(username, password);
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

	public Usuario obterUsuarioPorCodigo(int codigo) throws ExceptionDao {

		if (codigo != 0) {
			return new Usuario().obterUsuarioPorCodigo(codigo);
		}

		return null;
	}

	public boolean autenticarUsuario(String username, String password)
	throws ExceptionDao {

		if (username != null && username.length() > 0 &&
			password != null && password.length() > 0) {

			return new Usuario().autenticarUsuario(username, password);
		}

		return false;
	}

	public boolean atualizarUsuario(int codigo, String username, String password)
	throws ExceptionDao {

		if (codigo != 0 &&
			username != null && username.length() > 0 &&
			password != null && password.length() > 0) {

			Usuario usuario = new Usuario(username, password);
			usuario.setCodigo(codigo);
			usuario.atualizarUsuario(usuario);

			return true;
		}

		return false;
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

	public Usuario iniciarSessao(String username, String password)
	throws ExceptionDao {

		if (username != null && username.length() > 0 &&
			password != null && password.length() > 0) {

			Usuario usuario = new Usuario();
			return usuario.iniciarSessao(username, password);
		}

		return null;
	}
}