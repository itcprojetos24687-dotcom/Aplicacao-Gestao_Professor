package controller;
import model.Inscricao;
import model.Modulo;
import model.Formando;
import dao.ExceptionDao;
import java.util.ArrayList;

public class InscricaoController {

	public boolean cadastrarInscricao(Formando formando, Modulo modulo, String semestre, String data_inscricao)
	throws ExceptionDao{
		if(formando != null && modulo != null && semestre != null && semestre.length() > 0
				&& data_inscricao != null && data_inscricao.length() > 0){
			Inscricao inscricao = new Inscricao(formando, modulo, semestre, data_inscricao);
			inscricao.cadastrarInscricao(inscricao);
			return true;
		}
		return false;
	}

	public ArrayList<Inscricao> listarInscricao(String semestre) throws ExceptionDao{
		return new Inscricao().listarInscricao(semestre);
	}

	public boolean atualizarInscricao(int codigo, Formando formando, Modulo modulo, String semestre, String data_inscricao)
	throws ExceptionDao{
		if(codigo != 0 && formando != null && modulo != null && semestre != null && semestre.length() > 0
				&& data_inscricao != null && data_inscricao.length() > 0){
			Inscricao inscricao = new Inscricao(formando, modulo, semestre, data_inscricao);
			inscricao.setCodigo(codigo);
			inscricao.atualizarInscricao(inscricao);
			return true;
		}
		return false;
	}

	public boolean apagarInscricao(int codigo) throws ExceptionDao{
		if(codigo != 0) {
			Inscricao inscricao = new Inscricao();
			inscricao.setCodigo(codigo);
			inscricao.apagarInscricao(inscricao);
			return true;
		}
		return false;
	}
}