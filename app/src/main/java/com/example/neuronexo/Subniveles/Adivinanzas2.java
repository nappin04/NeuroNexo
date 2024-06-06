package com.example.neuronexo.Subniveles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.neuronexo.DAO.PreguntasRespuestas;
import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Niveles.Nivel1;
import com.example.neuronexo.Niveles.Nivel2;
import com.example.neuronexo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Adivinanzas2 extends AppCompatActivity {

    private Button adivinanzas, salir;
    private ToastMensajes mensaje = new ToastMensajes();
    private int respuestaCorrecta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivinanzas2);
        adivinanzas = findViewById(R.id.bt_adivinanzas2);
        salir = findViewById(R.id.bt_salirAdi2);

        crearAdivinanzas();
        adivinanzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarAdivinanzas();
            }
        });
    }

    private HashMap<String, PreguntasRespuestas> preguntasAdivinanzas = new HashMap<>();
    private ArrayList<String> elegirAdivinanzas;
    private ArrayList<Integer> respuestaUsuario = new ArrayList<>();

    private void crearAdivinanzas() {
        // Defino las preguntas y sus respuestas (una correcta y dos incorrecta)
        ArrayList<String> adi1respuesta = new ArrayList<>();
        adi1respuesta.add("El aire");
        adi1respuesta.add("El fuego");
        adi1respuesta.add("La sombra");
        adi1respuesta.add("La arena");

        ArrayList<String> adi2respuesta = new ArrayList<>();
        adi2respuesta.add("El aire");
        adi2respuesta.add("Un agujero");
        adi2respuesta.add("Una burbuja");
        adi2respuesta.add("Un corcho");

        ArrayList<String> adi3respuesta = new ArrayList<>();
        adi3respuesta.add("El amor");
        adi3respuesta.add("El dinero");
        adi3respuesta.add("El aire");
        adi3respuesta.add("La amistad");

        ArrayList<String> adi4respuesta = new ArrayList<>();
        adi4respuesta.add("Un disco duro");
        adi4respuesta.add("El bolsillo");
        adi4respuesta.add("El teléfono");
        adi4respuesta.add("Una memoria USB");

        ArrayList<String> adi5respuesta = new ArrayList<>();
        adi5respuesta.add("El silencio");
        adi5respuesta.add("La sombra");
        adi5respuesta.add("El viento");
        adi5respuesta.add("El sueño");

        ArrayList<String> adi6respuesta = new ArrayList<>();
        adi6respuesta.add("Un rompecabezas");
        adi6respuesta.add("Un libro");
        adi6respuesta.add("Un mapa");
        adi6respuesta.add("Una pintura");

        ArrayList<String> adi7respuesta = new ArrayList<>();
        adi7respuesta.add("La temperatura");
        adi7respuesta.add("El sol");
        adi7respuesta.add("El ascensor");
        adi7respuesta.add("La escalera");

        // Asocio las adivinanzas con sus respuestas y la respuesta correcta
        preguntasAdivinanzas.put("En el mar no se moja, y en la cocina no se quema. ¿Qué es?", new PreguntasRespuestas("En el mar no se moja, y en la cocina no se quema. ¿Qué es?", adi1respuesta, 2)); // La respuesta correcta está en el índice 2
        preguntasAdivinanzas.put("No pesa nada, pero si la pones en un cubo de agua, este pesará menos. ", new PreguntasRespuestas("No pesa nada, pero si la pones en un cubo de agua, este pesará menos. ", adi2respuesta, 1)); // La respuesta correcta está en el índice 1
        preguntasAdivinanzas.put("Siempre va de mano en mano, pero no se puede sostener. ¿Qué es?", new PreguntasRespuestas("Siempre va de mano en mano, pero no se puede sostener. ¿Qué es?", adi3respuesta, 0)); // La respuesta correcta está en el índice 0
        preguntasAdivinanzas.put("Soy pequeño como un ratón, pero guardo más cosas que un gran cajón. ¿Qué soy?", new PreguntasRespuestas("Soy pequeño como un ratón, pero guardo más cosas que un gran cajón. ¿Qué soy?", adi4respuesta, 0)); // La respuesta correcta está en el índice 0
        preguntasAdivinanzas.put("Si me nombras, desaparezco. ¿Qué soy?", new PreguntasRespuestas("Si me nombras, desaparezco. ¿Qué soy?", adi5respuesta, 0)); // La respuesta correcta está en el índice 0
        preguntasAdivinanzas.put("Tengo ciudades, pero no casas; tengo montañas, pero no árboles; tengo agua, pero no peces. ¿Qué soy?", new PreguntasRespuestas("Tengo ciudades, pero no casas; tengo montañas, pero no árboles; tengo agua, pero no peces. ¿Qué soy?", adi6respuesta, 2)); // La respuesta correcta está en el índice 2
        preguntasAdivinanzas.put("¿Qué sube y baja pero no se mueve?", new PreguntasRespuestas("¿Qué sube y baja pero no se mueve?", adi7respuesta, 2)); // La respuesta correcta está en el índice 2

        // Selecciono aleatoriamente 6 preguntas de las disponibles
        elegirAdivinanzas = new ArrayList<>(preguntasAdivinanzas.keySet());
        Collections.shuffle(elegirAdivinanzas);
        elegirAdivinanzas = new ArrayList<>(elegirAdivinanzas.subList(0, 6));
    }

    private int contadorAdivinanzas = 0;

    private void mostrarAdivinanzas() {
        // Si el contador de las preguntas es menor que el array de preguntas, entra
        if (contadorAdivinanzas < elegirAdivinanzas.size()) {
            // Guardo en una variable la primera pregunta
            String adivinanza = elegirAdivinanzas.get(contadorAdivinanzas);
            // Llamo al método que me devuelve la pregunta elegida
            PreguntasRespuestas adivinanzaYrespuesta = preguntasAdivinanzas.get(adivinanza);

            if (adivinanzaYrespuesta != null) {
                // Almaceno las respuestas en un array
                ArrayList<String> respuesta = adivinanzaYrespuesta.getRespuestas();
                // Creo un contador de respuestas correctas
                int indiceCorrecto = adivinanzaYrespuesta.getRespuestaCorrecta();

                if (indiceCorrecto >= 0 && indiceCorrecto < respuesta.size()) {

                    // Infla el diseño personalizado para el AlertDialog
                    LayoutInflater inflater = this.getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.adivinanzas_personalizadas, null);

                    TextView questionTextView = dialogView.findViewById(R.id.question);
                    RadioGroup answersRadioGroup = dialogView.findViewById(R.id.answers);

                    questionTextView.setText("Pregunta " + (contadorAdivinanzas + 1) + ": " + adivinanza);

                    // Añade las respuestas al RadioGroup
                    for (int i = 0; i < respuesta.size(); i++) {
                        RadioButton radioButton = new RadioButton(this);
                        radioButton.setText(respuesta.get(i));
                        radioButton.setId(i);
                        answersRadioGroup.addView(radioButton);
                    }

                    // Crea el AlertDialog
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    builder.setView(dialogView)
                            .setCancelable(false)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    int selectedId = answersRadioGroup.getCheckedRadioButtonId();
                                    if (selectedId == -1) {
                                        mensaje.mostrarToastcorto(Adivinanzas2.this, "Debes elegir una opción");
                                    } else {
                                        respuestaUsuario.add(selectedId);
                                        if (selectedId == indiceCorrecto) {
                                            mensaje.mostrarToastcorto(Adivinanzas2.this, "¡CORRECTO!");
                                            respuestaCorrecta++;
                                        } else {
                                            mensaje.mostrarToastcorto(Adivinanzas2.this, "INCORRECTO");
                                        }
                                        // Paso a la siguiente pregunta
                                        contadorAdivinanzas++;
                                        dialog.dismiss();
                                        mostrarAdivinanzas();
                                    }
                                }
                            });

                    android.app.AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    // Manejo el caso donde el índice de la respuesta correcta está fuera de rango
                    mensaje.mostrarToastcorto(Adivinanzas2.this, "Error en las respuestas de la adivinanza: " + adivinanza);
                }
            } else {
                // Manejo el caso donde la pregunta no se encuentra en el mapa
                mensaje.mostrarToastcorto(Adivinanzas2.this, "No se encontró la pregunta: " + adivinanza);
            }
        } else {
            // Todas las preguntas fueron respondidas
            if (respuestaCorrecta == 3) {
                mensaje.mostrarToastcorto(Adivinanzas2.this, "¡CONSEGUIDO!");
                Intent intent = new Intent(Adivinanzas2.this, Nivel2.class);
                startActivity(intent);
                finish(); // Finalizo la actividad actual
            } else {
                mensaje.mostrarToastcorto(Adivinanzas2.this, "No has superado el nivel. Inténtalo de nuevo.");
                reiniciarJuego();
            }
        }
    }

    private void reiniciarJuego() {
        respuestaCorrecta = 0;
        contadorAdivinanzas = 0;
        respuestaUsuario.clear();
        crearAdivinanzas(); // Reorganiza las preguntas
    }

    public void Salir(View v){
        finish();
    }
}
