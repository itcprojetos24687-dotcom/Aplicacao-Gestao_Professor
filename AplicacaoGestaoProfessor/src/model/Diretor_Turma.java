package model;
import java.util.ArrayList;


import dao.*;

public class Diretor_Turma {
	private Formador formador;
	public Diretor_Turma() {}
	public Diretor_Turma(Formador formador) {
		this.formador= formador;
	}
	
	public Formador getFomador() {
		return formador;
	}
	public void setFormador(Formador formador) {
		this.formador=formador;
	}

	public ArrayList<Diretor_Turma> comboDiretor_Turma()throws ExceptionDao {
		return new Diretor_TurmaDao().comboDiretor_Turma();
		
	}
	
	public void cadastrarDiretor_Turma(Diretor_Turma diretor)throws ExceptionDao{
		new Diretor_TurmaDao().cadastrarDiretor_Turma(diretor);;
	}
//	public ArrayList<Diretor_Turmas> listarDiretor_Turma(Diretor_Turma diretor) throws ExceptionDao{
//		return new Diretor_TurmaDao().listarDiretor_Turma(diretor);
//	}
	public void atualizarDiretor_Turma(Diretor_Turma diretor, int codigo) throws ExceptionDao{
		new Diretor_TurmaDao().atualizarDiretor_Turma(diretor, codigo);
	}
//	public void apagarCampo(Diretor_Turma diretor)throws ExceptionDao{
//		new Diretor_TurmaDao().apagarDiretor_Turma(diretor);
//	}
	public String toString() {
		return formador.getNome();
	}
}
