package com.example.neuronexo.Subniveles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Niveles.Nivel1;
import com.example.neuronexo.R;

public class Colores1 extends AppCompatActivity {

    private EditText et1, et2, et3, et4;
    private TextView crono;
    private ToastMensajes toastMensajes = new ToastMensajes();
    private Button salir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        crono = findViewById(R.id.tv_crono);
        salir = findViewById(R.id.bt_salirColores1);
        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                crono.setText("Segundos restantes: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                crono.setText("¡TIEMPO AGOTADO!");
                ejercicio(crono);
            }
        }.start();
    }

    public void ejercicio(View v) {
        if(crono.getText().toString().equals("¡TIEMPO AGOTADO!")){
            AlertDialog.Builder builder = new AlertDialog.Builder(Colores1.this);
            builder.setTitle("Se acabó el tiempo")
                    .setPositiveButton("Terminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Colores1.this, Nivel1.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setCancelable(false).show();


        }else{
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

        }

    public void volver() {
        finish();
    }

    public void Salir(View v){
        finish();
    }
}