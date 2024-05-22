package com.example.neuronexo.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.neuronexo.DAO.PreguntasRespuestas;
import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Niveles.Nivel1;
import com.example.neuronexo.Niveles.Nivel2;
import com.example.neuronexo.Niveles.Nivel3;
import com.example.neuronexo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Menuu extends AppCompatActivity {

    private ToastMensajes mensaje= new ToastMensajes();
    private int respuestaCorrecta = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Creo/configuro las preguntas
        crearPreguntas();
        // Muestro la primera pregunta
        mostrarSiguientePregunta();

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pregunta " + (contadorPreguntas + 1) +": " + pregunta )
                            .setSingleChoiceItems(respuestaArray, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Guardo la respuesta del usuario
                                    respuestaUsuario.add(which);
                                    // Si el usuario no ha elegido ninguna opcion...
                                    if (which == -1) {
                                        mensaje.mostrarToastcorto(Menuu.this, "Debes elegir una opción");
                                    } else {
                                        // Si la opcion elegida es igual a la opcion correcta...
                                        if (which == indiceCorrecto) {
                                            mensaje.mostrarToastcorto(Menuu.this, "¡CORRECTO!");
                                            respuestaCorrecta ++;
                                        } else {
                                            mensaje.mostrarToastcorto(Menuu.this, "INCORRECTO");
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
                    mensaje.mostrarToastcorto(Menuu.this, "Error en las respuestas de la pregunta: " + pregunta);
                }
            } else {
                // Manejo el caso donde la pregunta no se encuentra en el mapa
                mensaje.mostrarToastcorto(Menuu.this, "No se encontró la pregunta: " + pregunta);
            }
        } else {
            if (respuestaCorrecta == 0 || respuestaCorrecta == 1) {
                Intent intent = new Intent(Menuu.this,  Nivel1.class);
                startActivity(intent);
            } else if (respuestaCorrecta == 2) {
                Intent intent = new Intent(Menuu.this, Nivel2.class);
                startActivity(intent);
            } else if (respuestaCorrecta == 3) {
                Intent intent = new Intent(Menuu.this, Nivel3.class);
                startActivity(intent);
            }
            finish(); // Finalizo la actividad actual
        }
    }

}