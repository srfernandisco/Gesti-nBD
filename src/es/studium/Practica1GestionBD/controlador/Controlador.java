package es.studium.Practica1GestionBD.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import es.studium.Practica1GestionBD.modelo.AyudanteBD;
import es.studium.Practica1GestionBD.modelo.Usuario;
import es.studium.Practica1GestionBD.vista.Login;
import es.studium.Practica1GestionBD.vista.MenuPrincipal;
import es.studium.Practica1GestionBD.vista.VistaUsuario;

public class Controlador implements ActionListener {

	private MenuPrincipal vistamenu;
	private AyudanteBD ayudantebd;
	private Usuario usuarioActual;

	private static final String EMPTY_FIELD = "" + '\u0000';

	public Controlador(MenuPrincipal vistamenu, AyudanteBD ayudantebd, VistaUsuario vistausuario) {
		this.vistamenu = vistamenu;
		this.ayudantebd = ayudantebd;
	}

	/**
	 * Se ejecutan los eventos de la vista con comandos definidos
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		switch (comando) {
		// Salir del programa desde el menu principal
		case MenuPrincipal.COMANDO_SALIR:
			cerrarPrograma();
			break;
		// hacer visible el menu de usuarios al pulsar usuarios
		case MenuPrincipal.GESTION_USUARIO:
			if(usuarioActual.getTipoUsuario().equals("User"))		
					vistamenu.mostrarPanel("VistaUsuario",false);
			else
				vistamenu.mostrarPanel("VistaUsuario",true);
			
			break;

		// borrar usuario
		case VistaUsuario.CM_BORRAR_USER:

			break;

		// Loguerase
		case Login.COMANDO_ACEPTAR:
			login();
			break;
		// limpiar logueo
		case Login.COMANDO_LIMPIAR:
			limpiarLogin();
			break;
			
		case VistaUsuario.CM_NUEVO_USER:
			crearUsuario();
			break;
		}

	}

	private void crearUsuario( ) {
		// comprobamos el rol del usuario		
		
		String nombre,passwd,tipo;
		Usuario usuario;
		
		
		
		nombre=vistamenu.getPanelVistaUsuario().getTxtNombre().getText();
		passwd = vistamenu.getPanelVistaUsuario().getTxtContrasena().getText();
		tipo = vistamenu.getPanelVistaUsuario().getChoTipoUsuario().getSelectedItem();
		
		usuario = new Usuario(0,nombre,passwd,tipo);
		
		System.out.println(usuario);
		ayudantebd.registrarUsuario(usuario);
		
	}

	private void limpiarLogin() {
		vistamenu.getLogin().getTextusuario().setText(EMPTY_FIELD);
		vistamenu.getLogin().getTextcontrasena().setText(EMPTY_FIELD);
	}

	private void login() {
		Usuario usuario;
		String user = vistamenu.getLogin().getTextusuario().getText();
		String passwd = vistamenu.getLogin().getTextcontrasena().getText();
		usuario = ayudantebd.login(user, passwd);
		if (usuario.getId()!=0) {
			usuarioActual = usuario;
			vistamenu.getLogin().cerrarLogin();
			vistamenu.haceVisibleMenu(true,usuarioActual.getTipoUsuario());
			
		} else {
			// ventana de aviso de diálogo SWING
			JOptionPane.showMessageDialog(vistamenu, "Usuario/Contraseña incorrecto", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
		

	}

	private void cerrarPrograma() {
		System.exit(0);
	}
	


}
