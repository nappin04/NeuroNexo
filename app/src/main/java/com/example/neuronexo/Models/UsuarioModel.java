package com.example.neuronexo.Models;

public class UsuarioModel {

    private int id;


    private String sexo,edad;


    public UsuarioModel(String sexo, String edad) {

        this.sexo = sexo;
        this.edad = edad;
    }

    public UsuarioModel() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
