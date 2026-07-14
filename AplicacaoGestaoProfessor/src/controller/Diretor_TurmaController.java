package controller;
import model.*;
import java.util.ArrayList;
import dao.*;
public class Diretor_TurmaController {
	public void salvarDiretor(Formador f, int codigoFormador) throws ExceptionDao{
		if(new Diretor_Turma().isDiretor(codigoFormador)) {
			atualizarDiretor_Turma(f, codigoFormador);
		}
		else {
			cadastrarDiretor_Turma(f);
		}
	}
	public boolean cadastrarDiretor_Turma(Formador f)throws ExceptionDao{
		
		if(f != null) {
			Diretor_Turma dt = new Diretor_Turma(f);
			dt.cadastrarDiretor_Turma(dt);
			return true;
		}
		return false;
	}
	public ArrayList<Diretor_Turma> comboDiretor_Turma()throws ExceptionDao{
		return new Diretor_Turma().comboDiretor_Turma();
	}
	public boolean atualizarDiretor_Turma(Formador f, int codigo)throws ExceptionDao{
		if(f != null) {
			Diretor_Turma dt = new Diretor_Turma(f);
			dt.atualizarDiretor_Turma(dt, codigo);
		}
		return false;
	}
	public boolean isDiretor(int codigoFormador) throws ExceptionDao{
		if(codigoFormador > 0) {
			return new Diretor_Turma().isDiretor(codigoFormador);
			
		}
		
		return false;
	}
	public boolean apagarDiretor(int codigoFormador) throws ExceptionDao{
		if( codigoFormador > 0) {
			new Diretor_Turma().apagarDiretor(codigoFormador);
			return true;
		}
		return false;
	}
}
