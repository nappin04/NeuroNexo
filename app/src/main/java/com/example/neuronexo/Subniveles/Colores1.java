package com.example.neuronexo.Subniveles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Niveles.Nivel1;
import com.example.neuronexo.R;

public class Colores1 extends AppCompatActivity {

    private EditText et1, et2, et3, et4;
    private ToastMensajes toastMensajes = new ToastMensajes();
    private int intentos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
    }

    public void ejercicio(View v) {
        if (et1.getText().toString().isEmpty() ||
                et2.getText().toString().isEmpty() ||
                et3.getText().toString().isEmpty() ||
                et4.getText().toString().isEmpty()) {

            toastMensajes.mostrarToastcorto(Colores1.this, "No puedes dejar ninguno vacío");
        } else if (et1.getText().toString().equals("rojo") &&
                et2.getText().toString().equals("azul") &&
                et3.getText().toString().equals("verde") &&
                et4.getText().toString().equals("amarillo")) {

            toastMensajes.mostrarToastcorto(Colores1.this, "CORRECTO");
            volver();
        } else {
            toastMensajes.mostrarToastcorto(Colores1.this, "Uy, alguno está mal");
        }
    }

    public void volver() {
        finish();
    }
}