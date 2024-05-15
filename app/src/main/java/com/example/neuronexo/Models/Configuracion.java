package com.example.neuronexo.Models;

public class Configuracion {
    public static boolean estadoMusica = true;

    public static boolean isMusicaActivada(){

        return estadoMusica;
    }
    public static void setMusicaActiva(boolean estado){

        estadoMusica = estado;

    }


}
