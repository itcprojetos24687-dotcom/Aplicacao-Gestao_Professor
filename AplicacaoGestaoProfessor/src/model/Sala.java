package model;

import java.util.ArrayList;
public class Sala {
	private int codigo;
	private String designacao;
	private String tipo;
	private ArrayList<Licao> licoes;
	
	public Sala(String designacao, String tipo) {
		this.designacao = designacao;
		this.tipo = tipo;
	}
}
