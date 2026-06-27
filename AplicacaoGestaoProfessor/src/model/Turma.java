package model;
import java.util.ArrayList;
public class Turma  {
	private int codigo_turma;
	private String nome_turma;
	private int ano_ingresso;
	private String turno;
	private ArrayList<Licao> licoes;
	private Diretor_Turma diretor_turma;
	
	public Turma(String nome, int ano_ingresso, String turno) {
		nome_turma = nome;
		this.ano_ingresso = ano_ingresso;
		this.turno = turno;
	}

	public int getCodigo_turma() {
		return codigo_turma;
	}

	public void setCodigo_turma(int codigo_turma) {
		this.codigo_turma = codigo_turma;
	}

	public String getNome_turma() {
		return nome_turma;
	}

	public void setNome_turma(String nome_turma) {
		this.nome_turma = nome_turma;
	}

	public int getAno_ingresso() {
		return ano_ingresso;
	}

	public void setAno_ingresso(int ano_ingresso) {
		this.ano_ingresso = ano_ingresso;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	public void cadastrarTurma(Turma turma) {
		
	}

	@Override
	public String toString() {
		return "Turma [codigo_turma=" + codigo_turma + ", nome_turma=" + nome_turma + ", ano_ingresso=" + ano_ingresso
				+ ", turno=" + turno + "]";
	}
	
	
	
}
