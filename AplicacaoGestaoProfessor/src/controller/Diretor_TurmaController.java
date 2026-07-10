package controller;
import model.*;
import dao.*;
public class Diretor_TurmaController {
	public void cadastrarDiretor_Turma(Formador f)throws ExceptionDao{
		Diretor_Turma dt = new Diretor_Turma(f);
		dt.cadastrarDiretor_Turma(dt);
	}
}
