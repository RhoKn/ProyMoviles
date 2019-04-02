package com.example.rubengonzalez.proyect1moviles;

public class Alumno {
    int id;
    String name;
    String lastName;
    Integer grade;
    String group;

    //Constructor
    public Alumno(int _id, String _name, String _lastName, Integer _grade, String _group){
        id=_id;
        name = _name;
        lastName = _lastName;
        grade = _grade;
        group = _group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
