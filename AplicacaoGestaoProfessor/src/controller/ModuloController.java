
package controller;
import model.*;
import dao.ExceptionDao;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ModuloController {
	static Scanner sc = new Scanner(System.in);
	public boolean cadastrarModulo(String nome, int carga_horaria, String semestre,Qualificacao q, Nivel n)
	throws ExceptionDao{
		if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+") &&  carga_horaria>0 && q != null && n != null) {
			Modulo modulo = new Modulo(nome,  carga_horaria);
			if(q != null && n != null) {
				Quali_NivelController qc = new Quali_NivelController();
				int codigoQuali_Nivel = qc.buscarCodigo(q,n);
				
				if(codigoQuali_Nivel >0) {
					Quali_Nivel qn = new Quali_Nivel();
					qn.setCodigo(codigoQuali_Nivel);
					modulo.setQuali_Nivel(qn);
					modulo.cadastrarModulo(modulo);
					
					if(modulo.getCodigo()>0) {
						Quali_moduloController qm = new Quali_moduloController();
						boolean sucesso = qm.cadastrarQuali_modulo(semestre, modulo, q);
						if(sucesso) {
							
							return true;
						}
				}
			}
				
			}
			
		}
		return false;
	}
	public ArrayList<Modulo> listarModulo(String nome) throws ExceptionDao{
		return new Modulo().listarModulo(nome);
	}

public boolean atualizarModulo(String nome, int carga_horaria, int codigo, Qualificacao q, Nivel n,String semestre)
		throws ExceptionDao{
			if( semestre != null && semestre.length()>0 && q != null && n != null &&nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+") && codigo != 0 && carga_horaria > 0){
				Modulo modulo = new Modulo( nome,  carga_horaria);
				modulo.setCodigo(codigo);
				if(q != null && n != null) {
					Quali_NivelController qc = new Quali_NivelController();
					int codigoQuali_Nivel = qc.buscarCodigo(q,n);
					
					if(codigoQuali_Nivel > 0) {
						Quali_Nivel qn = new Quali_Nivel();
						qn.setCodigo(codigoQuali_Nivel);
						modulo.setQuali_Nivel(qn);
						modulo.atualizarModulo(modulo);
						
						Quali_moduloController qm = new Quali_moduloController();
						qm.atualizarQuali_modulo(codigo, semestre, modulo, q);
						
						return true;
					}
				}
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