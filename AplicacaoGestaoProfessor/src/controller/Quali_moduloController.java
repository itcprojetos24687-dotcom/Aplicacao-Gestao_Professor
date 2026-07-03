package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.*;

import dao.ExceptionDao;
import model.Quali_modulo;
import model.Qualificacao;
import model.Modulo;

public class Quali_moduloController {
	static Scanner sc = new Scanner(System.in);
	public boolean cadastrarQuali_modulo(String semestre, int ano_curricular)
	throws ExceptionDao{
		if(semestre != null && semestre.length()>0 && semestre.matches("[a-zA-Z]+") &&  ano_curricular>0) {
			Quali_modulo quali_modulo = new Quali_modulo(semestre, ano_curricular);
			quali_modulo.cadastrarQuali_modulo(quali_modulo);
			return true;
		}
		return false;
	}
	public ArrayList<Quali_modulo> listarQuali_modulo(String semestre) throws ExceptionDao{
		return new Quali_modulo().listarQuali_modulo(semestre);
	}

public boolean atualizarQuali_modulo(String semestre, int ano_curricular, int codigo)
		throws ExceptionDao{
			if(semestre != null && semestre.length()>0 && semestre.matches("[a-zA-Z]+") && codigo != 0 && ano_curricular > 0){
				Quali_modulo quali_modulo = new Quali_modulo( semestre,  ano_curricular);
				quali_modulo.setCodigo(codigo);
				quali_modulo.atualizarQuali_modulo(quali_modulo);;
				return true;
			}
			return false;
			}
public boolean apagarQuali_modulo(int codigo) throws ExceptionDao{

	if(codigo != 0) {

		Quali_modulo quali_modulo = new Quali_modulo();
		quali_modulo.setCodigo(codigo);
		quali_modulo.apagarQuali_modulo(quali_modulo);
		return true;
		
	}
	return false;
}

}
	