package model;

public class Modu_curso implements Comparable<Modu_curso> {
private int codigoModu_curso;
private int codigo_modulo;
private int codigo_curso;

public Modu_curso() {
	
}
public Modu_curso( int codigoModu_curso, int codigo_modulo,  int codigo_curso) {
	this.codigoModu_curso = codigoModu_curso;
	this.codigo_modulo = codigo_modulo;
	this.codigo_curso = codigo_curso;
}

public int getCodigoModu_curso() {
	return codigoModu_curso;
}
public void setCodigoModu_curso(int codigoModu_curso) {
	this.codigoModu_curso = codigoModu_curso;
}
public int getCodigo_modulo() {
	return codigo_modulo;
}
public void setCodigo_modulo(int codigo_modulo) {
	this.codigo_modulo = codigo_modulo;
}
public int getCodigo_curso() {
	return codigo_curso;
}
public void setCodigo_curso(int codigo_curso) {
	this.codigo_curso = codigo_curso;
}
public int compareTo( Modu_curso  modu_curso) {
	if (this.codigoModu_curso > modu_curso.codigoModu_curso)
		return 1;
	if (this.codigoModu_curso < modu_curso.codigoModu_curso)
		return -1;
	return 0;

}
}
