package controller;
import dao.*;
import model.Usuario;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import servico.UsuarioServico;

public class UsuarioController {
	public boolean login(String username,String password)throws ExceptionDao {
		if(username != null && username.length()> 0 && password != null && password.length()>0) {
			UsuarioServico us = new UsuarioServico();
			JOptionPane.showMessageDialog(null, "username e password preenchidos com sucesso");
			Usuario u = us.login(username, password);
			
			if(u != null) {
				JOptionPane.showMessageDialog(null, "Retornou username e password com sucesso");
				return true;				
			}
		}
		JOptionPane.showMessageDialog(null, "Nao retornou username e password no controller");
		return false;
	}
}
