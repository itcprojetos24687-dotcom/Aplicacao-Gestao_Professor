package model;

public class Quali_Nivel {
	private int codigo;
	private Nivel nivel;
	private Qualificacao qualificacao;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "Quali_Nivel [codigo=" + codigo + "]";
	}
	
	
}
