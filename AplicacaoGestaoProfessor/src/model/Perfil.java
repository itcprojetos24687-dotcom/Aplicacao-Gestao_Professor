package model;

import java.util.ArrayList;

import dao.*;

public class Perfil {
	private int id;
	private String nome;
	
	public Perfil() {}
	public Perfil(int id, String nome) {
		this.id = id;
		this.nome= nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Perfil getPerfil(String username) throws ExceptionDao{
		return new PerfilDao().getPerfil( username);
	}
	public ArrayList<Perfil> listarPerfil()throws ExceptionDao{
		return new PerfilDao().listarPerfil();
	}
	@Override
	public String toString() {
		return nome;
	}
	
}
