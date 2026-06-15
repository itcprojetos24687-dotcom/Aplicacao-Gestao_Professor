package model;

public class Salario implements Comparable<Salario>  {
	private int codigo_salario;
	private int num_horas;
	private int valor_horas;
	
	public Salario() {
		
	}
	
	public Salario(int codigo_salario, int num_horas, int valor_horas) {
		this.codigo_salario =  codigo_salario;
		this.num_horas = num_horas;
		this.valor_horas =  valor_horas;
	}
	
	public int getCodigo_salario() {
		return codigo_salario;
	}
	public void setCodigo_salario(int codigo_salario) {
		this.codigo_salario = codigo_salario;
	}
	public int getNum_horas() {
		return  num_horas;
	}
	public void setNum_horas(int num_horas) {
		this.num_horas =  num_horas;
	}
	public int getValor_horas() {
		return valor_horas;
	}
	public void setValor_horas(int valor_horas) {
		this.valor_horas = valor_horas;
	}
	public int compareTo(Salario salario) {
		if (this.codigo_salario > salario.codigo_salario)
			return 1;
		if (this.codigo_salario < salario.codigo_salario)
			return -1;
		return 0;
	}
}
