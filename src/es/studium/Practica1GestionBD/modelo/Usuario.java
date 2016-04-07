package es.studium.Practica1GestionBD.modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final String ADMIN = "admin";
	private static final String USER = "user";

	
	private static final long serialVersionUID = 1L;
	private int  idUsuario;
	private String nombreUsuario;
	private String passwordUsuario;
	private String tipoUsuario;
	
	public Usuario() {
		super();
		idUsuario = 0;
		nombreUsuario = "";
		passwordUsuario = "";
		tipoUsuario = "";
	}
	
	
	public Usuario(int id, String nombreUsuario, String passwordUsuario, String tipoUsuario) {
		super();
		this.idUsuario = id;
		this.nombreUsuario = nombreUsuario;
		this.passwordUsuario = passwordUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	public int getId() {
		return idUsuario;
	}
	
	public void setId(int id) {
		this.idUsuario = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPasswordUsuario() {
		return passwordUsuario;
	}
	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	/**
	 * Los tipos de usuario son admin y user
	 * @param tipoUsuario
	 */
	public void setTipoUsuario(String tipoUsuario) {
		if(tipoUsuario.equals(ADMIN)||tipoUsuario.equals(USER))
			this.tipoUsuario = tipoUsuario;
		else tipoUsuario = "";
	}


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", passwordUsuario="
				+ passwordUsuario + ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
	

}
