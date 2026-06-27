package controller;
import java.util.ArrayList;
import dao.*;
import model.Campo;
import model.Nivel;
public class NivelController {
	public boolean cadastrarNivel( String nome) throws ExceptionDao{
		if(nome != null &&nome.length()>0 ) {
			Nivel nivel = new Nivel(nome);
			nivel.cadastrarNivel(nivel);
			return true;
		}
		return false;
	}
	public ArrayList<Nivel> listarCampo (String nome) throws ExceptionDao{
		return new Nivel().listarNivel(nome);
	}
	public boolean atualizarCampo(int codigo,String nome)
			throws ExceptionDao{
				if( codigo>0 && nome != null && nome.length()>0 ){
					Nivel nivel = new Nivel(nome);
					nivel.setCodigo(codigo);
					nivel.atualizarNivel(nivel);
					return true;
				}
				return false;
				}

	public boolean apagarCampo(int codigo) throws ExceptionDao{

		if(codigo != 0) {
			Nivel nivel = new Nivel();
			nivel.setCodigo(codigo);
			nivel.apagarNivel(nivel);
			return true;
			
		}
		return false;
	}
}	
