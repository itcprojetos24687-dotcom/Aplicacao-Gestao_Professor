package model;

import java.util.ArrayList;
import dao.*;
import javax.swing.JOptionPane;
import dao.ExceptionDao;

public class Usuario {
	private int codigo;
	private String nome;
	private String username;
	private String password;
	private String apelido;
	private Perfil perfil;
	private boolean primeiroAcesso;

	public Usuario() {
	}

	public Usuario(String username,String password) {
		this.username = username;
		this.password = password;
	}
	public Usuario(String nome,String username, String password,String apelido,Perfil perfil,boolean primeiro) {
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.apelido = apelido;
		this.perfil = perfil;
		primeiroAcesso = primeiro;
	}
	public Usuario(String nome,String username, String password,String apelido,Perfil perfil) {
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.apelido = apelido;
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



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
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
	public void logout(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().registarLogout(usuario);
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
	public void refinirSenha(String novapassword,int codigo)throws ExceptionDao{
		new UsuarioDao().refinirSenha(novapassword,codigo);
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
		return "USuario: Codigo: "+codigo+" Username: "+username+" Apelido: "+apelido+" password: "+password+" primeiroAcesso: "+primeiroAcesso+" idPerfil: "+ perfil.getId()+" NomePerfil: "+perfil.getNome();
	}
}