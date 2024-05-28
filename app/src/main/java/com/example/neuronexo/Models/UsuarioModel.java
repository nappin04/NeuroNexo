package com.example.neuronexo.Models;




import androidx.annotation.NonNull;

public class UsuarioModel{

    private int id, nivel, puntos;


    private String sexo,edad;


    public UsuarioModel() {
    }

    public UsuarioModel(String sexo, String edad, int nivel, int puntos) {
        this.nivel = nivel;
        this.puntos = puntos;
        this.sexo = sexo;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
