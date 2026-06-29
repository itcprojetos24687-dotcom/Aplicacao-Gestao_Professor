package model;
import java.util.ArrayList;
import dao.*;
public class Turma  {
	private int codigo;
	private String nome;
	private int ano_ingresso;
	private String turno;
	private ArrayList<Licao> licoes;
	private Diretor_Turma diretor_turma;
	
	public Turma() {
		
	}
	public Turma(String nome, int ano_ingresso, String turno) {
		nome = nome;
		this.ano_ingresso = ano_ingresso;
		this.turno = turno;
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
		new TurmaDao().cadastrarTurma(turma);
	}

	@Override
	public String toString() {
		return "Turma [codigo=" + codigo + ", nome_turma=" + nome + ", ano_ingresso=" + ano_ingresso
				+ ", turno=" + turno + "]";
	}
	
	
	
}
