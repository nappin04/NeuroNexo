package com.example.neuronexo.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.neuronexo.DAO.PreguntasRespuestas;
import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Menuu extends AppCompatActivity {

    private ToastMensajes mensaje= new ToastMensajes();
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
        ArrayList<String> question1Answers = new ArrayList<>();
        question1Answers.add("Leche");
        question1Answers.add("Agua");
        question1Answers.add("Zumo");

        ArrayList<String> question2Answers = new ArrayList<>();
        question2Answers.add("5");
        question2Answers.add("15");
        question2Answers.add("20");

        ArrayList<String> question3Answers = new ArrayList<>();
        question3Answers.add("Con la nariz");
        question3Answers.add("Con la garganta");
        question3Answers.add("Con la cabeza");

        // Asocio las preguntas con sus respuestas y la respuesta correcta
        preguntasRespuestas.put("Pregunta 1", new PreguntasRespuestas("¿Qué alimentos da la vaca?", question1Answers, 1)); // La respuesta correcta está en el índice 1
        preguntasRespuestas.put("Pregunta 2", new PreguntasRespuestas("¿Cuántos dedos tiene una persona en total?", question2Answers, 2)); // La respuesta correcta está en el índice 2
        preguntasRespuestas.put("Pregunta 3", new PreguntasRespuestas("¿Con qué respira una persona?", question3Answers, 0)); // La respuesta correcta está en el índice 0

        // Selecciono aleatoriamente 2 preguntas de las disponibles
        elegirPreguntas = new ArrayList<>(preguntasRespuestas.keySet());
        Collections.shuffle(elegirPreguntas);
        elegirPreguntas = new ArrayList<>(elegirPreguntas.subList(0, 2));
    }

    private int contadorPreguntas = 0;

    private void mostrarSiguientePregunta() {
        // Si el contador de las preguntas es menor que el array de preguntas, entra
        if (contadorPreguntas < elegirPreguntas.size()) {
            // Guardo en una variable la primera pregunta
            String pregunta = elegirPreguntas.get(contadorPreguntas);
            // Llamo al método que me devuelve la pregunta elegida
            PreguntasRespuestas preguntaYrespuesta = preguntasRespuestas.get(pregunta);
            // Almaceno las respuestas en un array
            ArrayList<String> respuesta = preguntaYrespuesta.getRespuestas();
            // Creo un contador de respuestas correctas
            int contadorCorrectas = preguntaYrespuesta.getRespuestaCorrecta();
            String respuestaCorrecta = respuesta.get(contadorCorrectas);

            CharSequence[] respuestaArray = respuesta.toArray(new CharSequence[0]);

            // Creo el alertDialog mostrando la pregunta con sus respuestas
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pregunta " + (contadorPreguntas + 1))
                    .setSingleChoiceItems(respuestaArray, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Guardo la respuesta del usuario
                            respuestaUsuario.add(which);
                            // Si el usuario no ha elegido ninguna opcion...
                            if(which == -1){
                                mensaje.mostrarToastcorto(Menuu.this, "Debes elegir una opción");
                            } else {
                                // Si la opcion elegida es igual a la opcion correcta...
                                if (which == contadorPreguntas){
                                    mensaje.mostrarToastcorto(Menuu.this, "¡CORRECTO!");
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
            // Todas las preguntas han sido respondidas
            // Puedes hacer cualquier otra acción necesaria aquí
        }
    }

}