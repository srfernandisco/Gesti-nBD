package es.studium.Practica1GestionBD.vista;

import java.awt.Button;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.util.ArrayList;

import es.studium.Practica1GestionBD.modelo.Usuario;

public class VistaUsuario extends Panel{
	
	
	public static final String CM_BORRAR_USER = "Borrar";
	public static final String CM_NUEVO_USER = "Nuevo";
	public static final String CM_PRIMERO= "btnPrimero";
	public static final String CM_SIGUIENTE= "btnSiguiente";
	public static final String CM_ANTERIOR= "btnAnterior";
	public static final String CM_ULTIMO= "btnUltimo";
	
	
	
	private static final int ALTO = 700;
	private static final int ANCHO = 300;

	private static final long serialVersionUID = 1L;
	
	
	private Label labelNombre;
	private TextField txtNombre;
	private Label labelContrasena;
	private TextField txtContrasena;
	private Label labelTipoUsuario;
	private Choice choTipoUsuario;
	private Button btnNuevo, btnBorrar, btnSiguiente, btnAnterior, btnPrimero, btnUltimo;
	
	
	public VistaUsuario() {
		
		
		setLayout(new GridLayout(2, 1));
		Panel panel1 = new Panel();	
		Panel panel2 = new Panel();
		panel1.setLayout(new GridLayout(4, 2));
		panel2.setLayout(new GridLayout(1,4));
		
		labelNombre= new Label ("Usuario");
		txtNombre= new TextField ("",20);
		labelContrasena = new Label ("Contraseña");
		txtContrasena = new TextField ("",20);
		txtContrasena.setEchoChar('*');

		labelTipoUsuario = new Label("Tipo usuario") ;
		choTipoUsuario = new Choice ();
		
		choTipoUsuario.add("User");
		choTipoUsuario.add("Admin");
		btnNuevo= new Button ("nuevo");
		btnBorrar= new Button ("borrar");
		btnSiguiente = new Button (">>");
		btnAnterior = new Button ("<<");
		btnPrimero = new Button ("|<<");
		btnUltimo = new Button ("|>>");
		
		
		panel1.add(labelNombre);
		panel1.add(txtNombre);
		panel1.add(labelContrasena);
		panel1.add(txtContrasena);
		panel1.add(labelTipoUsuario);
		panel1.add(choTipoUsuario);
		panel1.add(btnNuevo);
		panel1.add(btnBorrar);
		add(panel1);
		
		panel2.add(btnPrimero);
		panel2.add(btnAnterior);
		panel2.add(btnSiguiente);	
		panel2.add(btnUltimo);
		add(panel2);
		

		setSize(ANCHO, ALTO);
		//setLocationRelativeTo(null);
		setVisible(false);
		
		
		// Cierra la ventana de diálogo al pulsar la x
		/*
				addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent windowEvent) {
						dispose();
						System.exit(0);
					}
				});
		*/
	}


	public Button getBtnNuevo() {
		return btnNuevo;
	}	


	public Button getBtnBorrar() {
		return btnBorrar;
	}


	public Button getBtnSiguiente() {
		return btnSiguiente;
	}

	public Button getBtnAnterior() {
		return btnAnterior;
	}

		public Button getBtnPrimero() {
		return btnPrimero;
	}
	
	public Button getBtnUltimo() {
		return btnUltimo;
	}


	public TextField getTxtNombre() {
		return txtNombre;
	}


	

	public TextField getTxtContrasena() {
		return txtContrasena;
	}


	

	public Choice getChoTipoUsuario() {
		return choTipoUsuario;
	}


	



	
	
/*
	public void haceVisibleUsuario(boolean visible) {
		setVisible(visible);
		
	}
*/
	/*
	public void conectaControlador(Controlador controlador){
		//Maneja el controlador el comando nuevo
		btnNuevo.addActionListener(controlador);
		btnNuevo.setActionCommand(CM_NUEVO_USER);
		
		//Maneja el controlador el comando borrar
		btnBorrar.addActionListener(controlador);
		btnBorrar.setActionCommand(CM_BORRAR_USER);
	}
	
	*/

	

	

	

	


}
