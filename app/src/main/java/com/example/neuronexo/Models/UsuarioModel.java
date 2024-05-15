package com.example.neuronexo.Models;

public class UsuarioModel {

    private int id;


    private boolean sexo;
    private int edad;

    public UsuarioModel(boolean sexo, int edad) {

        this.sexo = sexo;
        this.edad = edad;
    }



    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public UsuarioModel(){

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
