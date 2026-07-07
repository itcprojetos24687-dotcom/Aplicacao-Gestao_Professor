package controller;
import model.Turma;

import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class TurmaController {
	static Scanner sc = new Scanner(System.in);

	public boolean cadastrarTurma(String nome, int ano_ingresso, String turno)

	throws ExceptionDao{
		if(nome != null && nome.length()>0 && ano_ingresso > 0 && turno != null && turno.length() > 0){
			Turma turma = new Turma(nome, ano_ingresso, turno);
			turma.cadastrarTurma(turma);
			return true;
		}
		return false;
		}
//	public ArrayList<Turma> listarTurma(String nome) throws ExceptionDao{
//
//		return new Turma().listarTurma(nome);
//	}
//	public boolean atualizarTurma(int codigo, String nome, int ano_ingresso, String turno)
//			throws ExceptionDao{
//				if(nome != null && nome.length()>0 && codigo != 0 && ano_ingresso > 0 && turno != null && turno.length() > 0){
//					Turma turma = new Turma(nome, ano_ingresso, turno);
//					turma.setCodigo(codigo);
//					turma.atualizarTurma(turma);;
//					return true;
//				}
//				return false;
//				}
//	public boolean apagarTurma(int codigo) throws ExceptionDao{
//
//		if(codigo != 0) {
//
//			Turma turma = new Turma();
//			turma.setCodigo(codigo);
//			turma.apagarTurma(turma);
//			return true;
//
//		}
//		return false;
//	}
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