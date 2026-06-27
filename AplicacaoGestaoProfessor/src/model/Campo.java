package model;
import java.util.ArrayList;
import dao.*;
public class Campo {
	private int codigo;
	private String nome;
	private ArrayList<Classificacao> classificacoes;
	
	public Campo() {
		
	}
	public Campo(String nome) {
		this.nome = nome;
	}
	public void cadastrarCampo(Campo campo) throws ExceptionDao{
		new CampoDao().cadastrarCampo(campo);
	}
	public ArrayList<Campo> listarCampo(String nome) throws ExceptionDao{
		return new CampoDao().listarCampo(nome);
	}
	public void atualizarCampo(Campo campo) throws ExceptionDao{
		new CampoDao().atualizarCampo(campo);
	}
	public void apagarCampo(Campo campo)throws ExceptionDao{
		new CampoDao().apagarCampo(campo);
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
	
}
