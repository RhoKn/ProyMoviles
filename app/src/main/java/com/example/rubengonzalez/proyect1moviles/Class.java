package com.example.rubengonzalez.proyect1moviles;

public class Class {
    private String name;
    private  String matricula;
    private int grade;
    private int faltas;
    private int id;

    @Override
    public String toString() {
        return " " +name+" ------ "+matricula+"     "+faltas;
    }

    public Class(int id, String name, String matricula, int grade, int faltas){
        this.name=name;
        this.matricula=matricula;
        this.grade=grade;
        this.faltas=faltas;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
