package controller;
import model.*;

import java.util.ArrayList;

import dao.*;
public class Quali_NivelController {
	public Quali_Nivel cadastrarQuali_Nivel(Nivel nivel, Qualificacao qualificacao) throws ExceptionDao{
		if(nivel != null && qualificacao != null) {
			Quali_Nivel qn = new Quali_Nivel(nivel, qualificacao);
			qn.cadastrarQuali_Nivel(qn);
			
			return qn;
		}
		return null;
	}
	public ArrayList<Nivel> getQualificacao_Nivel(Qualificacao q) throws Exception{
		return new Quali_Nivel().getQualificacao_Nivel(q);
	}
	public int buscarCodigo(Qualificacao q, Nivel n) throws ExceptionDao{
		return new Quali_Nivel().buscarCodigo(q,n);
	}
}
