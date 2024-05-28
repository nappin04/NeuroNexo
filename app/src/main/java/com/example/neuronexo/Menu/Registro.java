package com.example.neuronexo.Menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.neuronexo.DAO.PreguntasRespuestas;
import com.example.neuronexo.DAO.UsuarioDAO;
import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Models.UsuarioModel;
import com.example.neuronexo.Niveles.Nivel1;
import com.example.neuronexo.Niveles.Nivel2;
import com.example.neuronexo.Niveles.Nivel3;
import com.example.neuronexo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Registro extends AppCompatActivity {

    private Button alert;
    private String genero, edad;
    private ToastMensajes mensajeToast = new ToastMensajes();
    private UsuarioModel usuarioModel;
    private UsuarioDAO usuarioDAO;
    private int respuestaCorrecta = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        alert = findViewById(R.id.bt_mostrarAlert);
        // creo las preguntas tipo test
        crearPreguntas();
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
                        // Le decimos al usuario que se ha registrado correctamente
                        mensajeToast.mostrarToastcorto(Registro.this, "BIENVENID@");

                        mostrarSiguientePregunta();

                    }
                })
                // Esto evita que el usuario si pulsa fuera del AlertDialog se cierre
                .setCancelable(false)
                // Mostramos el AlertDialog
                .show();
    }
    private HashMap<String, PreguntasRespuestas> preguntasRespuestas = new HashMap<>();
    private ArrayList<String> elegirPreguntas;
    private ArrayList<Integer> respuestaUsuario = new ArrayList<>();

    private void crearPreguntas() {
        // Defino las preguntas y sus respuestas (una correcta y dos incorrecta)
        ArrayList<String> pregunta1respuesta = new ArrayList<>();
        pregunta1respuesta.add("Leche");
        pregunta1respuesta.add("Agua");
        pregunta1respuesta.add("Zumo");

        ArrayList<String> pregunta2respuesta = new ArrayList<>();
        pregunta2respuesta.add("5");
        pregunta2respuesta.add("15");
        pregunta2respuesta.add("20");

        ArrayList<String> pregunta3respuesta = new ArrayList<>();
        pregunta3respuesta.add("Con la nariz");
        pregunta3respuesta.add("Con la garganta");
        pregunta3respuesta.add("Con la cabeza");

        ArrayList<String> pregunta4respuesta = new ArrayList<>();
        pregunta4respuesta.add("Un conejo");
        pregunta4respuesta.add("Un perro");
        pregunta4respuesta.add("Un gato");

        // Asocio las preguntas con sus respuestas y la respuesta correcta
        preguntasRespuestas.put("¿Qué alimentos da la vaca?", new PreguntasRespuestas("¿Qué alimentos da la vaca?", pregunta1respuesta, 0)); // La respuesta correcta está en el índice 0
        preguntasRespuestas.put("¿Cuántos dedos tiene una persona en total?", new PreguntasRespuestas("¿Cuántos dedos tiene una persona en total?", pregunta2respuesta, 2)); // La respuesta correcta está en el índice 2
        preguntasRespuestas.put("¿Con qué respira una persona?", new PreguntasRespuestas("¿Con qué respira una persona?", pregunta3respuesta, 0)); // La respuesta correcta está en el índice 0
        preguntasRespuestas.put("¿Qué animal ladra?", new PreguntasRespuestas("¿Qué animal ladra?", pregunta4respuesta, 1)); // La respuesta correcta está en el índice 1

        // Selecciono aleatoriamente 2 preguntas de las disponibles
        elegirPreguntas = new ArrayList<>(preguntasRespuestas.keySet());
        Collections.shuffle(elegirPreguntas);
        elegirPreguntas = new ArrayList<>(elegirPreguntas.subList(0, 3));
    }

    private int contadorPreguntas = 0;

    private void mostrarSiguientePregunta() {
        // Si el contador de las preguntas es menor que el array de preguntas, entra
        if (contadorPreguntas < elegirPreguntas.size()) {
            // Guardo en una variable la primera pregunta
            String pregunta = elegirPreguntas.get(contadorPreguntas);
            // Llamo al método que me devuelve la pregunta elegida
            PreguntasRespuestas preguntaYrespuesta = preguntasRespuestas.get(pregunta);

            if (preguntaYrespuesta != null) {
                // Almaceno las respuestas en un array
                ArrayList<String> respuesta = preguntaYrespuesta.getRespuestas();
                // Creo un contador de respuestas correctas
                int indiceCorrecto = preguntaYrespuesta.getRespuestaCorrecta();


                if (indiceCorrecto >= 0 && indiceCorrecto < respuesta.size()) {

                    CharSequence[] respuestaArray = respuesta.toArray(new CharSequence[0]);

                    // Creo el alertDialog mostrando la pregunta con sus respuestas
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    builder.setTitle("Pregunta " + (contadorPreguntas + 1) +": " + pregunta )
                            .setSingleChoiceItems(respuestaArray, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Guardo la respuesta del usuario
                                    respuestaUsuario.add(which);
                                    // Si el usuario no ha elegido ninguna opcion...
                                    if (which == -1) {
                                        mensajeToast.mostrarToastcorto(Registro.this, "Debes elegir una opción");
                                    } else {
                                        // Si la opcion elegida es igual a la opcion correcta...
                                        if (which == indiceCorrecto) {
                                            mensajeToast.mostrarToastcorto(Registro.this, "¡CORRECTO!");
                                            respuestaCorrecta ++;
                                        } else {
                                            mensajeToast.mostrarToastcorto(Registro.this, "INCORRECTO");
                                        }
                                        // Paso a la siguiente pregunta
                                        contadorPreguntas++;
                                        dialog.dismiss();
                                        mostrarSiguientePregunta();
                                    }
                                }
                            })
                            // Esto permite que el usuario no pulse fuera del AlertDialog
                            .setCancelable(false)
                            // Mostramos el dialogo
                            .show();
                } else {
                    // Manejo el caso donde el índice de la respuesta correcta está fuera de rango
                    mensajeToast.mostrarToastcorto(Registro.this, "Error en las respuestas de la pregunta: " + pregunta);
                }
            } else {
                // Manejo el caso donde la pregunta no se encuentra en el mapa
                mensajeToast.mostrarToastcorto(Registro.this, "No se encontró la pregunta: " + pregunta);
            }
        } else {
            if (respuestaCorrecta == 0 || respuestaCorrecta == 1) {
                // Creo el usuario
                usuarioModel = new UsuarioModel(genero, edad, 1, 0);
                // Llamo al DAO para que lo guarde
                usuarioDAO = new UsuarioDAO();
                // Llamamos al método y lo asociamos a una variable para almacenar la respuesta
                boolean respuesta = usuarioDAO.registrarUsuario(usuarioModel);

                if (respuesta){
                    Intent intent = new Intent(Registro.this,  Nivel1.class);
                    startActivity(intent);
                }
            } else if (respuestaCorrecta == 2) {
                // Creo el usuario
                usuarioModel = new UsuarioModel(genero, edad, 2, 0);
                // Llamo al DAO para que lo guarde
                usuarioDAO = new UsuarioDAO();
                // Almaceno la respuesta del DAO
                boolean resultado = usuarioDAO.registrarUsuario(usuarioModel);
                if (resultado){
                    Intent intent = new Intent(Registro.this,  Nivel2.class);
                    startActivity(intent);
                }
            } else if (respuestaCorrecta == 3) {
                // Creo el usuario
                usuarioModel = new UsuarioModel(genero, edad, 3, 0);
                // Llamo al DAO para que lo guarde
                usuarioDAO = new UsuarioDAO();
                // Obtengo el resultado del DAO
                boolean respuesta = usuarioDAO.registrarUsuario(usuarioModel);
                if (respuesta){
                    Intent intent = new Intent(Registro.this,  Nivel3.class);
                    startActivity(intent);
                }
            }
            finish(); // Finalizo la actividad actual
        }
    }
}