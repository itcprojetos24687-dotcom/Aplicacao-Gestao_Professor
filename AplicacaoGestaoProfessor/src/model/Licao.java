package model;
import dao.ExceptionDao;
import dao.LicaoDao;
import java.util.ArrayList;

public class Licao {
	private int codigo;
	private Modulo modulo;
	private Formador formador;
	private Sala sala;
	private Turma turma;
	private String data;        // yyyy-MM-dd
	private String hora_inicio; // HH:mm
	private String hora_fim;    // HH:mm

	public Licao() {

	}

	public Licao(Modulo modulo, Formador formador, Sala sala, Turma turma, String data, String hora_inicio, String hora_fim) {
		this.modulo = modulo;
		this.formador = formador;
		this.sala = sala;
		this.turma = turma;
		this.data = data;
		this.hora_inicio = hora_inicio;
		this.hora_fim = hora_fim;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public Formador getFormador() {
		return formador;
	}
	public void setFormador(Formador formador) {
		this.formador = formador;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public String getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(String hora_fim) {
		this.hora_fim = hora_fim;
	}

	public void cadastrarLicao(Licao licao) throws ExceptionDao{
		new LicaoDao().cadastrarLicao(licao);
	}
	public ArrayList<Licao> listarLicao(String texto) throws ExceptionDao{
		return new LicaoDao().listarLicao(texto);
	}
	public void apagarLicao(Licao licao) throws ExceptionDao{
		new LicaoDao().apagarLicao(licao);
	}

	@Override
	public String toString() {
		return "Licao [codigo=" + codigo + ", data=" + data + "]";
	}
}