package model;
import java.util.ArrayList;
public class Inscricao {
	private int codigo;
	private Modulo modulo;
	private int data_inscricao;
	private String semestre;
	private ArrayList<Formando> formandos;
	
	public Inscricao(int data_inscricao, String semestre) {
		this.data_inscricao = data_inscricao;
		this.semestre = semestre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getData_inscricao() {
		return data_inscricao;
	}

	public void setData_inscricao(int data_inscricao) {
		this.data_inscricao = data_inscricao;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	@Override
	public String toString() {
		return "Inscricao [codigo=" + codigo + ", data_inscricao=" + data_inscricao + ", semestre=" + semestre + "]";
	}
	
}
