package controller;
import model.Formador;
import model.Sala;
import java.util.ArrayList;
import dao.ExceptionDao;
public class SalaController {
	public boolean cadastrarSala( String designacao, String tipo_sala) throws ExceptionDao{
		if(designacao != null && designacao.length()>0 && tipo_sala != null && tipo_sala.length()>0) {
			Sala sala = new Sala(designacao,tipo_sala);
			sala.cadastrarSala(sala);
			return true;
		}
		return false;
	}
	public ArrayList<Sala> listarSala(String designacao) throws ExceptionDao{
		return new Sala().listarSala(designacao);
	}
	public boolean atualizarSala(int codigo,String designacao, String tipo_sala)
			throws ExceptionDao{
				if( codigo>0 && designacao != null && designacao.length()>0  && tipo_sala !=  null && tipo_sala.length()>0 ){
					Sala sala = new Sala(designacao, tipo_sala);
					sala.setCodigo(codigo);
					sala.atualizarSala(sala);
					return true;
				}
				return false;
				}
	public boolean apagarsala(int codigo) throws ExceptionDao{

		if(codigo != 0) {
			Sala sala= new Sala();
			sala.getCodigo();
			sala.apagarSala(sala);
			return true;
			
		}
		return false;
	}
}
