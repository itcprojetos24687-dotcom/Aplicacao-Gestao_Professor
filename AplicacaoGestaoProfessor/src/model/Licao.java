package model;
import java.util.ArrayList;
import dao.*;
import javax.swing.JOptionPane;
import dao.ExceptionDao;
public class Licao {
	private int codigo;
	private Modulo modulo;
	private Sala sala;
	private Formador formador;
	private Turma turma;
	private String date;
	private int horas_inicio;
	private int horas_fim;
	public Licao() {
	}
	public Licao(int codigo, int horas_inicio, int horas_fim) {
	    this.codigo = codigo;
	    this.horas_inicio = horas_inicio;
	    this.horas_fim = horas_fim;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getHoras_inicio() {
		return horas_inicio;
	}
	public void setHoras_inicio(int horas_inicio) {
		this.horas_inicio= horas_inicio;
	}
	public int getHoras_fim(){
		return horas_fim;
	}
	public void setHoras_fim(int horas_fim) {
		this.horas_fim = horas_fim;
	}
	public void cadastrarLicao(Licao licao) throws ExceptionDao{
		new LicaoDao().cadastrarlicao(licao);
	}
	public ArrayList<Licao> listarLicao(int horas_inicio) throws ExceptionDao{
		return new LicaoDao().listarLicao(horas_inicio);
	}
	public void atualizarLicao(Licao licao) throws ExceptionDao{
		new LicaoDao().atualizarLicao(licao);
	}
	public void apagarLicao(Licao licao) throws ExceptionDao{
		JOptionPane.showMessageDialog(null, "Chamado no model com sucesso");
		new LicaoDao().apagarLicao(licao);
	}
	@Override
	public String toString() {
		return " Licao [codigo=" + codigo + ", horas_inicio=" + horas_inicio + ",horas_fim =" + horas_fim
				+ "]";
	}
}