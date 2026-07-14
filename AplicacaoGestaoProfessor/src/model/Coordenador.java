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
	public boolean isCoordenador(int codigoFormador)throws ExceptionDao {
		
		return new CoordenadorDao().isCoordenador(codigoFormador);
	}
	public void apagarCoordenador(int codigo) throws ExceptionDao{
		 new CoordenadorDao().apagarCoordenador(codigo);
		
	}
	public void atualizarCoordenador(Coordenador c, int codigoFormador)throws ExceptionDao {
		new CoordenadorDao().atualizarCoordenador(c, codigoFormador);
		
	}
	public String toString() {
		return formador.getNome();
	}
}
