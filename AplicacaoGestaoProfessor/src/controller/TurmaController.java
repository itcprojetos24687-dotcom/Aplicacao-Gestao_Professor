package controller;
import model.Turma;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.ExceptionDao;

public class TurmaController {
	public boolean cadastrarTurma(String nome, int ano_ingresso, String turno)throws ExceptionDao {
		if(nome != null && nome.length() > 0  && ano_ingresso> 0 && turno != null && turno.length() >0){
			Turma turma = new Turma(nome, ano_ingresso, turno);
			JOptionPane.showMessageDialog(null, nome);
			turma.cadastrarTurma(turma);
			return true;
		}
		return false;
	}
}
