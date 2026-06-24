package model;
import java.util.ArrayList;

public class Nivel {
	private int codigo;
	private String nome;
	private ArrayList<Quali_Nivel> quali_nivel;
	
	public Nivel(String nome) {
		this.nome = nome;
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

	@Override
	public String toString() {
		return "Nivel [codigo=" + codigo + ", nome=" + nome + "]";
	}
	
	
}

