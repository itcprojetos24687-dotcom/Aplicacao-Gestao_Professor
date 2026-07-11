package controller;
import model.*;

import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class TurmaController {
	static Scanner sc = new Scanner(System.in);

	public boolean cadastrarTurma(String nome, int ano_ingresso, String turno,Diretor_Turma dt, Qualificacao q)

	throws ExceptionDao{
		if(nome != null && nome.length()>0 && ano_ingresso > 0 && turno != null && turno.length() > 0 && dt != null && q != null){
			Turma turma = new Turma(nome, ano_ingresso, turno);
			turma.setDiretor_turma(dt);
			turma.setQualificacao(q);
			Diretor_TurmaController dc = new Diretor_TurmaController();
			turma.cadastrarTurma(turma);
			return true;
		}
		return false;
		}
	public ArrayList<Turma> listarTurma(String nome) throws ExceptionDao{

		return new Turma().listarTurma(nome);
	}
	public boolean atualizarTurma(int codigo, String nome, int ano_ingresso, String turno, Diretor_Turma dt, Qualificacao q)
			throws ExceptionDao{
				if(nome != null && nome.length()>0 && codigo != 0 && ano_ingresso > 0 && turno != null && turno.length() > 0){
					Turma turma = new Turma(nome, ano_ingresso, turno);
					turma.setCodigo(codigo);
					turma.setDiretor_turma(dt);
					turma.setQualificacao(q);
					turma.atualizarTurma(turma);;
						return true;
					
				}
				return false;
				}
	public boolean apagarTurma(int codigo) throws ExceptionDao{

		if(codigo != 0) {

			Turma turma = new Turma();
			
			turma.apagarTurma(codigo);
			return true;

		}
		return false;
	}
	/*public static void main( String[]args) throws ExceptionDao{
		TurmaController controller = new TurmaController();
		System.out.print("Adiciona o codigo");
		int codigo = sc.nextInt();
		try {
			controller.apagarTurma(codigo);
			JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no metodo apagar Turma");
			throw new ExceptionDao("Erro ao apagar Turma"+e);
		}
	}*/


}