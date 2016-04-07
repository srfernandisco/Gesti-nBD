package es.studium.Practica1GestionBD.vista;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import es.studium.Practica1GestionBD.controlador.Controlador;

public class MenuPrincipal extends Frame {
	
	
	public static final String GESTION_USUARIO = "Usuarios";
	public static final String COMANDO_SALIR = "Salir";

	
	private static final long serialVersionUID = 1L;
	private static final int ALTO = 400;
	private static final int ANCHO = 700;
	
	
	private Login login;
	
	private Panel panelPrincipal, panel2;
	private VistaUsuario panelVistaUsuario;
	private CardLayout layoutPanelPrincipal;
	
	// En primer lugar creamos la barra de menú
	MenuBar barraMenu = new MenuBar();
	// Ahora creamos los elementos principales del menú
	Menu menuGestion = new Menu("Gestión");
	Menu menuInformes = new Menu("Informes");
	Menu menuSalir = new Menu ("Salir");
	
	// menuArchivoNuevo contendrá otros elementos, por lo que es tipo Menu
	// Los siguientes son MenuItem pues dentro no tienen submenús
	MenuItem mniGestionUsuario = new MenuItem(GESTION_USUARIO);
	MenuItem mniGestionClientes = new MenuItem("Clientes");
	MenuItem mniGestionVentas = new MenuItem("Ventas");
	MenuItem mniGestionProductos = new MenuItem("Productos");
	
	
	// Luego los de Edición
	MenuItem mniInformesClientes = new MenuItem("Clientes");
	MenuItem mniInformesVentas = new MenuItem("Ventas");
	MenuItem mniInformesProductos = new MenuItem("Productos");
	
	//Salir
	MenuItem mniSalirInformacion = new MenuItem("Información");
	MenuItem mniSalirSalir = new MenuItem("Salir");
	
	public MenuPrincipal()
	{
	setLayout (new FlowLayout());
	
	
	//Creamos el panel principal que contiene todos los paneles
	panelPrincipal = new Panel();
	layoutPanelPrincipal = new CardLayout();
	panelPrincipal.setLayout(layoutPanelPrincipal);
	add(panelPrincipal);
	
	// Establecemos la barra de menú
	setMenuBar(barraMenu);
	//Añadimos a gestión
	menuGestion.add(mniGestionUsuario);
	menuGestion.add(mniGestionClientes);
	menuGestion.add(mniGestionVentas);
	menuGestion.add(mniGestionProductos);
	
	// Añadimos a Informes
	
	menuInformes.add(mniInformesClientes);
	menuInformes.add(mniInformesVentas);
	menuInformes.add(mniInformesProductos);
	
	//Añadimos a salir
	menuSalir.add(mniSalirInformacion);
	menuSalir.add(mniSalirSalir);
	
	
	// Por último agregamos los elementos gestión, informes y salir la barra
	barraMenu.add(menuGestion);
	barraMenu.add(menuInformes);
	barraMenu.add(menuSalir);
	
	// añadimos los paneles
	panelVistaUsuario = new VistaUsuario();
	panel2 = new Panel();
	// ... añadimos todos los paneles necesarios
	panelPrincipal.add(panelVistaUsuario,"VistaUsuario");
	panelPrincipal.add(panel2,"panel2");
	
	layoutPanelPrincipal.show(panelPrincipal,"panel2");
	
	setLocationRelativeTo(null);
	setSize(ANCHO,ALTO);
	setVisible(false);
	//Añadimos la parte de login
	login=new Login(this);
	
	// Cierra la ventana de diálogo al pulsar la x
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent) {
			dispose();
			System.exit(0);
		}
	});
}
	
	/**
	 * Método para mostrar los distintos paneles del Frame principal
	 * @param nombrePanel
	 */
	public void mostrarPanel(String nombrePanel,boolean privilegios){
		layoutPanelPrincipal.show(panelPrincipal, nombrePanel);
		if(!privilegios){
			panelVistaUsuario.getBtnNuevo().setEnabled(false);
			panelVistaUsuario.getBtnBorrar().setEnabled(false);
		}
	}
	
	public void haceVisibleMenu(boolean visible, String tipoUsuario){
		setTitle ("Gestión Base de Datos: "+tipoUsuario);
		setVisible(visible);
	}

	public void conectaControlador(Controlador controlador){
		// maneja el controlador el evento salir
		mniSalirSalir.addActionListener(controlador);
		mniSalirSalir.setActionCommand(COMANDO_SALIR);
		// maneja el controlador el evento de la ventana login Aceptar
		login.getBtnAceptar().addActionListener(controlador);
		login.getBtnAceptar().setActionCommand(Login.COMANDO_ACEPTAR);
		//maneja el controlador el evento limpiar
		login.getBtnLimpiar().addActionListener(controlador);
		login.getBtnLimpiar().setActionCommand(Login.COMANDO_LIMPIAR);
		//maneja el controlador el evento gestion usuario
		mniGestionUsuario.addActionListener(controlador);
		mniGestionUsuario.setActionCommand(GESTION_USUARIO);
		
		panelVistaUsuario.getBtnNuevo().addActionListener(controlador);
		panelVistaUsuario.getBtnNuevo().setActionCommand(VistaUsuario.CM_NUEVO_USER);
		//Maneja el controlador el comando borrar
		panelVistaUsuario.getBtnBorrar().addActionListener(controlador);
		panelVistaUsuario.getBtnBorrar().setActionCommand(VistaUsuario.CM_BORRAR_USER);
		
		
		
		
		
	}
	
	/**
	 * Devuelve la ventana de diálogo de login
	 * @return
	 */
	public Login getLogin(){
		return login;
	}

	public VistaUsuario getPanelVistaUsuario(){
		return panelVistaUsuario;
	}

	
	
}
