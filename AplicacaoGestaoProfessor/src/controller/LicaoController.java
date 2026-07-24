package controller;
import model.Licao;
import model.Modulo;
import model.Formador;
import model.Sala;
import model.Turma;
import dao.ExceptionDao;
import java.util.ArrayList;

public class LicaoController {

	public boolean cadastrarLicao(Modulo modulo, Formador formador, Sala sala, Turma turma, String data, String hora_inicio, String hora_fim)
	throws ExceptionDao{
		if(modulo != null && formador != null && sala != null && turma != null
				&& data != null && data.length() > 0
				&& hora_inicio != null && hora_inicio.length() > 0
				&& hora_fim != null && hora_fim.length() > 0){
			Licao licao = new Licao(modulo, formador, sala, turma, data, hora_inicio, hora_fim);
			licao.cadastrarLicao(licao);
			return true;
		}
		return false;
	}

	public ArrayList<Licao> listarLicao(String texto) throws ExceptionDao{
		return new Licao().listarLicao(texto);
	}

	public boolean apagarLicao(int codigo) throws ExceptionDao{
		if(codigo != 0) {
			Licao licao = new Licao();
			licao.setCodigo(codigo);
			licao.apagarLicao(licao);
			return true;
		}
		return false;
	}
}