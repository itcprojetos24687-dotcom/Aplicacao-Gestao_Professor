package controller;
import model.*;
import java.util.ArrayList;
import dao.*;
public class Diretor_TurmaController {
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
}
