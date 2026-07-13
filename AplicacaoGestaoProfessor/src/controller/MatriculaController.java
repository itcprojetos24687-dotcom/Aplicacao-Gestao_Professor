package controller;
import model.*;
import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class MatriculaController {
	static Scanner sc = new Scanner(System.in);
	
	public boolean cadastrarMatricula(Formando f, Qualificacao q, Nivel n,String data_matricula)
	throws ExceptionDao{
		if(f != null && q != null &&n != null && data_matricula.length() > 0 ){
			Matricula matricula = new Matricula(f,q,n,data_matricula);
			matricula.cadastrarMatricula(matricula);
			return true;
		}
		return false;
		}
	public ArrayList<Matricula> listarMatricula(String ano_lectivo) throws ExceptionDao{
		return new Matricula().listarMatricula(ano_lectivo);
	}
	public boolean atualizarMatricula(int codigo,Formando f, Qualificacao q, Nivel n,String data_matricula)
			throws ExceptionDao{
				if(codigo > 0 && f != null && q != null &&n != null && data_matricula.length() > 0 ){
					Matricula matricula = new Matricula(f,q,n,data_matricula);
					matricula.setCodigo(codigo);
					JOptionPane.showMessageDialog(null, matricula);
					matricula.atualizarMatricula(matricula);
					return true;
				}
				return false;
				}
	
	public boolean apagarMatricula(int codigo) throws ExceptionDao{
		if(codigo != 0) {
			Matricula matricula = new Matricula();
			matricula.setCodigo(codigo);
			matricula.apagarMatricula(matricula);
			return true;
			
		}
		return false;
	}
	/*public static void main( String[]args) throws ExceptionDao{
		MatriculaController controller = new MatriculaController();
		System.out.print("Adiciona o codigo");
		int codigo = sc.nextInt();
		try {
			controller.apagarMatricula(codigo);
			JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no metodo apagar Matricula");
			throw new ExceptionDao("Erro ao apagar Matricula"+e);
		}
	}*/
	
	
}