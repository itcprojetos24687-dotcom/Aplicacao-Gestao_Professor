package model;

import java.util.ArrayList;
import dao.*;
import javax.swing.JOptionPane;
import dao.ExceptionDao;

public class Usuario {
	private int codigo;
	private String nome_completo;
	private String username;
	private String password;
	private String email;
	private Perfil perfil;
	private boolean primeiroAcesso;

	public Usuario() {
	}

	public Usuario(String username,String password) {
		this.username = username;
		this.password = password;
	}
	public Usuario(String nome_completo,String username, String password,String email,Perfil perfil,boolean primeiro) {
		this.nome_completo = nome_completo;
		this.username = username;
		this.password = password;
		this.email = email;
		this.perfil = perfil;
		primeiroAcesso = primeiro;
	}
	public Usuario(String nome_completo,String username, String password,String email,Perfil perfil) {
		this.nome_completo = nome_completo;
		this.username = username;
		this.password = password;
		this.email = email;
		this.perfil = perfil;
		
	}
	
	public boolean isPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso(boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
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



	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public int getIdPerfil() {
//		return idPerfil;
//	}
//
//	public void setIdPerfil(int idPerfil) {
//		this.idPerfil = idPerfil;
//	}
	public Usuario login(String username,String password)throws ExceptionDao {
		 return new UsuarioDao().login(username,password);
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
	
//	public Usuario obterUsuarioPorCodigo(int codigo) throws ExceptionDao {
//		return new UsuarioDao().obterUsuarioPorCodigo(codigo);
//	}

	public Usuario autenticar(String password)throws ExceptionDao {
	 return new UsuarioDao().autenticar(password);
}
	public void refinirSenha(String novapassword,String antigapassword)throws ExceptionDao{
		new UsuarioDao().refinirSenha(novapassword,antigapassword);
	}
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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