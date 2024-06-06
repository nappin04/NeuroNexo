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
import com.example.neuronexo.Niveles.Nivel2;
import com.example.neuronexo.R;

public class Calculo2 extends AppCompatActivity {

    private EditText operacion1, operacion2, operacion3, operacion4, operacion5;
    private Button terminar, atras;
    private TextView crono;
    private ToastMensajes mensaje = new ToastMensajes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo2);
        operacion1 = findViewById(R.id.et_valorOp1N2);
        operacion2 = findViewById(R.id.et_valorOp2N2);
        operacion3 = findViewById(R.id.et_valorOp3N2);
        operacion4 = findViewById(R.id.et_valorOp4N2);
        operacion5 = findViewById(R.id.et_valorOp5N2);
        terminar = findViewById(R.id.bt_terminarN2);
        crono = findViewById(R.id.tv_cronoN2Calculo);
        atras = findViewById(R.id.bt_atrasCalculo2);

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
            AlertDialog.Builder builder = new AlertDialog.Builder(Calculo2.this);
            builder.setTitle("Se acabó el tiempo")
                    .setPositiveButton("Terminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Calculo2.this, Nivel2.class);
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
                mensaje.mostrarToastcorto(Calculo2.this, "Debes realizar todas las operaciones");

            }
            else if(operacion1.getText().toString().equals("11") &&
                    operacion2.getText().toString().equals("27") &&
                    operacion3.getText().toString().equals("80") &&
                    operacion4.getText().toString().equals("169") &&
                    operacion5.getText().toString().equals("571")){

            }else{
                mensaje.mostrarToastcorto(Calculo2.this,"Uy, alguno no es correcto");

            }
        }

    }

    public void Salir(View v){
        finish();
    }
}