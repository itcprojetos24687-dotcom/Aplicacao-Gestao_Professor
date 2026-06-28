package model;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.*;

public class Modulo {
	private int codigo;
	private String nome;
	private int carga_horaria;
	private ArrayList<Licao> licoes;
	private ArrayList<Quali_modulo> quali_modulo;
	
	public Modulo() {
		
	}
	
	public Modulo(String nome,int carga_horaria) {
		this.nome = nome;
		this.carga_horaria = carga_horaria;
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

	public int getCarga_horaria() {
		return carga_horaria;
	}

	public void setCarga_horaria(int carga_horaria) {
		this.carga_horaria = carga_horaria;
	}
	
	public void cadastrarModulo(Modulo modulo) throws ExceptionDao{
		new ModuloDao().cadastrarModulo(modulo);
	}
	public ArrayList<Modulo> listarModulo(String nome) throws ExceptionDao{
		return new ModuloDao().listarModulo(nome);
	}
	public void atualizarModulo(Modulo modulo) throws ExceptionDao{
		new ModuloDao().atualizarModulo(modulo);
	}
	public void apagarModulo(Modulo modulo) throws ExceptionDao{
		JOptionPane.showMessageDialog(null, "Chamado no model com sucesso");
		new ModuloDao().apagarModulo(modulo);
	}
	@Override
	public String toString() {
		return "Modulo [codigo=" + codigo + ", nome=" + nome + ", carga_horaria=" + carga_horaria + "]";
	}
	
	
}

