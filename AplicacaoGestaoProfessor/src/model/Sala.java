package model;
import dao.*;
import java.util.ArrayList;
public class Sala {
	private int codigo;
	private String designacao;
	private String tipo;
	private ArrayList<Licao> licoes;
	
	public Sala() {
		
	}
	public Sala(String designacao, String tipo) {
		this.designacao = designacao;
		this.tipo = tipo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void cadastrarSala(Sala sala) throws ExceptionDao{
		new SalaDao().cadastrarSala(sala);
	}
	public ArrayList<Sala> listarSala(String designacao) throws ExceptionDao{
		return new SalaDao().listarSala(designacao);
	}
	public void atualizarSala(Sala sala)throws ExceptionDao{
		new SalaDao().atualizarSala(sala);
	}
	public void apagarSala(Sala sala)throws ExceptionDao{
		new SalaDao().apagarSala(sala);
}
}
