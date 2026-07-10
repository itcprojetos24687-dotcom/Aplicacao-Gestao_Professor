package model;
import java.util.ArrayList;
import dao.*;
public class Coordenador{
	private Formador formador;
	
	public Coordenador() {
		
	}
	public Coordenador(Formador formador) {
		this.formador = formador;
	}
	public Formador getFormador() {
		return formador;
	}
	public void setFormador(Formador formador) {
		this.formador = formador;
	}
	public void cadastrarCoordenador(Coordenador coordenador) throws ExceptionDao {
		new CoordenadorDao().cadastrarCoordenador(coordenador);
	}
	public ArrayList<Coordenador> listarCoordenador() throws ExceptionDao{
		return new CoordenadorDao().listarCoordenador();
	}
	public String toString() {
		return formador.getNome();
	}
}
