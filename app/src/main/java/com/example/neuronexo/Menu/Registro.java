package com.example.neuronexo.Menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.neuronexo.DAO.UsuarioDAO;
import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Models.UsuarioModel;
import com.example.neuronexo.R;

public class Registro extends AppCompatActivity {

    private Button alert;
    private String genero, edad;
    private ToastMensajes mensajeToast = new ToastMensajes();
    private UsuarioModel usuarioModel;
    private UsuarioDAO usuarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        alert = findViewById(R.id.bt_mostrarAlert);

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoSexo();
            }
        });


    }

    private void mostrarDialogoSexo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        builder.setTitle("¿Eres hombre o mujer?")
                // El -1 es el valor que se obtiene si no se ha seleccionado ninguna opción
                .setSingleChoiceItems(new String[]{"Hombre", "Mujer"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int opcion) {
                        if(opcion == -1){
                            mensajeToast.mostrarToastlargo(Registro.this, "Debes elegir una opción 😁");
                        }else {
                            if (opcion == 0) {
                                genero = "Hombre";
                            } else if (opcion == 1) {
                                genero = "Mujer";
                            }
                        }
                    }
                })
                .setPositiveButton("Siguiente", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Una vez seleccionado el género, muestro el segundo AlertDialog
                        mostrarDialogoEdad();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void mostrarDialogoEdad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        builder.setTitle("¿Cuál es tu grupo de edad?")
                .setSingleChoiceItems(new String[]{"Niño", "Joven", "Adulto", "Persona mayor"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int opcion) {
                        if(opcion == -1){
                            mensajeToast.mostrarToastlargo(Registro.this, "Debes elegir una opción 😁");
                        } else {
                            if (opcion == 0) {
                                edad = "Niño";
                            } else if (opcion == 1) {
                                edad = "Joven";
                            } else if (opcion == 2) {
                                edad = "Adulto";
                            } else if (opcion == 3) {
                                edad = "Persona mayor";
                            }
                        }
                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Una vez que el usuario se haya registrado, guardamos los datos en la base de datos
                        // Creamos el usuario
                        usuarioModel = new UsuarioModel(genero, edad);
                        // Ahora llamamos al DAO para que registre los datos en la base de datos
                        usuarioDAO = new UsuarioDAO();

                        // Almacenamos la respuesta obtenida del DAO
                        boolean respuesta = usuarioDAO.registrarUsuario(usuarioModel);
                        if (respuesta){
                            mensajeToast.mostrarToastcorto(Registro.this, "¡BIENVENID@! 🥳🥳");
                        }else {
                            mensajeToast.mostrarToastcorto(Registro.this, "No se pudo registrar 😕");
                        }


                    }
                })
                // Esto evita que el usuario si pulsa fuera del AlertDialog se cierre
                .setCancelable(false)
                // Mostramos el AlertDialog
                .show();
    }
}