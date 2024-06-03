package com.example.neuronexo.Subniveles;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.example.neuronexo.Models.ToastMensajes;
import com.example.neuronexo.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Memoria1 extends AppCompatActivity {

    private ImageButton[] buttons = new ImageButton[8];
    private Integer[] colors = {Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.YELLOW, Color.YELLOW};
    private int firstButtonIndex = -1;
    private int secondButtonIndex = -1;
    private boolean isClickable = true;
    private Handler handler = new Handler();
    private ToastMensajes toastMensajes = new ToastMensajes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria1);

        buttons[0] = findViewById(R.id.ib1);
        buttons[1] = findViewById(R.id.ib2);
        buttons[2] = findViewById(R.id.ib3);
        buttons[3] = findViewById(R.id.ib4);
        buttons[4] = findViewById(R.id.ib5);
        buttons[5] = findViewById(R.id.ib6);
        buttons[6] = findViewById(R.id.ib7);
        buttons[7] = findViewById(R.id.ib8);

        List<Integer> colorList = Arrays.asList(colors);
        Collections.shuffle(colorList);
        colorList.toArray(colors);

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(v -> onButtonClicked(index));
        }

        resetButtons();
    }

    private void onButtonClicked(int index) {
        if (!isClickable) return;
        if (firstButtonIndex == index) return;

        buttons[index].setBackgroundColor(colors[index]);

        if (firstButtonIndex == -1) {
            firstButtonIndex = index;
        } else {
            secondButtonIndex = index;
            isClickable = false;

            handler.postDelayed(this::checkForMatch, 1000);
        }
    }

    private void checkForMatch() {
        if (colors[firstButtonIndex].equals(colors[secondButtonIndex])) {
            buttons[firstButtonIndex].setVisibility(View.INVISIBLE);
            buttons[secondButtonIndex].setVisibility(View.INVISIBLE);
            toastMensajes.mostrarToastcorto(Memoria1.this, "Pareja acertada");
        } else {
            buttons[firstButtonIndex].setBackgroundColor(Color.GRAY);
            buttons[secondButtonIndex].setBackgroundColor(Color.GRAY);
            toastMensajes.mostrarToastcorto(Memoria1.this, "Pareja incorrecta");
        }

        // Verificar si todos los botones son invisibles
        boolean allInvisible = true;
        for (ImageButton button : buttons) {
            if (button.getVisibility() == View.VISIBLE) {
                allInvisible = false;
                break;
            }
        }

        // Si todos los botones son invisibles, mostrar un mensaje al usuario
        if (allInvisible) {
            toastMensajes.mostrarToastcorto(Memoria1.this, "¡LO HAS LOGRADO!");
            finish();
        }

        firstButtonIndex = -1;
        secondButtonIndex = -1;
        isClickable = true;
    }

    private void resetButtons() {
        for (ImageButton button : buttons) {
            button.setBackgroundColor(Color.GRAY);
            button.setVisibility(View.VISIBLE);
        }
    }



}
