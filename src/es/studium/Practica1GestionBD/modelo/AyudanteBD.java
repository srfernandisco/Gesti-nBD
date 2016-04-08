package es.studium.Practica1GestionBD.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @descrition Clase encargada del modelo de datos
 * @author Fer
 * @date 30/4/2016
 * @version 1.0
 * @license GPLv3
 */

public class AyudanteBD extends Conexion {

	private static final String DATABASE = "herboristeria";
	private static final String USER = "herboristeria";
	private static final String PASSWORD = "herboristeria";

	private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private static final String EXISTE_USUARIO = "SELECT * FROM USUARIOS " + "WHERE nombreUSuario = ?";
	private static final String CONSULTAR_USUARIO = "SELECT * FROM USUARIOS "
			+ "WHERE nombreUSuario = ? AND passwordUsuario=PASSWORD(?)";

	private final static String INSERT_USER = "INSERT INTO USUARIOS " + "(nombreUsuario, passwordUsuario,tipoUsuario) "
			+ "VALUES ( ?, PASSWORD(?),?)";

	private final static String CONSULTA_TODOS_USUARIO = "Select * from Usuarios order by nombreUsuario ";

	private static final String DELETE_USUARIO = "DELETE FROM USUARIOS WHERE " + "NOMBREUSUARIO=?";

	// Constructor
	public AyudanteBD(String driver, String URL, String user, String password) {
		super(driver, URL, user, password);

	}

	// Constructor
	public AyudanteBD() {
		super(DRIVER, URL, USER, PASSWORD);

	}

	/**
	 * comprueba que el usuario y la contraseña existen y son válidos
	 * 
	 * @param user
	 * @param passwd
	 * @return si existen
	 */
	public Usuario login(String user, String passwd) {
		boolean existe = false;
		Usuario usuario = new Usuario();
		try {
			PreparedStatement sentencia = getConexion().prepareStatement(CONSULTAR_USUARIO);
			sentencia.setString(1, user);
			sentencia.setString(2, passwd);
			ResultSet rs = sentencia.executeQuery();
			if (rs.next()) {
				usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("NOMBREUSUARIO"),
						rs.getString("passwordUsuario"), rs.getString("tipoUsuario"));
			}

		} catch (SQLException e) {
			System.err.println("Error en la sentencia SQL: " + e.getMessage());

		}
		return usuario;
	}

	public boolean existeUsuario(String nombre) {
		boolean existe = false;
		try {
			PreparedStatement sentencia = getConexion().prepareStatement(EXISTE_USUARIO);
			sentencia.setString(1, nombre);
			ResultSet rs = sentencia.executeQuery();
			existe = rs.next();

		} catch (SQLException e) {
			System.err.println("Error en la sentencia SQL: " + e.getMessage());

		}
		return existe;
	}

	/**
	 * Registra(alta) en la base de datos MySQL el usuario, guarda la contraseña
	 * hasheada y el tipo de usuario
	 * 
	 * @param usuario
	 * @param passwd
	 * @param tipoUsuario
	 * @return
	 */
	public boolean registrarUsuario(Usuario usuario) {
		int filasAfectadas = 0;
		try {
			PreparedStatement sentencia = getConexion().prepareStatement(INSERT_USER);
			sentencia.setString(1, usuario.getNombreUsuario());
			sentencia.setString(2, usuario.getPasswordUsuario());
			sentencia.setString(3, usuario.getTipoUsuario());
			filasAfectadas = sentencia.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error en la sentencia SQL: " + e.getMessage());
		}
		// Si se ha afectado una fila, filasAfectadas será igual a 1 y
		// devolvemos true
		// en caso contrario no ha insertado ningún usuario a la tabla
		return (filasAfectadas == 1);

	}

	/**
	 * Para dar de baja a usuarios
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean borrarUsuario(Usuario usuario) {
		int filasAfectadas = 0;

		try {
			PreparedStatement sentencia = getConexion().prepareStatement(DELETE_USUARIO);
			sentencia.setString(1, usuario.getNombreUsuario());
			filasAfectadas = sentencia.executeUpdate();
		} catch (SQLException e) {

			System.err.println("Error en la sentencia SQL: " + e.getMessage());
		}
		// como en el caso anterior pero en vez de insertar una fila borra una
		// fila
		return (filasAfectadas == 1);

	}

	public ArrayList<Usuario> consultarTodosUsuarios() {
		ArrayList<Usuario> listaUsuarios = new ArrayList();
		Usuario usuario;
		Statement sentencia;
		try {
			sentencia = getConexion().createStatement();

			ResultSet rs = sentencia.executeQuery(CONSULTA_TODOS_USUARIO);

			while (rs.next()) {
				usuario = new Usuario(rs.getInt("idUsuario"), 
						rs.getString("NOMBREUSUARIO"),
						rs.getString("passwordUsuario"), 
						rs.getString("tipoUsuario"));
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			System.err.println("Error en la sentencia SQL: " + e.getMessage());

		}
		return listaUsuarios;
	}

}
