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
	public boolean cadastrarQuali_modulo(String semestre,Modulo m, Qualificacao q)
	throws ExceptionDao{
		if(semestre != null && semestre.length()>0 &&  m != null && q != null) {
			Quali_modulo quali_modulo = new Quali_modulo(semestre, m,q);
			quali_modulo.cadastrarQuali_modulo(quali_modulo);
			return true;
		}
		return false;
	}
	public ArrayList<Quali_modulo> listarQuali_modulo(String semestre) throws ExceptionDao{
		return new Quali_modulo().listarQuali_modulo(semestre);
	}

public boolean atualizarQuali_modulo(int codigo, String semestre,Modulo modulo, Qualificacao q)
		throws ExceptionDao{
	        if(semestre != null && semestre.length()>0 && codigo != 0 && q != null){
				Quali_modulo quali_modulo = new Quali_modulo( semestre, modulo, q);
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
	