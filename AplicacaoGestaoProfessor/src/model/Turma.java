package model;

import java.util.ArrayList;
import dao.*;

public class Turma {
	private int codigo;
	private String nome;
	private int ano_ingresso;
	private String turno;
	private ArrayList<Licao> licoes;
	private Diretor_Turma diretor_turma;
	private Qualificacao qualificacao;
	private Quali_Nivel quali_nivel;

	public Quali_Nivel getQuali_nivel() {
		return quali_nivel;
	}

	public void setQuali_nivel(Quali_Nivel quali_nivel) {
		this.quali_nivel = quali_nivel;
	}

	public Qualificacao getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}

	public Turma() {

	}

	public Turma(String nome, int ano_ingresso, String turno) {
		this.nome = nome;
		this.ano_ingresso = ano_ingresso;
		this.turno = turno;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAno_ingresso() {
		return ano_ingresso;
	}
	public void setAno_ingresso(int ano_ingresso) {
		this.ano_ingresso = ano_ingresso;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public ArrayList<Licao> getLicoes() {
		return licoes;
	}
	public void setLicoes(ArrayList<Licao> licoes) {
		this.licoes = licoes;
	}
	public Diretor_Turma getDiretor_turma() {
		return diretor_turma;
	}
	public void setDiretor_turma(Diretor_Turma diretor_turma) {
		this.diretor_turma = diretor_turma;
	}

	public void cadastrarTurma(Turma turma) throws ExceptionDao {
		new TurmaDao().cadastrarTurma(turma);
	}

	public ArrayList<Turma> listarTurma(String nome) throws ExceptionDao {
		return new TurmaDao().listarTurma(nome);
	}
//
//	public void atualizarTurma(Turma turma) throws ExceptionDao {
//		new TurmaDao().atualizarTurma(turma);
//	}
//
	public void apagarTurma(int codigo) throws ExceptionDao {
		new TurmaDao().apagarTurma(codigo);
	}

	@Override
	public String toString() {
		return  nome;
	}

	public void atualizarTurma(Turma turma) throws ExceptionDao{
		new TurmaDao().atualizarTurma(turma);
		
	}

}