package controller;

import model.Formando;
import dao.ExceptionDao;
import java.util.ArrayList;
import java.util.Scanner;

public class FormandoController {
    static Scanner sc = new Scanner(System.in);
    
    public boolean cadastrarFormando(String nome, String apelido, int contacto, String email, String bi) throws ExceptionDao {
        if (nome != null && nome.length() > 0 && nome.matches("[a-zA-Z]+") && 
            apelido != null && apelido.matches("[a-zA-Z]+") && apelido.length() > 0 && 
            bi != null && email.length() > 0 && contacto > 0) {
            
            Formando formando = new Formando(nome, apelido, contacto, email, bi);
            formando.cadastrarFormando(formando);
            return true;
        }
        return false;
    }
    
    public ArrayList<Formando> listarFormando(String nome) throws ExceptionDao {
        return new Formando().listarFormando(nome);
    }
    
    public boolean atualizarFormando(String nome, String apelido, int contacto, String email, String bi, int codigo) throws ExceptionDao {
        if (nome != null && nome.length() > 0 && nome.matches("[a-zA-Z]+") && codigo != 0 && 
            apelido != null && apelido.matches("[a-zA-Z]+") && apelido.length() > 0 && 
            email != null && email.length() > 0 && contacto > 0) {
            
            Formando formando = new Formando(nome, apelido, contacto, email, bi);
            formando.setCodigo(codigo);
            formando.atualizarFormando(formando);
            return true;
        }
        return false;
    }
    
    public boolean apagarFormando(int codigo) throws ExceptionDao {
        if (codigo != 0) {
            Formando formando = new Formando();
            formando.setCodigo(codigo);
            formando.apagarFormando(formando);
            return true;
        }
        return false;
    }
}