package controller;
import java.io.*;

import java.util.Scanner;
import model.*;
public class FicheiroDefault {
	public static void criarFicheiro(Usuario u) throws Exception{
		String Nome = u.getNome();
		String username = u.getUsername();
		String perfil = u.getPerfil().getNome();
		String senha = u.getPassword();
		String tituloficheiro = Nome;
		
		File file = new File("senhasDefault",tituloficheiro+".txt");
		if(file.exists()) {
			file.mkdirs();
		}
		FileWriter ficheiro = new FileWriter(file);
		BufferedWriter abrir = new BufferedWriter(ficheiro);
		abrir.write("Nome: "+Nome);
		abrir.newLine();
		abrir.write("Username: "+username);
		abrir.newLine();
		abrir.write("Perfil: "+perfil);
		abrir.newLine();
		abrir.write("Senha : "+senha);
		abrir.newLine();
		abrir.close();
		ficheiro.close();
	
	}
	public static boolean apagarFicheiro(String nome) throws Exception{
		File file = new File("senhasDefault", nome+".txt");
		boolean sucesso = file.delete();
		if(sucesso) {
			return true;
		}
		else {
			return false;
		}
		
	}
//	public static void atualizarFicheiro(Usuario u)throws Exception{
//		String Nome = u.getNome_completo();
//		String tituloficheiro = Nome;
//		String username = u.getUsername();
//		String perfil = u.getPerfil().getNome();
//		String senha = u.getPassword();
//		File file = new File("senhasDefault",tituloficheiro+"txt");
//		if(file.exists()) {
//			file.mkdirs();
//		}
//		FileWriter ficheiro = new FileWriter(file);
//		BufferedWriter abrir = new BufferedWriter(ficheiro);
//		abrir.write("Nome: "+Nome);
//		abrir.newLine();
//		abrir.write("Username: "+username);
//		abrir.newLine();
//		abrir.write("Perfil: "+perfil);
//		abrir.newLine();
//		abrir.write("Senha : "+senha);
//		abrir.newLine();
//		abrir.close();
//		ficheiro.close();
//		
//		
//	}
	/*public static void main(String[]args)throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.print("Nome: ");
		String Nome = sc.next();
		System.out.print("Username: ");
		String username = sc.next();
		System.out.print("Perfil: ");
		String perfil = sc.next();
		System.out.print("Senha: ");
		String senha = sc.next();
		String tituloficheiro = sc.next();
		FileWriter ficheiro = new FileWriter(tituloficheiro+".txt",true);
		BufferedWriter abrir = new BufferedWriter(ficheiro);
		abrir.write("Nome: "+Nome);
		abrir.newLine();
		abrir.write("Username: "+username);
		abrir.newLine();
		abrir.write("Perfil: "+perfil);
		abrir.newLine();
		abrir.write("Senha : "+senha);
		abrir.close();
		ficheiro.close();
		
		
	
	}*/
}
