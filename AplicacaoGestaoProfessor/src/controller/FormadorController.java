package controller;
import model.Formador;
import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class FormadorController {
	static Scanner sc = new Scanner(System.in);
	public boolean cadastrarFormador(String nome, String apelido, String email, String genero, String estadoCivil,int contacto, double salario)
	throws ExceptionDao{
		if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+")&& apelido != null && apelido.matches("[a-zA-z]+") && apelido.length() > 0 && email != null && email.length() > 0 && contacto > 0){
			Formador formador = new Formador(nome, apelido, email,genero,estadoCivil,contacto, salario);
			formador.cadastrarFormador(formador);
			return true;
		}
		return false;
		}
	
	public ArrayList<Formador> listarFormador(String nome) throws ExceptionDao{
		return new Formador().listarFormador(nome);
	}
	/*public static void main(String[]args) {
		boolean sucesso;
		do {
			
			System.out.println("Print name");
			String nome = sc.next();
			System.out.println("Print apelido");
			String apelido = sc.next();
			System.out.println("Print email");
			String email = sc.next();
			System.out.println("Print contacto");
			int contacto = sc.nextInt();
			FormadorController formadorController = new FormadorController();
			sucesso = formadorController.cadastrarFormador(nome, apelido, email, contacto);
			
			if (sucesso) {
				JOptionPane.showMessageDialog(null, "Todos os dados foram preenchido scom sucesso");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Todas invalidos");
		}
		}while (!sucesso);
	}*/
	
}

