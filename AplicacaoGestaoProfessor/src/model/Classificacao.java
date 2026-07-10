package model;
import javax.swing.JOptionPane;

import dao.*;

import java.util.ArrayList;

public class Classificacao {
	private int codigo;
	private Campo campo;
	private Qualificacao qualificacao;

	public Classificacao() {
	}

	public Classificacao( Campo campo, Qualificacao qualificacao) {
		
		this.campo = campo;
		this.qualificacao = qualificacao;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Campo getCampo() {
		return campo;
	}
	public void setCampo(Campo campo) {
		this.campo = campo;
	}
	public Qualificacao getQualificacao() {
		return qualificacao;
	}
	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}

	public void cadastrarClassificacao(Classificacao classificacao) throws ExceptionDao {
		new ClassificacaoDao().cadastrarClassificacao(classificacao);
	}

	public ArrayList<Classificacao> listarClassificacao(Campo campo) throws ExceptionDao {
		return new ClassificacaoDao().listarClassificacao(campo);
	}

	public void atualizarClassificacao(Classificacao classificacao) throws ExceptionDao {
		new ClassificacaoDao().atualizarClassificacao(classificacao);
	}

	public void apagarClassificacao(Classificacao classificacao) throws ExceptionDao {
		new ClassificacaoDao().apagarClassificacao(classificacao);
	}
}