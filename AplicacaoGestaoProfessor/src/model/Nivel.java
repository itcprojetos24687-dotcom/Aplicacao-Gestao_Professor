package model;
import java.util.ArrayList;

import dao.NivelDao;
import dao.ExceptionDao;

public class Nivel {
	private int codigo;
	private String nome;
	private ArrayList<Quali_Nivel> quali_nivel;
	
	public Nivel() {
		
	}
	public Nivel(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	public Nivel(String nome) {
		this.nome = nome;
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

	public void cadastrarNivel(Nivel nivel) throws ExceptionDao{
		new NivelDao().cadastrarNivel(nivel);
	}
	public ArrayList<Nivel> listarNivel() throws ExceptionDao{
		return new NivelDao().listarNivel();
	}
	public void atualizarNivel(Nivel nivel) throws ExceptionDao{
		new NivelDao().atualizarNivel(nivel);
	}
	public void apagarNivel(Nivel nivel)throws ExceptionDao{
		new NivelDao().apagarNivel(nivel);
	}
	@Override
	public String toString() {
		return nome;
	}
//	public ArrayList<Nivel> comboNivel() {
//		
//		return new NivelDao().comboNivel();
//	}
	
	
}

