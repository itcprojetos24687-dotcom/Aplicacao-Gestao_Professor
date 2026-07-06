package controller;
import model.*;
import dao.*;
import java.util.ArrayList;
public class PerfilController {
	public ArrayList<Perfil> listarPerfil() throws ExceptionDao{
		return new Perfil().listarPerfil();
	}
	public Perfil getPerfil(String username)throws ExceptionDao{
		
		return new Perfil().getPerfil(username);
	}
}
