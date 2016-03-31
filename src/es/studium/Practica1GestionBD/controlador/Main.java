package es.studium.Practica1GestionBD.controlador;

import es.studium.Practica1GestionBD.modelo.AyudanteBD;
import es.studium.Practica1GestionBD.vista.Vista;

public class Main {

	public static void main(String[] args) {
		
		Vista vista= new Vista();
		AyudanteBD ayudantebd=new AyudanteBD();
		Controlador controlador= new Controlador(vista,ayudantebd);
		vista.conectaControlador(controlador);
	}
}
