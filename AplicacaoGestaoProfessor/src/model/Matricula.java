package model;

import java.util.ArrayList;
import dao.*;
import javax.swing.JOptionPane;

import dao.ExceptionDao;
import dao.FormadorDao;

public class Matricula {
	private int codigo;
	private Qualificacao qualificacao;
	private Formando formando;
	private String data_matricula;
	private Nivel nivel;
	
	
	public Matricula () {
		
	}
	
	public Matricula(Formando f, Qualificacao q,Nivel n, String data_matricula) {
		formando = f;
		qualificacao = q;
		nivel = n;
		this.data_matricula = data_matricula;
		
	}

	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getData_matricula() {
		return data_matricula;
	}
	public void setData_matricula(String data_matricula) {
		this.data_matricula = data_matricula;
	}



	
	public Qualificacao getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}

	public Formando getFormando() {
		return formando;
	}

	public void setFormando(Formando formando) {
		this.formando = formando;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}




	
	public void cadastrarMatricula(Matricula matricula) throws ExceptionDao{
		new MatriculaDao().cadastrarMatricula(matricula);
	}
	public ArrayList<Matricula> listarMatricula(String ano_lectivo) throws ExceptionDao{
		return new MatriculaDao().listarMatricula(ano_lectivo);
	}
	public void atualizarMatricula(Matricula matricula) throws ExceptionDao{
		new MatriculaDao().atualizarMatricula(matricula);
	}
	public void apagarMatricula(Matricula matricula) throws ExceptionDao{
		JOptionPane.showMessageDialog(null, "Chamado no model com sucesso");
		new MatriculaDao().apagarMatricula(matricula);
	}


	@Override
	public String toString() {
		return "Matricula [codigo=" + codigo + ", data_matricula=" + data_matricula + ", ano_lectivo=" 
				+ "]";
	}
	
	
}
