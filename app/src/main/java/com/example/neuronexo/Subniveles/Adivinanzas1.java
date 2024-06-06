package com.example.neuronexo.Subniveles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.neuronexo.DAO.PreguntasRespuestas;
import com.example.neuronexo.DAO.UsuarioDAO;
import com.example.neuronexo.Menu.Registro;
import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Models.UsuarioModel;
import com.example.neuronexo.Niveles.Nivel1;
import com.example.neuronexo.Niveles.Nivel2;
import com.example.neuronexo.Niveles.Nivel3;
import com.example.neuronexo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Adivinanzas1 extends AppCompatActivity {

    private Button adivinanzas, salir;
    private ToastMensajes mensaje = new ToastMensajes();
    private int respuestaCorrecta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivinanzas1);
        adivinanzas = findViewById(R.id.bt_adivinanzas);
        salir = findViewById(R.id.bt_salir);

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
        adi1respuesta.add("La pera");
        adi1respuesta.add("La manzana");
        adi1respuesta.add("Ninguna de las dos");

        ArrayList<String> adi2respuesta = new ArrayList<>();
        adi2respuesta.add("El zorro");
        adi2respuesta.add("Un leon");
        adi2respuesta.add("El conejo");

        ArrayList<String> adi3respuesta = new ArrayList<>();
        adi3respuesta.add("El reloj");
        adi3respuesta.add("El erizo");
        adi3respuesta.add("Ninguna de las dos");

        ArrayList<String> adi4respuesta = new ArrayList<>();
        adi4respuesta.add("Una mariposa");
        adi4respuesta.add("Un murciélago");
        adi4respuesta.add("Un búho");

        // Asocio las adivinanzas con sus respuestas y la respuesta correcta
        preguntasAdivinanzas.put("Blanca por dentro, verde por fuera. Si quieres que te lo diga, espera", new PreguntasRespuestas("Blanca por dentro, verde por fuera. Si quieres que te lo diga, espera", adi1respuesta, 0)); // La respuesta correcta está en el índice 0
        preguntasAdivinanzas.put("Tengo orejas largas y también muy buena vista. Si me echas una carrera, seguro que me despistas", new PreguntasRespuestas("Tengo orejas largas y también muy buena vista. Si me echas una carrera, seguro que me despistas", adi2respuesta, 2)); // La respuesta correcta está en el índice 2
        preguntasAdivinanzas.put("Tengo agujas y no sé coser", new PreguntasRespuestas("Tengo agujas y no sé coser", adi3respuesta, 0)); // La respuesta correcta está en el índice 0
        preguntasAdivinanzas.put("Vuelo de noche, duermo en el día y nunca verás plumas en ala mía", new PreguntasRespuestas("Vuelo de noche, duermo en el día y nunca verás plumas en ala mía", adi4respuesta, 1)); // La respuesta correcta está en el índice 1

        // Selecciono aleatoriamente 4 preguntas de las disponibles
        elegirAdivinanzas = new ArrayList<>(preguntasAdivinanzas.keySet());
        Collections.shuffle(elegirAdivinanzas);
        elegirAdivinanzas = new ArrayList<>(elegirAdivinanzas.subList(0, 4));
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
                                        mensaje.mostrarToastcorto(Adivinanzas1.this, "Debes elegir una opción");
                                    } else {
                                        respuestaUsuario.add(selectedId);
                                        if (selectedId == indiceCorrecto) {
                                            mensaje.mostrarToastcorto(Adivinanzas1.this, "¡CORRECTO!");
                                            respuestaCorrecta++;
                                        } else {
                                            mensaje.mostrarToastcorto(Adivinanzas1.this, "INCORRECTO");
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
                    mensaje.mostrarToastcorto(Adivinanzas1.this, "Error en las respuestas de la adivinanza: " + adivinanza);
                }
            } else {
                // Manejo el caso donde la pregunta no se encuentra en el mapa
                mensaje.mostrarToastcorto(Adivinanzas1.this, "No se encontró la pregunta: " + adivinanza);
            }
        } else {
            // Todas las preguntas fueron respondidas
            if (respuestaCorrecta == 3) {
                mensaje.mostrarToastcorto(Adivinanzas1.this, "¡CONSEGUIDO!");
                Intent intent = new Intent(Adivinanzas1.this, Nivel1.class);
                startActivity(intent);
                finish(); // Finalizo la actividad actual
            } else {
                mensaje.mostrarToastcorto(Adivinanzas1.this, "No has superado el nivel. Inténtalo de nuevo.");
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
