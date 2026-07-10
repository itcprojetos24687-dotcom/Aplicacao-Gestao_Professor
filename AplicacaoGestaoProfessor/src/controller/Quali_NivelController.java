package controller;
import model.*;
import dao.*;
public class Quali_NivelController {
	public boolean  cadastrarQuali_Nivel(Nivel nivel, Qualificacao qualificacao) throws ExceptionDao{
		if(nivel != null && qualificacao != null) {
			Quali_Nivel qn = new Quali_Nivel(nivel, qualificacao);
			qn.cadastrarQuali_Nivel(qn);
			
			return true;
		}
		return false;
	}
}
