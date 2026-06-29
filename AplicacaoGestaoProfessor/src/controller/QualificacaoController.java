package controller;

import java.util.ArrayList;

import dao.ExceptionDao;
import model.Qualificacao;

public class QualificacaoController {
	public boolean cadastrarQualificacao( String titulo) throws ExceptionDao{
		if(titulo != null && titulo.length()>0 ) {
			Qualificacao qc = new Qualificacao(titulo);
			qc.cadastrarQualificacao(qc);
			return true;
		}
		return false;
	}
	public ArrayList<Qualificacao> comboQualificacao(){
		return new Qualificacao().comboQualificacao()
	}
	/*public ArrayList<Campo> listarCampo (String nome) throws ExceptionDao{
		return new Campo().listarCampo(nome);
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
	}*/
}
