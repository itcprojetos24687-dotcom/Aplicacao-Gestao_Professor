package controller;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dao.ExceptionDao;
import model.*;
public class QualificacaoController {
	static Scanner sc = new Scanner(System.in);

	public boolean cadastrarQualificacao(String titulo, Coordenador coordenador, Campo campo, Nivel nivel)
	throws ExceptionDao{
		if(titulo != null && titulo.length() > 0 && titulo.matches("[a-zA-Z ]+") && coordenador != null && campo != null){
			Qualificacao qc = new Qualificacao(titulo, coordenador);
			qc.cadastrarQualificacao(qc);
			
			if(qc.getCodigo()>0) {
				ClassificacaoController cc = new ClassificacaoController();
				boolean sucesso= cc.cadastrarClassificacao(campo,qc);
				
				if(sucesso) {
					Quali_NivelController qn = new Quali_NivelController();
					Quali_Nivel QN = qn.cadastrarQuali_Nivel(nivel,qc);
					return true;
			}
		}
	}
		return false;
	}

	public ArrayList<Qualificacao> comboQualificacao() throws ExceptionDao{
		return new Qualificacao().comboQualificacao();
	}

	public ArrayList<Qualificacao> listarQualificacao(String titulo) throws ExceptionDao{
		return new Qualificacao().listarQualificacao(titulo);
	}

	public boolean atualizarQualificacao(int codigo, String titulo)
	throws ExceptionDao{
		if(codigo != 0 && titulo != null && titulo.length() > 0 && titulo.matches("[a-zA-Z ]+")){
			Qualificacao qc = new Qualificacao();
			qc.setTitulo(titulo);
			qc.setCodigo(codigo);
			qc.atualizarQualificacao(qc);
			return true;
		}
		return false;
	}

	public boolean apagarQualificacao(int codigo) throws ExceptionDao{
		if(codigo != 0) {
			Qualificacao qc = new Qualificacao();
			qc.setCodigo(codigo);
			qc.apagarQualificacao(qc);
			return true;
		}
		return false;
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
