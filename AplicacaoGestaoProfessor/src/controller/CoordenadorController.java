package controller;
import dao.ExceptionDao;
import model.Coordenador;
import java.util.ArrayList;

public class CoordenadorController {
	public void cadastrarCoordenador(String nome, String apelido, String email, String genero, String estadoCivil,int contacto, double salario) {
		if(nome != null && nome.length()>0 && nome.matches("[a-zA-Z]+")&& apelido != null && apelido.matches("[a-zA-z]+") && apelido.length() > 0 && email != null && email.length() > 0 && contacto > 0) {
			Coordenador coordenador = new Coordenador();
			//oordenador.setFormador(f)
			coordenador.cadastrarCoordenador(coordenador);
		}
	}
}
