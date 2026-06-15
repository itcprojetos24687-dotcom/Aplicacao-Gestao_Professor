package model;

public class Professor implements Comparable<Professor> {
	private int codigo;
	private String nome;
	private String apelido;
	private int  salario;
	
	
	public Professor() {
		
	}
	
	public Professor(int codigo, String nome, String apelido, int salario ) {
		this.codigo = codigo;
		this.nome = nome;
		this.apelido = apelido;
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

	public double getSalario() {
		return salario;
	}


	public String toString() {
		return codigo + "-" + nome + "-" + apelido + "-" + salario;
	}

	public int compareTo(Professor professor) {
		if (this.codigo > professor.codigo)
			return 1;
		if (this.codigo < professor.codigo)
			return -1;
		return 0;

}
}
