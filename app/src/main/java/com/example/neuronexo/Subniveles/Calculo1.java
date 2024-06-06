package com.example.neuronexo.Subniveles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.Niveles.Nivel1;
import com.example.neuronexo.R;

public class Calculo1 extends AppCompatActivity {
    private EditText operacion1, operacion2, operacion3, operacion4, operacion5;
    private Button terminar, atras;
    private TextView crono;
    private ToastMensajes mensaje = new ToastMensajes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo1);
        operacion1 = findViewById(R.id.et_valorOp1);
        operacion2 = findViewById(R.id.et_valorOp2);
        operacion3 = findViewById(R.id.et_valorOp3);
        operacion4 = findViewById(R.id.et_valorOp4);
        operacion5 = findViewById(R.id.et_valorOp5);
        terminar = findViewById(R.id.bt_terminar);
        crono = findViewById(R.id.tv_crono2);
        atras = findViewById(R.id.bt_atrasCalculo1);

        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                crono.setText("Segundos restantes, "+ millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                crono.setText("¡TIEMPO AGOTADO!");
                ejercicio(crono);
            }
        }.start();
    }

    public void ejercicio(View v){
        if(crono.getText().toString().equals("¡TIEMPO AGOTADO!")){
            AlertDialog.Builder builder = new AlertDialog.Builder(Calculo1.this);
            builder.setTitle("Se acabó el tiempo")
                    .setPositiveButton("Terminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Calculo1.this, Nivel1.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setCancelable(false).show();


        }else{
            if(operacion1.getText().toString().isEmpty() ||
                    operacion2.getText().toString().isEmpty() ||
                    operacion3.getText().toString().isEmpty() ||
                    operacion4.getText().toString().isEmpty() ||
                    operacion5.getText().toString().isEmpty()){
                mensaje.mostrarToastcorto(Calculo1.this, "Debes realizar todas las operaciones");

            }
            else if(operacion1.getText().toString().equals("11") &&
                    operacion2.getText().toString().equals("27") &&
                    operacion3.getText().toString().equals("80") &&
                    operacion4.getText().toString().equals("169") &&
                    operacion5.getText().toString().equals("571")){

            }else{
                mensaje.mostrarToastcorto(Calculo1.this,"Uy, alguno no es correcto");

            }
        }

    }

    public void Salir(View v){
        finish();
    }
}