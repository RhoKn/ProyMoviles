package com.example.rubengonzalez.proyect1moviles;

public class AlumnosMaterias {
    private int idAlumn;
    private int idClass;
    private int id;
    private int faltasPermitidad;
    private int faltas;

    @Override
    public String toString() {
        return " " + idAlumn + " " + idClass;
    }

    public AlumnosMaterias(int id, int idAlumn, int idClass, int faltasPermitidad, int faltas) {
        this.idAlumn = idAlumn;
        this.idClass = idClass;
        this.id = id;
        this.faltasPermitidad = faltasPermitidad;
        this.faltas = faltas;
    }

    public int getIdAlumn() {
        return idAlumn;
    }

    public void setIdAlumn(int idAlumn) {
        this.idAlumn = idAlumn;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFaltasPermitidad() {
        return faltasPermitidad;
    }

    public void setFaltasPermitidad(int faltasPermitidad) {
        this.faltasPermitidad = faltasPermitidad;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
