package model;

public class Quali_modulo {
	private int codigo;
	private Modulo modulo;
	private Qualificacao qualificacao;
	private String semestre;
	private int ano_curricular;
	
	public Quali_modulo(String semestre, int ano_curricular) {
		this.semestre = semestre;
		this.ano_curricular = ano_curricular;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getAno_curricular() {
		return ano_curricular;
	}

	public void setAno_curricular(int ano_curricular) {
		this.ano_curricular = ano_curricular;
	}

	@Override
	public String toString() {
		return "Quali_modulo [codigo=" + codigo + ", semestre=" + semestre + ", ano_curricular=" + ano_curricular + "]";
	}
	
}
