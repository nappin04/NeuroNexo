package com.example.neuronexo.Models;


/* Me creo una clase que contenga una variable común de puntos que me servirá como
   contenedor o almacén de los puntos que vaya obteniendo
 */
public class PuntosTotales {
    private static int totalPuntos = 0;

    private static int intentosJugados = 0;

    public static int getIntentosJugados() {
        return intentosJugados;
    }

    public static void setIntentosJugados(int intentosJugados) {
        PuntosTotales.intentosJugados = intentosJugados;
    }

    public static int getPuntosTotales(){
        return totalPuntos;
    }

    // Este método lo usaré en los niveles, para ir guardando los puntos que obtenga
    public static void setPuntosTotales(int puntos){
        totalPuntos += puntos;
    }
}
