package model;
import dao.*;
public class Logs {
	private int codigo;
	private Usuario usuario;
	private String acao;
	private String descricao;
	
	public Logs(String acao,String descricao,Usuario usuario) {
		this.acao = acao;
		this.descricao = descricao;
		this.usuario = usuario;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void salvar(Logs log) throws ExceptionDao{
		new LogDao().salvar(log);
	}
	
}
