package controller;

import model.*;

import dao.ExceptionDao;
import java.util.ArrayList;

public class ClassificacaoController {

	public boolean cadastrarClassificacao(Campo campo, Qualificacao qualificacao) throws ExceptionDao {
		if (campo != null && qualificacao != null) {
			Classificacao classificacao = new Classificacao();
			classificacao.setCampo(campo);
			classificacao.setQualificacao(qualificacao);
			classificacao.cadastrarClassificacao(classificacao);
			return true;
		}
		return false;
	}

	public ArrayList<Classificacao> listarClassificacao(Campo campo) throws ExceptionDao {
		return new Classificacao().listarClassificacao(campo);
	}

	public boolean atualizarClassificacao(int codigo, Campo campo) throws ExceptionDao {
		if (codigo != 0 && campo != null) {
			Classificacao classificacao = new Classificacao();
			classificacao.setCodigo(codigo);
			classificacao.setCampo(campo);
			classificacao.atualizarClassificacao(classificacao);
			return true;
		}
		return false;
	}

	public boolean apagarClassificacao(int codigo) throws ExceptionDao {
		if (codigo != 0) {
			Classificacao classificacao = new Classificacao();
			classificacao.setCodigo(codigo);
			classificacao.apagarClassificacao(classificacao);
			return true;
		}
		return false;
	}
}