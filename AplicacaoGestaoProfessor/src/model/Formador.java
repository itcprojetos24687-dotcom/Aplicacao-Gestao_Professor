package model;

import java.util.ArrayList;

public class Formador {
	private int codigo;
	private String nome;
	private String apelido;
	private String email;
	private int contacto;
	private ArrayList<Licao> licoes;
	
	public Formador(String nome, String apelido, String email, int contacto) {
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.contacto = contacto;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getContacto() {
		return contacto;
	}
	public void setContacto(int contacto) {
		this.contacto = contacto;
	}
	
	public void cadastrarFormador(Formador formador) {
		
	}
	@Override
	public String toString() {
		return "Formador [codigo=" + codigo + ", nome=" + nome + ", apelido=" + apelido + ", email=" + email
				+ ", contacto=" + contacto + "]";
	}
	
	
}
