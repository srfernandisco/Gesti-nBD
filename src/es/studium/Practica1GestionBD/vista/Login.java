package es.studium.Practica1GestionBD.vista;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends Dialog {

	private static final int ALTO = 150;

	private static final int ANCHO = 300;

	private static final long serialVersionUID = 1L;	
	
	public static final String COMANDO_ACEPTAR = "LoginAceptar";
	
	public static final String COMANDO_LIMPIAR = "LoginLimpiar";

	private Label labelusuario;
	private Label labelcontrasena;
	private Button btnAceptar;
	private Button btnLimpiar;
	private TextField textusuario;
	private TextField textcontrasena;

	public Login(Frame frame) {
		super(frame, "Login", false);
		// setTitle("Login");
		setLayout(new FlowLayout());

		labelusuario = new Label("Usuario");
	
		labelcontrasena = new Label("Contraseña");
		btnAceptar = new Button("Aceptar");
		btnLimpiar = new Button("Limpiar");
		textusuario = new TextField("", 20);
		textcontrasena = new TextField("", 20);
		textcontrasena.setEchoChar('*');
		
		this.add(labelusuario);
		this.add(textusuario);
		this.add(labelcontrasena);
		this.add(textcontrasena);
		this.add(btnAceptar);
		this.add(btnLimpiar);

		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		
		// Cierra la ventana de diálogo al pulsar la x
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				dispose();
				System.exit(0);
			}
		});
		
		setVisible(true);

	}
	
	public void cerrarLogin(){
		dispose();
	}

	public Button getBtnAceptar() {
		return btnAceptar;
	}


	public Button getBtnLimpiar() {
		return btnLimpiar;
	}

	public TextField getTextusuario() {
		return textusuario;
	}

	public void setTextusuario(TextField textusuario) {
		this.textusuario = textusuario;
	}

	public TextField getTextcontrasena() {
		return textcontrasena;
	}

	public void setTextcontrasena(TextField textcontrasena) {
		this.textcontrasena = textcontrasena;
	}

	
	
	

}
