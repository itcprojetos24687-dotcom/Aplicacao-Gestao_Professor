package model;

import java.util.ArrayList;
import dao.*;
import javax.swing.JOptionPane;
import dao.ExceptionDao;

public class Usuario {
	private int codigo;
	private String username;
	private String password;

	public Usuario() {
	}

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario iniciarSessao(String username, String password) throws ExceptionDao {
		return new UsuarioDao().iniciarSessao(username, password);
	}

	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().cadastrarUsuario(usuario);
	}

	public ArrayList<Usuario> listarUsuario(String username) throws ExceptionDao {
		return new UsuarioDao().listarUsuario(username);
	}

	public ArrayList<Usuario> obterTodosUsuarios() throws ExceptionDao {
		return new UsuarioDao().obterTodosUsuarios();
	}

	public Usuario obterUsuarioPorCodigo(int codigo) throws ExceptionDao {
		return new UsuarioDao().obterUsuarioPorCodigo(codigo);
	}

	public boolean autenticarUsuario(String username, String password) throws ExceptionDao {
		Usuario u = this.iniciarSessao(username, password);
		if (u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}

	public void atualizarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().atualizarUsuario(usuario);
	}

	public void apagarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().apagarUsuario(usuario);
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", username=" + username + "]";
	}
}