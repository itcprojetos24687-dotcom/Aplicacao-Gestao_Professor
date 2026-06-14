package model;

public class Curso implements Comparable<Curso>  {

		private int codigo_curso;
		private String nome_curso;
		private String campo;
		private String turno_curso;	
	
	
	public Curso(int codigo_curso, String nome_curso, String campo, String turno_curso  ) {
		this.codigo_curso = codigo_curso;
		this.nome_curso = nome_curso;
		this.campo = campo;
		this.turno_curso = turno_curso;
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
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getTurno_curso() {
		return turno_curso;
	}
	public void setTurno_curso(String turno_curso) {
		this.turno_curso = turno_curso;
	}
	public int compareTo(Curso curso) {
		if (this.codigo_curso >curso.codigo_curso)
			return 1;
		if (this.codigo_curso < curso.codigo_curso)
			return -1;
		return 0;

}
}


