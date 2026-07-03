package controller;
import model.Inscricao;
import model.Modulo;
import dao.ExceptionDao;
import dao.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class InscricaoController {

		static Scanner sc = new Scanner(System.in);
		
		public boolean cadastrarInscricao(int data_inscricao, String semestre)
		throws ExceptionDao{
			if(data_inscricao > 0 &&  semestre.length()> 0){
				Inscricao inscricao = new Inscricao(data_inscricao, semestre);
				inscricao.cadastrarInscricao(inscricao);
				return true;
			}
			return false;
			}
		public ArrayList<Inscricao> listarInscricao(String semestre) throws ExceptionDao{
			return new Inscricao().listarInscricao(semestre);
		}
		
		public boolean atualizarInscricao(String semestre, int data_inscricao, int codigo)
		throws ExceptionDao{
		if(semestre != null && semestre.length()>0 && semestre.matches("[a-zA-Z]+") && codigo != 0 && data_inscricao > 0){
			Inscricao inscricao = new Inscricao(data_inscricao, semestre);
		inscricao.setCodigo(codigo);
		inscricao.atualizarInscricao(inscricao);;
		return true;
		}		
		return false;
		}
		public boolean apagarInscricao(int codigo) throws ExceptionDao{
			if(codigo != 0) {
				Inscricao inscricao = new Inscricao();
				inscricao.setCodigo(codigo);
				inscricao.apagarInscricao(inscricao);
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