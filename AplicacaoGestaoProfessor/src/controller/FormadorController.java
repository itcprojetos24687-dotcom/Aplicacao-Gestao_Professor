package controller;
import model.Formador;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class FormadorController {
	static Scanner sc = new Scanner(System.in);
	public boolean CadastrarFormador(String nome, String apelido, String email, int contacto) {
		if(nome != null && nome.length()>0 && apelido != null && apelido.length() > 0 && email != null && email.length() > 0 && contacto > 0){
			Formador formador = new Formador(nome, apelido, email,contacto);
			formador.cadastrarFormador(formador);
			return true;
		}
		return false;
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
			sucesso = formadorController.CadastrarFormador(nome, apelido, email, contacto);
			
			if (sucesso) {
				JOptionPane.showMessageDialog(null, "Todos os dados foram preenchido scom sucesso");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Todas invalidos");
		}
		}while (!sucesso);
	}*/
	
}

