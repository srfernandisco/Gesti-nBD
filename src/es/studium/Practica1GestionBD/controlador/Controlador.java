package es.studium.Practica1GestionBD.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import es.studium.Practica1GestionBD.modelo.AyudanteBD;
import es.studium.Practica1GestionBD.modelo.Usuario;
import es.studium.Practica1GestionBD.vista.Login;
import es.studium.Practica1GestionBD.vista.MenuPrincipal;
import es.studium.Practica1GestionBD.vista.VistaUsuario;

public class Controlador implements ActionListener {

	private MenuPrincipal vistamenu;
	private AyudanteBD ayudantebd;
	private Usuario usuarioLogueado;

	private ArrayList<Usuario> listaUsuarios;
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
			listaUsuarios = ayudantebd.consultarTodosUsuarios();
			if (usuarioLogueado.getTipoUsuario().equals("User"))
				vistamenu.mostrarPanel("VistaUsuario", false);
			else
				vistamenu.mostrarPanel("VistaUsuario", true);

			break;

		// borrar usuario
		case VistaUsuario.CM_BORRAR_USER:
			borrarUsuario();
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

		case VistaUsuario.CM_PRIMERO:
			System.out.println("primero");
			consultarUsuario(VistaUsuario.CM_PRIMERO);
			break;
		case VistaUsuario.CM_ANTERIOR:
			System.out.println("anterior");
			consultarUsuario(VistaUsuario.CM_ANTERIOR);
			break;
		case VistaUsuario.CM_SIGUIENTE:
			System.out.println("siguiente");
			consultarUsuario(VistaUsuario.CM_SIGUIENTE);
			break;
		case VistaUsuario.CM_ULTIMO:
			System.out.println("ultimo");
			consultarUsuario(VistaUsuario.CM_ULTIMO);
			break;
		}
	}

	// Este método carga en el panel los usuarios primero, ant, sig o ultimo
	private void consultarUsuario(String opcion) {
		switch (opcion) {
		case VistaUsuario.CM_PRIMERO:
			if (listaUsuarios.size() > 0)
				usuarioActual = listaUsuarios.get(0);	
			break;
		case VistaUsuario.CM_ANTERIOR:
			if ( listaUsuarios.indexOf(usuarioActual) > 0)
				usuarioActual = listaUsuarios.get(listaUsuarios.indexOf(usuarioActual)-1);
			break;
		case VistaUsuario.CM_SIGUIENTE:
			if ( listaUsuarios.indexOf(usuarioActual) < (listaUsuarios.size()-1))
				usuarioActual = listaUsuarios.get(listaUsuarios.indexOf(usuarioActual)+1);
			break;
		case VistaUsuario.CM_ULTIMO:
			if (listaUsuarios.size() > 0)
				usuarioActual = listaUsuarios.get(listaUsuarios.size()-1);
			break;
		}
		
		System.out.println("consultando usuario"+usuarioActual);

		vistamenu.getPanelVistaUsuario().getTxtNombre().setText(usuarioActual.getNombreUsuario());
		if(usuarioActual.getTipoUsuario().equals("User"))
			vistamenu.getPanelVistaUsuario().getChoTipoUsuario().select(0);
		else 
			vistamenu.getPanelVistaUsuario().getChoTipoUsuario().select(1);
	}

	private void borrarUsuario() {
		String nombre;
		Usuario usuario;
		nombre = vistamenu.getPanelVistaUsuario().getTxtNombre().getText();
		usuario = new Usuario(0, nombre, "", "");

		final JButton btnSi = new JButton("Si");
		btnSi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("borrando usuario:" + nombre);
				ayudantebd.borrarUsuario(usuario);
			}
		});

		String[] botones = { "Si", "No" };

		int opcion = JOptionPane.showOptionDialog(vistamenu, "¿Está seguro de borrar el usuario " + nombre, "Aviso",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, null);

		if (opcion == JOptionPane.YES_OPTION) {
			System.out.println("borrando usuario:" + nombre);
			ayudantebd.borrarUsuario(usuario);
			listaUsuarios = ayudantebd.consultarTodosUsuarios();
		}

	}

	private void crearUsuario() {
		// comprobamos el rol del usuario

		String nombre, passwd, tipo;
		Usuario usuario;

		nombre = vistamenu.getPanelVistaUsuario().getTxtNombre().getText().trim();
		passwd = vistamenu.getPanelVistaUsuario().getTxtContrasena().getText().trim();
		tipo = vistamenu.getPanelVistaUsuario().getChoTipoUsuario().getSelectedItem();

		// Comprobamos si ha rellenado todos los campos
		if(nombre.equals("") || passwd.equals("")){
			// ventana de aviso de diálogo SWING
						JOptionPane.showMessageDialog(vistamenu, "Debe rellenar todos los campos", "Aviso",
								JOptionPane.WARNING_MESSAGE);
						System.err.println("campo vacio");
		}
			
		// Comprobamos si el usuario ya existe
		else if (ayudantebd.existeUsuario(nombre)) {
			// ventana de aviso de diálogo SWING
			JOptionPane.showMessageDialog(vistamenu, "El usuario " + nombre + " ya existe", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			System.err.println("usuario " + nombre + " ya existe");

		} else {

			usuario = new Usuario(0, nombre, passwd, tipo);

			System.out.println("usuario creado" + usuario);
			ayudantebd.registrarUsuario(usuario);
			listaUsuarios = ayudantebd.consultarTodosUsuarios();

		}

	}

	private void limpiarLogin() {
		vistamenu.getLogin().getTextusuario().setText(EMPTY_FIELD);
		vistamenu.getLogin().getTextcontrasena().setText(EMPTY_FIELD);
	}

	// Método para loguearse en la aplicación
	private void login() {
		Usuario usuario;
		String user = vistamenu.getLogin().getTextusuario().getText();
		String passwd = vistamenu.getLogin().getTextcontrasena().getText();
		// obtenemos el usuario si existe, en caso contrario el id seará 0
		usuario = ayudantebd.login(user, passwd);
		if (usuario.getId() != 0) {
			usuarioLogueado = usuario;
			vistamenu.getLogin().cerrarLogin();
			vistamenu.haceVisibleMenu(true, usuarioLogueado.getTipoUsuario());

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
