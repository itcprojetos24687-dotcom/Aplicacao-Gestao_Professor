package model;

public class Seccao {
	private static Usuario utilizadorlogado;
	
	public static void iniciarSeccao(Usuario u) {
		utilizadorlogado = u;
	}
	public static Usuario obterUtilizador() {
		return utilizadorlogado;
	}
	public static void terminarSeccao() {
		utilizadorlogado = null;
	}
}
