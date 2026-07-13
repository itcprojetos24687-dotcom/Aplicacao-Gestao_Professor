package model;
import java.util.ArrayList;

import dao.*;
public class Quali_Nivel {
	private int codigo;
	private Nivel nivel;
	private Qualificacao qualificacao;
	
	public Quali_Nivel(Nivel nivel, Qualificacao qualificacao) {
		this.nivel = nivel;
		this.qualificacao = qualificacao;
	}
	public Quali_Nivel() {
		// TODO Auto-generated constructor stub
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public Qualificacao getQualificacao() {
		return qualificacao;
	}
	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Quali_Nivel cadastrarQuali_Nivel(Quali_Nivel qn) throws ExceptionDao{
		return new Quali_NivelDao().cadastrarQuali_Nivel(qn);
	}
	
	@Override
	public String toString() {
		return "Quali_Nivel [codigo=" + codigo + "]";
	}
	public ArrayList<Nivel> getQualificacao_Nivel(Qualificacao q) throws ExceptionDao{
		return new Quali_NivelDao().getQualificacao_Nivel(q);
		
	}
	public int buscarCodigo(Qualificacao q, Nivel n)throws ExceptionDao {
		return new Quali_NivelDao().buscarCodigo(q,n);
	}
	
	
}
