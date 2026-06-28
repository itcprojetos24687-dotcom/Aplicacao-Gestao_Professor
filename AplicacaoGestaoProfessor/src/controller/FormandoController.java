package controller;
import model.Formando;
import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class FormandoController {
	static Scanner sc= new Scanner(System.in);
	public boolean cadastrarFormando(String nome, String apelido, int contacto, String email, String bi)
	throws ExceptionDao{
		if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+") && apelido != null && apelido.matches("[a-zA-z]+") && apelido.length() > 0 && bi != null && email.length()>0 && contacto >0){
			Formando formando = new Formando(nome, apelido, contacto , email, bi);
			formando.cadastrarFormando(formando);
			return true;
		}
		return false;
	}
	public ArrayList<Formando>listarFormando(String nome) throws ExceptionDao{
		return new Formando().listarFormando(nome);
	}
}
