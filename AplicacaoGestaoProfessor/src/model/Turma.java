package model;

public class Turma implements Comparable<Turma> {
	private int codigo_turma;
	private String nome_turma;
	private int ano_ingresso;
	private int codigo_curso;
	
	public Turma() {
		
	}
	
	public Turma(int codigo_turma,String nome_turma, int ano_ingresso, int codigo_curso ) {
		this.codigo_turma = codigo_turma;
		this.nome_turma = nome_turma;
		this.ano_ingresso = ano_ingresso;
		this.codigo_curso = codigo_curso;
	}
	public int getCodigo_turma() {
		return codigo_turma;
	}
	public void sentcodigo_turma(int codigo_turma) {
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
	public int getcodigo_curso() {
		return codigo_curso;
	}
	public void setcodigo_curso(int codigo_curso) {
		this.codigo_curso = codigo_curso;
	}
	public int compareTo(Turma turma) {
		if (this.codigo_turma > turma.codigo_turma)
			return 1;
		if (this.codigo_turma < turma.codigo_turma)
			return -1;
		return 0;

}
}
