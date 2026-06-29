package controller;
import model.Modulo;
import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ModuloController {
	static Scanner sc = new Scanner(System.in);
	public boolean cadastrarModulo(String nome, int carga_horaria)
	throws ExceptionDao{
		if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+") &&  carga_horaria>0) {
			Modulo modulo = new Modulo(nome,  carga_horaria);
			modulo.cadastrarModulo(modulo);
			return true;
		}
		return false;
	}
	public ArrayList<Modulo> listarModulo(String nome) throws ExceptionDao{
		return new Modulo().listarModulo(nome);
	}

public boolean atualizarModulo(String nome, int carga_horaria, int codigo)
		throws ExceptionDao{
			if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+") && codigo != 0 && carga_horaria > 0){
				Modulo modulo = new Modulo( nome,  carga_horaria);
				modulo.setCodigo(codigo);
				modulo.atualizarModulo(modulo);;
				return true;
			}
			return false;
			}
public boolean apagarModulo(int codigo) throws ExceptionDao{

	if(codigo != 0) {

		Modulo modulo = new Modulo();
		modulo.setCodigo(codigo);
		modulo.apagarModulo(modulo);
		return true;
		
	}
	return false;
}
}