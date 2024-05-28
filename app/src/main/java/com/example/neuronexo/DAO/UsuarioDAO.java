package com.example.neuronexo.DAO;



import com.example.neuronexo.Models.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;
    private ConexionBBDD conexionBBDD;
    private int filasAfectadas = 0;


    public UsuarioDAO(){
    }
    public boolean conectarConBBDD(){
        conexionBBDD = new ConexionBBDD();
        connection = conexionBBDD.conectar();
        if(connection != null){
            return true;
        }
        return false;
    }


    // Método para crear las tablas si no existen
    public void crearTablasSiNoExisten() {
        if (conectarConBBDD()) {
            try {
                // Query para verificar si la tabla usuarios ya existe (query buscada por foros)
                String queryUsuarios = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'usuario')";
                PreparedStatement statementUsuarios = connection.prepareStatement(queryUsuarios);
                ResultSet resultSetUsuarios = statementUsuarios.executeQuery();
                // Obtengo el resultado de la busqueda en un resulset
                resultSetUsuarios.next();
                // Almaceno la respuesta en una variable booleana
                boolean tablaUsuariosExiste = resultSetUsuarios.getBoolean(1);



                // Si alguna de las tablas no existe, las creamos
                if (!tablaUsuariosExiste) {
                    String queryCrearUsuarios = "CREATE TABLE usuarios (" +
                            "id_user SERIAL PRIMARY KEY," +
                            "sexo BOOLEAN" +
                            "edad INT" +
                            "nivel INT" +
                            "puntos INT" +
                            ")";
                    PreparedStatement statementCrearUsuarios = connection.prepareStatement(queryCrearUsuarios);
                    statementCrearUsuarios.executeUpdate();
                    System.out.println("Tabla usuario creada correctamente.");
                }



            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBBDD.cerrarConexion(connection);
            }
        } else {
            System.out.println("No se pudo conectar con la base de datos");
        }
    }




    public boolean registrarUsuario(UsuarioModel usuario){
        if(conectarConBBDD()){

            try {
                String query = "INSERT INTO usuario ( sexo, edad, nivel, puntos) VALUES (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, usuario.getSexo());
                statement.setString(2, usuario.getEdad());
                statement.setInt(3, usuario.getNivel());
                statement.setInt(4, usuario.getPuntos());


                // Las filas afectadas son las que me dirán si algún dato se ha guardado en la base de datos
                filasAfectadas = statement.executeUpdate();
                // Si filas afectadas es mayor que 0, significa que al menos un dato se ha guardado correctamente
                if (filasAfectadas > 0){
                    System.out.println("Usuario registrado correctamente");
                    return true;
                }else{
                    System.out.println("No se ha podido registrar el usuario");
                    return false;
                }

            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }finally {
                if(connection != null){
                    conexionBBDD.cerrarConexion(connection);
                }
            }
        }else{
            System.out.println("No se pudo conectar a la base de datos");
            return false;
        }

    }










    public int obtenerUltimaIdUsuario(){
        if(conectarConBBDD()){
            try {
                String query = "SELECT MAX(id_usuario) AS last_id FROM usuario";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()) {
                    int lastId = resultSet.getInt("last_id");
                    System.out.println("Última ID de usuario obtenida correctamente: " + lastId);
                    return lastId;
                }

                System.out.println("No se encontraron usuarios en la base de datos");
                return -1; // Retorno un valor negativo si no se encuentra ningún usuario

            } catch (SQLException e){
                e.printStackTrace();
                return -1; // Retorno un valor negativo si hay una excepción SQL
            } finally {
                if(connection != null){
                    conexionBBDD.cerrarConexion(connection);
                }
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos");
            return -1; // Retorno un valor negativo si no se puede conectar a la base de datos
        }
    }









}

