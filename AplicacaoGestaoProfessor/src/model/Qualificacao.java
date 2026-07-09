package model;
import java.util.ArrayList;
import dao.*;
public class Qualificacao {
	private int codigo;
	private String titulo;
	private ArrayList<Classificacao> classificacoes;
	private ArrayList<Quali_Nivel> quali_nivel;
	private ArrayList<Quali_modulo> quali_modulo;
	private Coordenador coordenador;

	public Qualificacao() {

	}
	public Qualificacao(String titulo, Coordenador coordenador) {
		this.titulo = titulo;
		this.coordenador= coordenador;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Coordenador getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}
	public void cadastrarQualificacao(Qualificacao qc) throws ExceptionDao{
		new QualificacaoDao().cadastrarQualificacao(qc);
	}
	public ArrayList<Qualificacao> comboQualificacao() throws ExceptionDao{
		return new QualificacaoDao().comboQualificacao();
	}
	public ArrayList<Qualificacao> listarQualificacao(String titulo) throws ExceptionDao{
		return new QualificacaoDao().listarQualificacao(titulo);
	}
	public void atualizarQualificacao(Qualificacao qc) throws ExceptionDao{
		new QualificacaoDao().atualizarQualificacao(qc);
	}
	public void apagarQualificacao(Qualificacao qc) throws ExceptionDao{
		new QualificacaoDao().apagarQualificacao(qc);
	}
	@Override
	public String toString() {
		return "Qualificacao [codigo=" + codigo + ", titulo=" + titulo + "]";
	}
}