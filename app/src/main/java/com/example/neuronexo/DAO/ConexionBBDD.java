package com.example.neuronexo.DAO;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
    private Connection connection = null;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://10.0.2.2:5432/neuronex";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "admin";

    public Connection conectar(){
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        }catch (SQLException e){
            System.out.println("Error con la conexión con la base de datos");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver");
            e.printStackTrace();
        }
        return connection;
    }
    public void cerrarConexion(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al desconectar la base de datos");
            e.printStackTrace();
        }
    }
}
