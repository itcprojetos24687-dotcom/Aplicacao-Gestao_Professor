package servico;
import model.Formador;
import dao.*;
public class FormadorServico {
	private Formador formador;
	public FormadorServico(Formador formador) {
		this.formador = formador;
	}
	public void salvar(Formador formador) throws ExceptionDao{
		formador.cadastrarFormador(formador);
		
		
	}
}
