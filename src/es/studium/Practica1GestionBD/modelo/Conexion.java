package es.studium.Practica1GestionBD.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Conexion para una base de datos generica
public class Conexion {
	
	private static Connection conexion;
	
	Conexion(String driver,String URL, String user, String password){
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(URL,user, password);
			System.out.println("Conectado");
			
		} catch (ClassNotFoundException e) {
			System.err.println("driver no encontrado");
		} catch (SQLException e) {
			System.err.println("error al conectar");

		}

	}

	static Connection getConexion() {
		return conexion;
	}



}