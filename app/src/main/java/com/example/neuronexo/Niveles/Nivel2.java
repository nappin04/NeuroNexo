package com.example.neuronexo.Niveles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.neuronexo.R;
import com.example.neuronexo.Subniveles.Adivinanzas2;
import com.example.neuronexo.Subniveles.Calculo2;
import com.example.neuronexo.Subniveles.Colores2;
import com.example.neuronexo.Subniveles.Memoria2;

public class Nivel2 extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel2);

        listView = findViewById(R.id.ListaOpciones2);

        // Opciones para el lista
        String[] opciones = new String[]{"Colores 2", "Cálculo 2", "Memoria 2", "Adivinanzas 2"};

        // Creo un adaptador para la lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.opciones_lista, opciones);

        // Asigno el adaptador al lista
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent(Nivel2.this, Colores2.class);
                    startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent(Nivel2.this, Calculo2.class);
                    startActivity(intent);
                }else if(position == 2){
                    Intent intent = new Intent(Nivel2.this, Memoria2.class);
                    startActivity(intent);
                }else if(position == 3){
                    Intent intent = new Intent(Nivel2.this, Adivinanzas2.class);
                    startActivity(intent);
                }
            }
        });
    }
}