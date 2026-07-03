package model;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;
import dao.*;
import dao.ExceptionDao;
import dao.FormandoDao;
public class Inscricao {
	private int codigo;
	private Modulo modulo;
	private int data_inscricao;
	private String semestre;
	private ArrayList<Formando> formandos;
	
	public Inscricao() {
		
	}
	
	public Inscricao(int data_inscricao, String semestre) {
		this.data_inscricao = data_inscricao;
		this.semestre = semestre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getData_inscricao() {
		return data_inscricao;
	}
	
	public void setData_inscricao(int data_inscricao) {
		this.data_inscricao = data_inscricao;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public void cadastrarInscricao(Inscricao inscricao) throws ExceptionDao{
		new InscricaoDao().cadastrarInscricao(inscricao);
	}
	public ArrayList<Inscricao>listarInscricao(String semestre) throws ExceptionDao{
		return new InscricaoDao().listarInscricao(semestre);
	}
	public void atualizarInscricao(Inscricao inscricao) throws ExceptionDao{
		new InscricaoDao().atualizarInscricao(inscricao);
	}
	public void apagarInscricao(Inscricao inscricao) throws ExceptionDao{
		JOptionPane.showMessageDialog(null, "Chamado no model com sucesso");
		new InscricaoDao().apagarInscricao(inscricao);
	}

	@Override
	public String toString() {
		return "Inscricao [codigo=" + codigo + ", data_inscricao=" + data_inscricao + ", semestre=" + semestre + "]";
	}
	
}
