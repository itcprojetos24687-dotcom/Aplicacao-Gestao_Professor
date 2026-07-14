package model;
import dao.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.ExceptionDao;
import dao.ModuloDao;

public class Quali_modulo {
	private int codigo;
	private Modulo modulo;
	private Qualificacao qualificacao;
	private String semestre;
	private Nivel nivel;
	private int ano_curricular;
	
	public Quali_modulo() {
		
	}
	public Quali_modulo(String semestre, Modulo m, Qualificacao q) {
		this.semestre = semestre;
		modulo = m;
		qualificacao = q;
	}

	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public Qualificacao getQualificacao() {
		return qualificacao;
	}
	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getAno_curricular() {
		return ano_curricular;
	}

	public void setAno_curricular(int ano_curricular) {
		this.ano_curricular = ano_curricular;
	}
	public void cadastrarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
		new Quali_moduloDao().cadastrarQuali_modulo(quali_modulo);
	}
	public ArrayList<Quali_modulo> listarQuali_modulo(String semestre) throws ExceptionDao{
		return new Quali_moduloDao().listarQuali_modulo(semestre);
	}
	public void atualizarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
		new Quali_moduloDao().atualizarQuali_modulo(quali_modulo);
	}
	public void apagarQuali_modulo(Quali_modulo quali_modulo) throws ExceptionDao{
		JOptionPane.showMessageDialog(null, "Chamado no model com sucesso");
		new Quali_moduloDao().apagarQuali_modulo(quali_modulo);
	}
	@Override
	public String toString() {
		return "Quali_modulo [codigo=" + codigo + ", semestre=" + semestre + ", ano_curricular=" + ano_curricular + "]";
	}
	
}
