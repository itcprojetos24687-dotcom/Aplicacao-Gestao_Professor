package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.*;

public class Formador {
	private int codigo;
	private String nome;
	private String apelido;
	private String email;
	private String genero;
	private String estadoCivil;
	private int contacto;
	private double salario;
	private int valor_horas;
	private int horas_mes;
	private ArrayList<Licao> licoes;
	
	public Formador() {}
	public Formador(String nome, String apelido, String email, String genero, String estadoCivil,int contacto,int valor_horas ,int horas_mes,double salario) {
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.genero = genero;
		this.estadoCivil = estadoCivil;
		this.contacto = contacto;
		this.valor_horas = valor_horas;
		this.horas_mes = horas_mes;
		this.salario = salario;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public int getContacto() {
		return contacto;
	}
	public void setContacto(int contacto) {
		this.contacto = contacto;
	}
	public double getSalario() {
		return this.salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public static double calcularSalario(int valor_hora, int num_horas) {
		return	valor_hora * num_horas; 
	}
	
	public void cadastrarFormador(Formador formador) throws ExceptionDao{
		new FormadorDao().cadastrarFormador(formador);
	}
	public int getValor_horas() {
		return valor_horas;
	}
	public void setValor_horas(int valor_horas) {
		this.valor_horas = valor_horas;
	}
	public int getHoras_mes() {
		return horas_mes;
	}
	public void setHoras_mes(int horas_mes) {
		this.horas_mes = horas_mes;
	}
	public ArrayList<Formador> listarFormador(String nome) throws ExceptionDao{
		return new FormadorDao().listarFormador(nome);
	}
	public void atualizarFormador(Formador formador) throws ExceptionDao{
		new FormadorDao().atualizarFormador(formador);
	}
	public void apagarFormador(Formador formador) throws ExceptionDao{
		JOptionPane.showMessageDialog(null, "Chamado no model com sucesso");
		new FormadorDao().apagarFormador(formador);
	}
	@Override
	public String toString() {
		return  nome + " "+apelido;
	}
	
	
	
	
}
