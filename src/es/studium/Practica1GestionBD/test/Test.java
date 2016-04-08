package es.studium.Practica1GestionBD.test;

import java.util.ArrayList;
import java.util.Scanner;

import es.studium.Practica1GestionBD.modelo.AyudanteBD;
import es.studium.Practica1GestionBD.modelo.Usuario;

public class Test {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String usuario , passwd, tipoUsuario;
		
		//System.out.println("# Logueando usuario");
		//System.out.println("Introduzca el usuario");
		// usuario = sc.nextLine();
		//System.out.println("Introduzca la contraseña del usuario "+usuario);
		// passwd = sc.nextLine();
		//System.out.println("Introduzca el tipo de usuario");
		// tipoUsuario = sc.nextLine();

		AyudanteBD ayudante = new AyudanteBD();
		
		//ayudante.registrarUsuario(new Usuario(0,usuario,passwd,tipoUsuario));
		
		//System.out.println("# Logueando usuario");

		//if(ayudante.borrarUsuario(new Usuario(0,usuario,"",""))) System.out.println("logueado correctamente");
		//	else System.err.println("usuario o contraseña incorrectos");

		ArrayList<Usuario> lista = ayudante.consultarTodosUsuarios();
		
		// for-each para cada elemento de la lista, lo guarda en u y lo imprimos
		for(Usuario u:lista){
			System.out.println(u);
		}
		
	}

}
