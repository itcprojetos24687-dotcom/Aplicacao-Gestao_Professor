package model;

public class Curso implements Comparable<Curso>  {

		private int codigo_curso;
		private String nome_curso;
		private String duracao_curso;	
	
	
	public Curso(String nome_curso, String duracao_curso  ) {
		this.nome_curso = nome_curso;
		this.duracao_curso = duracao_curso;
	}
	public int getCodigo_curso() {
		return codigo_curso;
	}
	public void sentcodigo_curso(int codigo_curso) {
		this.codigo_curso = codigo_curso;
	}
	public String getNome_curso() {
		return nome_curso;
	}
	public void setNome_curso(String nome_curso) {
		this.nome_curso = nome_curso;
	}
	public String duracao_curso() {
		return duracao_curso;
	}
	public void setTurno_curso(String duracao_curso) {
		this.duracao_curso = duracao_curso;
	}
	public int compareTo(Curso curso) {
		if (this.codigo_curso >curso.codigo_curso)
			return 1;
		if (this.codigo_curso < curso.codigo_curso)
			return -1;
		return 0;
	

}
	public void  CadastrarCurso(Curso curso) {
		
	}
}


