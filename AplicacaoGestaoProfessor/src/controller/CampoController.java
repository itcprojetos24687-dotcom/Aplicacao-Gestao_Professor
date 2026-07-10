package controller;
import java.util.ArrayList;
import dao.*;
import model.Campo;
import model.Sala;
public class CampoController {
	public boolean cadastrarCampo( String nome) throws ExceptionDao{
		if(nome != null &&nome.length()>0 ) {
			Campo campo = new Campo(nome);
			campo.cadastrarCampo(campo);
			return true;
		}
		return false;
	}
	public ArrayList<Campo> listarCampo () throws ExceptionDao{
		return new Campo().listarCampo();
	}
	public boolean atualizarCampo(int codigo,String nome)
			throws ExceptionDao{
				if( codigo>0 && nome != null && nome.length()>0 ){
					Campo campo = new Campo(nome);
					campo.setCodigo(codigo);
					campo.atualizarCampo(campo);
					return true;
				}
				return false;
				}

	public boolean apagarCampo(int codigo) throws ExceptionDao{

		if(codigo != 0) {
			Campo campo = new Campo();
			campo.setCodigo(codigo);
			campo.apagarCampo(campo);
			return true;
			
		}
		return false;
	}
}

	

