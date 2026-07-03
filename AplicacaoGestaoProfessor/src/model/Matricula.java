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
	private int data_matricula;
	private int ano_lectivo;
	
	
	public Matricula () {
		
	}
	
	public Matricula(int data_matricula, int ano_lectivo) {
		this.data_matricula = data_matricula;
		this.ano_lectivo = ano_lectivo;
	}

	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getData_matricula() {
		return data_matricula;
	}
	public void setData_matricula(int data_matricula) {
		this.data_matricula = data_matricula;
	}



	public int getAno_lectivo() {
		return ano_lectivo;
	}



	public void setAno_lectivo(int ano_lectivo) {
		this.ano_lectivo = ano_lectivo;
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
		return "Matricula [codigo=" + codigo + ", data_matricula=" + data_matricula + ", ano_lectivo=" + ano_lectivo
				+ "]";
	}
	
	
}
