package model;

public class Modulo implements Comparable<Modulo> {
	private int codigo_modulo;
	private String nome_modulo;
	private int num_aprendi;
	private int codigo_prof;
	
	public Modulo() {
		
	}
	
	public Modulo(int codigo_modulo, String nome_modulo, int num_aprendi,  int codigo_prof) {
		this.codigo_modulo = codigo_modulo;
		this.nome_modulo = nome_modulo;
		this.num_aprendi = num_aprendi;
		this.codigo_prof = codigo_prof;
	}
	public int getCodigo_modulo() {
		return codigo_modulo;
	}
	public void sentCodigo_modulo(int codigo_modulo) {
		this.codigo_modulo = codigo_modulo;
	}
	public String getNome_modulo() {
		return nome_modulo;
	}
	public void setNome_modulo(String nome_modulo) {
		this.nome_modulo = nome_modulo;
	}
	public int getNum_aprendi() {
		return num_aprendi;
	}
	public void setNum_aprendi(int num_aprendi) {
		this.num_aprendi = num_aprendi;
	}
	public int getCodigo_prof() {
		return codigo_prof;
	}
	public void setCodigo_prof(int codigo_prof) {
		this.codigo_prof = codigo_prof;
	}
	public int compareTo(Modulo modulo) {
		if (this.codigo_modulo > modulo.codigo_modulo)
			return 1;
		if (this.codigo_modulo < modulo.codigo_modulo)
			return -1;
		return 0;

}
}
