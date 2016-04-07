package es.studium.Practica1GestionBD.controlador;

import es.studium.Practica1GestionBD.modelo.AyudanteBD;
import es.studium.Practica1GestionBD.vista.MenuPrincipal;
import es.studium.Practica1GestionBD.vista.VistaUsuario;

public class Main {

	public static void main(String[] args) {
		
		MenuPrincipal vistamenu = new MenuPrincipal();
		AyudanteBD ayudantebd=new AyudanteBD();
		VistaUsuario vistausuario=new VistaUsuario();
		Controlador controlador= new Controlador(vistamenu,ayudantebd,vistausuario);
		vistamenu.conectaControlador(controlador);
		
	}
}
