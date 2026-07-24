package model;
import dao.ExceptionDao;
import dao.InscricaoDao;
import java.util.ArrayList;

public class Inscricao {
	private int codigo;
	private Formando formando;
	private Modulo modulo;
	private String semestre;
	private String data_inscricao;
	public Inscricao() {

	}

	public Inscricao(Formando formando, Modulo modulo, String semestre, String data_inscricao) {
		this.formando = formando;
		this.modulo = modulo;
		this.semestre = semestre;
		this.data_inscricao = data_inscricao;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Formando getFormando() {
		return formando;
	}
	public void setFormando(Formando formando) {
		this.formando = formando;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getData_inscricao() {
		return data_inscricao;
	}
	public void setData_inscricao(String data_inscricao) {
		this.data_inscricao = data_inscricao;
	}

	public void cadastrarInscricao(Inscricao inscricao) throws ExceptionDao{
		new InscricaoDao().cadastrarInscricao(inscricao);
	}
	public ArrayList<Inscricao> listarInscricao(String semestre) throws ExceptionDao{
		return new InscricaoDao().listarInscricao(semestre);
	}
	public void atualizarInscricao(Inscricao inscricao) throws ExceptionDao{
		new InscricaoDao().atualizarInscricao(inscricao);
	}
	public void apagarInscricao(Inscricao inscricao) throws ExceptionDao{
		new InscricaoDao().apagarInscricao(inscricao);
	}

	@Override
	public String toString() {
		return "Inscricao [codigo=" + codigo + ", semestre=" + semestre + ", data_inscricao=" + data_inscricao + "]";
	}
}