package controller;
import model.Formador;

import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import servico.FormadorServico;
public class FormadorController {
	static Scanner sc = new Scanner(System.in);

	public boolean cadastrarFormador(String nome, String apelido, String email, String genero, String estadoCivil,int contacto, double salario)

	throws ExceptionDao{
		if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+")&& apelido != null && apelido.matches("[a-zA-z]+") && apelido.length() > 0 && email != null && email.length() > 0 && contacto > 0){
			Formador formador = new Formador(nome, apelido, email,genero,estadoCivil,contacto, salario);
			formador.cadastrarFormador(formador);
//			FormadorServico fs = new FormadorServico(formador);
//			fs.salvar(formador);
			return true;
		}
		return false;
		}
	public ArrayList<Formador> listarFormador(String nome) throws ExceptionDao{

		return new Formador().listarFormador(nome);
	}
	public boolean atualizarFormador(int codigo, String nome, String apelido, String email, String genero, String estadoCivil,int contacto, double salario)
			throws ExceptionDao{
				if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+") && codigo != 0&& apelido != null && apelido.matches("[a-zA-z]+") && apelido.length() > 0 && email != null && email.length() > 0 && contacto > 0){
					Formador formador = new Formador(nome, apelido, email,genero,estadoCivil,contacto, salario);
					formador.setCodigo(codigo);
					formador.atualizarFormador(formador);;
					return true;
				}
				return false;
				}
	public boolean apagarFormador(int codigo) throws ExceptionDao{

		if(codigo != 0) {

			Formador formador = new Formador();
			formador.setCodigo(codigo);
			formador.apagarFormador(formador);
			return true;

		}
		return false;
	}
	/*public static void main( String[]args) throws ExceptionDao{
		FormadorController controller = new FormadorController();
		System.out.print("Adiciona o codigo");
		int codigo = sc.nextInt();
		try {
			controller.apagarFormador(codigo);
			JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no metodo apagar Professor");
			throw new ExceptionDao("Erro ao apagar Professor"+e);
		}
	}*/


}