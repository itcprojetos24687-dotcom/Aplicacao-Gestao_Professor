package controller;
import model.Curso;
public class CursoController {
	public boolean CadastrarCurso(String nome,String duracao) {
		if(nome != null && nome.length() >0 && duracao != null && duracao.length()>0) {
			Curso curso = new Curso(nome,duracao);
			return true;
		}
		return false;
	}
}
