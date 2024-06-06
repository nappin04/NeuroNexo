package com.example.neuronexo.Niveles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.neuronexo.R;
import com.example.neuronexo.Subniveles.Adivinanzas1;
import com.example.neuronexo.Subniveles.Calculo1;
import com.example.neuronexo.Subniveles.Colores1;
import com.example.neuronexo.Subniveles.Memoria1;

public class Nivel1 extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);

        listView = findViewById(R.id.ListaOpciones2);

        // Opciones para el lista
        String[] opciones = new String[]{"Colores", "Cálculo", "Memoria", "Adivinanzas"};

        // Creo un adaptador para la lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.opciones_lista, opciones);

        // Asigno el adaptador al lista
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent(Nivel1.this, Colores1.class);
                    startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent(Nivel1.this, Calculo1.class);
                    startActivity(intent);
                }else if(position == 2){
                    Intent intent = new Intent(Nivel1.this, Memoria1.class);
                    startActivity(intent);
                }else if(position == 3){
                    Intent intent = new Intent(Nivel1.this, Adivinanzas1.class);
                    startActivity(intent);
                }
            }
        });
    }
}