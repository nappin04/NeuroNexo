package com.example.neuronexo.DAO;



import com.example.neuronexo.Models.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

                // Query para verificar si la tabla puntuacion ya existe
                String queryPuntuacion = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'nivel')";
                PreparedStatement statementPuntuacion = connection.prepareStatement(queryPuntuacion);
                ResultSet resultSetPuntuacion = statementPuntuacion.executeQuery();
                resultSetPuntuacion.next();
                boolean tablaPuntuacionExiste = resultSetPuntuacion.getBoolean(1);

                // Si alguna de las tablas no existe, las creamos
                if (!tablaUsuariosExiste) {
                    String queryCrearUsuarios = "CREATE TABLE usuarios (" +
                            "id_user SERIAL PRIMARY KEY," +
                            "sexo BOOLEAN" +
                            "edad INT" +
                            ")";
                    PreparedStatement statementCrearUsuarios = connection.prepareStatement(queryCrearUsuarios);
                    statementCrearUsuarios.executeUpdate();
                    System.out.println("Tabla usuario creada correctamente.");
                }

                if (!tablaPuntuacionExiste) {
                    String queryCrearPuntuacion = "CREATE TABLE nivel (" +
                            "id_nivel SERIAL PRIMARY KEY," +
                            "nombre VARCHAR (50)" +
                            "puntos INT" +
                            "FOREIGN KEY (id_usuario) REFERENCES usuario(id_user)" +
                            ")";
                    PreparedStatement statementCrearPuntuacion = connection.prepareStatement(queryCrearPuntuacion);
                    statementCrearPuntuacion.executeUpdate();
                    System.out.println("Tabla nivel creada correctamente.");
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
                String query = "INSERT INTO usuario ( sexo, edad) VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setBoolean(1, usuario.isSexo());
                statement.setInt(2, usuario.getEdad());


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








    public boolean guardarPuntos(int puntos, String username){
        if(conectarConBBDD()){
            try{
                String query = "INSERT INTO puntuacion (puntuacion, usuario_username) VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, puntos);
                statement.setString(2, username);

                int filasAfectadas = statement.executeUpdate();
                if(filasAfectadas > 0){
                    System.out.println("Puntos registrados correctamente");
                    return true;
                }else{
                    System.out.println("No se han guardado los puntos");
                    return false;
                }

            }catch (SQLException e){
                System.out.println("Se ha producido un error con la consulta");
                return false;
            }
            finally {
                if(!conectarConBBDD()){
                    conexionBBDD.cerrarConexion(connection);
                }
            }
        }else{
            System.out.println("No se pudo conectar con la base de datos");
            return false;
        }

    }

    public ArrayList<UsuarioPuntuacion> obtenerPuntos() {
        ArrayList<UsuarioPuntuacion> listaRanking = new ArrayList<>();

        if (conectarConBBDD()) {
            try {
                String query = "SELECT usuarios.usuario, p.puntuacion FROM usuarios " +
                        "JOIN puntuacion p ON usuarios.usuario = p.usuario_username ORDER BY p.puntuacion DESC";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                // Creo una lista temporal para almacenar los resultados antes de procesarlos
                ArrayList<UsuarioPuntuacion> listaTemporal = new ArrayList<>();

                // Mientras que el ResultSet contenga datos, agrego los usuarios y sus puntos a la lista temporal
                while (resultSet.next()) {
                    String username = resultSet.getString("usuario");
                    int puntos = resultSet.getInt("puntuacion");
                    UsuarioPuntuacion usuarioPuntuacion = new UsuarioPuntuacion(username, puntos);
                    listaTemporal.add(usuarioPuntuacion);
                }

                // Utilizo dicha lista temporal y actualizo los puntos en la base de datos si es necesario
                for (UsuarioPuntuacion usuarioPuntuacion : listaTemporal) {
                    // Si el usuario ya está en la listaRanking, actualizo sus puntos en la base de datos
                    if (listaRanking.contains(usuarioPuntuacion)) {
                        actualizarPuntos(usuarioPuntuacion.getPuntuacion(), usuarioPuntuacion.getUsuario());
                    } else {
                        // Si el usuario no está en la listaRanking, lo agrego
                        listaRanking.add(usuarioPuntuacion);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Hubo un error con la consulta: " + e.getMessage());
            } finally {
                conexionBBDD.cerrarConexion(connection);
            }
        } else {
            System.out.println("No se pudo conectar con la base de datos");
        }

        return listaRanking;
    }







}

