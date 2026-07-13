package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import dao.FormandoDao;
import dao.ExceptionDao;

public class Formando {
    private int codigo;
    private String nome;
    private String apelido;
    private int contacto;
    private String email;
    private String bi;
    private ArrayList<Object> Inscricoes; 
    private ArrayList<Object> matricula;
    
    public Formando() {
    }
    
    public Formando(String nome, String apelido, int contacto, String email, String bi) {
        this.nome = nome;
        this.apelido = apelido;
        this.contacto = contacto;
        this.email = email;
        this.bi = bi;
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }
    
    public void cadastrarFormando(Formando formando) throws ExceptionDao {
        new FormandoDao().cadastrarFormando(formando);
    }
    public ArrayList<Formando> comboFormando() throws ExceptionDao {
    	
        return new FormandoDao().comboFormando();
    }
    
    public ArrayList<Formando> listarFormando(String nome) throws ExceptionDao {
        return new FormandoDao().listarFormando(nome);
    }
    
    public void atualizarFormando(Formando formando) throws ExceptionDao {
        new FormandoDao().atualizarFormando(formando);
    }
    
    public void apagarFormando(Formando formando) throws ExceptionDao {
        
        new FormandoDao().apagarFormando(formando);
    }

    @Override
    public String toString() {
        return nome + " "+ apelido ;
    }
}