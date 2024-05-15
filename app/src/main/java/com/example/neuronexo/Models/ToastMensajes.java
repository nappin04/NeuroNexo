package com.example.neuronexo.Models;

import android.content.Context;
import android.widget.Toast;

public class ToastMensajes {
    public static void mostrarToastcorto(Context context, String mensaje){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
    public static void mostrarToastlargo(Context context, String mensaje){
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }
}
