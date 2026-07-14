package controller;

import model.Coordenador;
import model.Formador;
import dao.ExceptionDao;
import java.util.ArrayList;

public class CoordenadorController {

	public void salvarCoordenador(Formador f,int codigoFormador)throws ExceptionDao{
		if(new Coordenador().isCoordenador(codigoFormador)) {
			atualizarCoordenador(f,codigoFormador);
		}
		else {
			cadastrarCoordenador(f);
		}
	}
	public boolean cadastrarCoordenador(Formador formador) throws ExceptionDao {
		if (formador != null && formador.getCodigo() != 0) {
			Coordenador coordenador = new Coordenador();
			coordenador.setFormador(formador);
			coordenador.cadastrarCoordenador(coordenador);
			return true;
		}
		return false;
	}

	public ArrayList<Coordenador> listarCoordenador() throws ExceptionDao {
		return new Coordenador().listarCoordenador();
	}
	public boolean isCoordenador( int codigoFormador) throws ExceptionDao{
		if(codigoFormador >0) {
			return new Coordenador().isCoordenador(codigoFormador);
			
		}
		return false;
	}
	public void atualizarCoordenador(Formador f, int codigoFormador) throws ExceptionDao{
		if(f != null && codigoFormador >0) {
			Coordenador c = new Coordenador(f);
			c.atualizarCoordenador(c, codigoFormador);
		}
	}

//	public boolean atualizarCoordenador(int codigo, Formador formador) throws ExceptionDao {
//		if (codigo != 0 && formador != null && formador.getCodigo() != 0) {
//			Coordenador coordenador = new Coordenador();
//			coordenador.setCodigo(codigo);
//			coordenador.setFormador(formador);
//			coordenador.atualizarCoordenador(coordenador);
//			return true;
//		}
//		return false;
//	}
//
	public boolean apagarCoordenador(int codigo) throws ExceptionDao {
		if (codigo != 0) {
			new Coordenador().apagarCoordenador(codigo);
			return true;
		}
		return false;
	}

	/*public static void main(String[] args) throws ExceptionDao {
		CoordenadorController controller = new CoordenadorController();
		System.out.print("Adiciona o codigo: ");
		int codigo = sc.nextInt();
		try {
			controller.apagarCoordenador(codigo);
			JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no metodo apagar Coordenador");
			throw new ExceptionDao("Erro ao apagar Coordenador" + e);
		}
	}*/

}