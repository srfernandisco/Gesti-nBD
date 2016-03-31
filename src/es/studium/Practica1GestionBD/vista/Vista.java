package es.studium.Practica1GestionBD.vista;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import es.studium.Practica1GestionBD.controlador.Controlador;

public class Vista extends Frame {
	
	
	public static final String COMANDO_SALIR = "Salir";

	private static final long serialVersionUID = 1L;
	
	private Login login;
	// En primer lugar creamos la barra de menú
	MenuBar barraMenu = new MenuBar();
	// Ahora creamos los elementos principales del menú
	Menu menuArchivo = new Menu("Archivo");
	Menu menuEdicion = new Menu("Edición");
	// Y Ahora los elementos de cada opción del menú principal
	// Primero los de Archivo
	// menuArchivoNuevo contendrá otros elementos, por lo que es tipo Menu
	Menu menuArchivoNuevo = new Menu("Nuevo");
	// Los siguientes son MenuItem pues dentro no tienen submenús
	MenuItem mniArchivoAbrir = new MenuItem("Abrir");
	MenuItem mniArchivoGuardar = new MenuItem("Guardar");
	MenuItem mniArchivoSalir = new MenuItem(COMANDO_SALIR);
	// Luego los de Edición
	MenuItem mniEdicionCortar = new MenuItem("Cortar");
	MenuItem mniEdicionCopiar = new MenuItem("Copiar");
	MenuItem mniEdicionPegar = new MenuItem("Pegar");
	
	// Por último, creamos los elementos dentro de Nuevo
	MenuItem mniArchivoNuevoVentana = new MenuItem("Ventana");
	MenuItem mniArchivoNuevoDialogo = new MenuItem("Dialogo");
	
	public Vista()
	{
	setLayout (new FlowLayout());
	setTitle ("Menú" );
	// Establecemos la barra de menú
	setMenuBar(barraMenu);
	// Empezamos a añadirle los elementos
	// Primero a menuArchivoNuevo, le añadimos su submenus
	menuArchivoNuevo.add(mniArchivoNuevoVentana);
	menuArchivoNuevo.add(mniArchivoNuevoDialogo);
	// Seguimos con los elementos de archivo
	menuArchivo.add(menuArchivoNuevo);
	menuArchivo.add(mniArchivoAbrir);
	menuArchivo.add(mniArchivoGuardar);
	// Añadimos un separador
	menuArchivo.addSeparator();
	menuArchivo.add(mniArchivoSalir);
	// Y ahora a edicion los suyos
	menuEdicion.add(mniEdicionCortar);
	menuEdicion.add(mniEdicionCopiar);
	menuEdicion.add(mniEdicionPegar);
	// Por último agregamos los elementos archivo y edicion a 	la barra
	barraMenu.add(menuArchivo);
	barraMenu.add(menuEdicion);
	setLocationRelativeTo(null);
	setSize(200,200);
	setVisible(false);
	login=new Login(this);
	}
	
	public void haceVisible(boolean visible){
		setVisible(visible);
	}

	public void conectaControlador(Controlador controlador){
		// maneja el controlador el evento salir
		mniArchivoSalir.addActionListener(controlador);
		mniArchivoSalir.setActionCommand(COMANDO_SALIR);
		// maneja el controlador el evento de la ventana login Aceptar
		login.getBtnAceptar().addActionListener(controlador);
		login.getBtnAceptar().setActionCommand(Login.COMANDO_ACEPTAR);
		
		login.getBtnLimpiar().addActionListener(controlador);
		login.getBtnLimpiar().setActionCommand(Login.COMANDO_LIMPIAR);
		
		
	}
	
	/**
	 * Devuelve la ventana de diálogo de login
	 * @return
	 */
	public Login getLogin(){
		return login;
	}
	
}
