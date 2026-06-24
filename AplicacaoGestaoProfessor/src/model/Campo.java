package model;
import java.util.ArrayList;
public class Campo {
	private int codigo;
	private String nome;
	private ArrayList<Classificacao> classificacoes;
	
	public Campo(String nome) {
		this.nome = nome;
	}
}
