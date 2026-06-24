package model;
import java.util.ArrayList;
public class Qualificacao {
	private int codigo;
	private String titulo;
	private ArrayList<Classificacao> classificacoes;
	private ArrayList<Quali_Nivel> quali_nivel;
	private ArrayList<Quali_modulo> quali_modulo;
	
	public Qualificacao(String titulo) {
		this.titulo = titulo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Qualificacao [codigo=" + codigo + ", titulo=" + titulo + "]";
	}
	
}
