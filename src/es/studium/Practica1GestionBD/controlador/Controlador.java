package es.studium.Practica1GestionBD.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import es.studium.Practica1GestionBD.modelo.AyudanteBD;
import es.studium.Practica1GestionBD.vista.Login;
import es.studium.Practica1GestionBD.vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	private AyudanteBD ayudantebd;
	
	private static final String EMPTY_FIELD = "" + '\u0000';

	public Controlador(Vista vista,AyudanteBD ayudantebd) {
		this.vista = vista;
		this.ayudantebd=ayudantebd;
	}

	/**
	 * Se ejecutan los eventos de la vista con comandos definidos
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		

		switch (comando) {
		case Vista.COMANDO_SALIR:
			cerrarPrograma();
			break;
		case Login.COMANDO_ACEPTAR:
			login();
			break;
	
		case Login.COMANDO_LIMPIAR:
			limpiarLogin();
			break;
		}

	}

	private void limpiarLogin() {
		vista.getLogin().getTextusuario().setText(EMPTY_FIELD);
		vista.getLogin().getTextcontrasena().setText(EMPTY_FIELD);
	}

	private void login() {
		System.out.println(Login.COMANDO_ACEPTAR);
		String user = vista.getLogin().getTextusuario().getText();
		String passwd =vista.getLogin().getTextcontrasena().getText();
		if (ayudantebd.login(user, passwd)){
			vista.getLogin().cerrarLogin();
			vista.haceVisible(true);
		}
		else {
			// ventana de aviso de diálogo SWING
			JOptionPane.showMessageDialog(vista,
				    "Usuario/Contraseña incorrecto",
				    "Aviso",
				    JOptionPane.WARNING_MESSAGE);
		}
	}

	private void cerrarPrograma() {
		System.exit(0);
	}

}
