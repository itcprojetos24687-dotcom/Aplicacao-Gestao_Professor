package controller;

import java.util.ArrayList;
import java.util.Scanner;
import dao.*;
import dao.ExceptionDao;
import model.Licao;

public class LicaoController {
	static Scanner sc = new Scanner(System.in);

	public boolean cadastrarLicao(int horas_inicio, int horas_fim)
	throws ExceptionDao{
		if(horas_inicio > 0 && horas_fim > 0){
			Licao licao = new Licao(0, horas_inicio, horas_fim);
			licao.cadastrarLicao(licao);
			return true;
		}
		return false;
	}

	public ArrayList<Licao> listarLicao(int horas_inicio) throws ExceptionDao{
		return new Licao().listarLicao(horas_inicio);
	}

	public boolean atualizarLicao(int codigo, int horas_inicio, int horas_fim)
	throws ExceptionDao{
		if(codigo != 0 && horas_inicio > 0 && horas_fim > 0){
			Licao licao = new Licao(codigo, horas_inicio, horas_fim);
			licao.atualizarLicao(licao);
			return true;
		}
		return false;
	}

	public boolean apagarLicao(int codigo) throws ExceptionDao{
		if(codigo != 0) {
			Licao licao = new Licao();
			licao.setCodigo(codigo);
			licao.apagarLicao(licao);
			return true;
		}
		return false;
	}

	/*public static void main( String[]args) throws ExceptionDao{
		LicaoController controller = new LicaoController();
		System.out.print("Adiciona o codigo");
		int codigo = sc.nextInt();
		try {
			controller.apagarLicao(codigo);
			JOptionPane.showMessageDialog(null, "Apagado com sucesso");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no metodo apagar Licao");
			throw new ExceptionDao("Erro ao apagar Licao"+e);
		}
	}*/


}