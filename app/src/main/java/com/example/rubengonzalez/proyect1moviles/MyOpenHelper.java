package com.example.rubengonzalez.proyect1moviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String ALUMNS_TABLE_CREATE = "CREATE TABLE alumns(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, lastName TEXT, grade INTEGER, aGroup TEXT)";
    private static final String CLASS_TABLE_CREATE = "CREATE TABLE class(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, matricula TEXT, grade INTEGER, falta TEXT)";
    private static final String GROUPS_TABLE_CREATE = "CREATE TABLE groups(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
    private static final String ALCLAS_TABLE_CREATE = "CREATE TABLE alclass(_id INTEGER PRIMARY KEY AUTOINCREMENT, idAlumn INTEGER, idClass INTEGER, faltasPermitidad INTEGER, faltas INTEGER)";
    private static final String DB_NAME = "alumns.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ALUMNS_TABLE_CREATE);
        db.execSQL(CLASS_TABLE_CREATE);
        db.execSQL(GROUPS_TABLE_CREATE);
        db.execSQL(ALCLAS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertar(String name,String lastName,Integer grade, String aGroup){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("lastName", lastName);
        cv.put("grade", grade);
        cv.put("aGroup", aGroup);
        db.insert("alumns", null, cv);
    }

    public void borrar(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("alumns", "_id=?", args);
    }

    public ArrayList<Alumno> getAlumns(){
        ArrayList<Alumno> lista=new ArrayList<Alumno>();
        Cursor c = db.rawQuery("select _id, name,lastName, grade, aGroup from alumns", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                String name = c.getString(c.getColumnIndex("name"));
                String lastName = c.getString(c.getColumnIndex("lastName"));
                Integer grade = c.getInt(c.getColumnIndex("grade"));
                String aGroup = c.getString(c.getColumnIndex("aGroup"));
                int id=c.getInt(c.getColumnIndex("_id"));
                Alumno al =new Alumno(id,name,lastName, grade, aGroup);
                lista.add(al);
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }


    //------------Class
    public void insertarClass(String name,String matricula,int grade, int falta){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("matricula", matricula);
        cv.put("grade", grade);
        cv.put("falta", falta);
        db.insert("class", null, cv);
    }

    public void borrarClass(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("class", "_id=?", args);
    }

    public ArrayList<Class> getClasses(){
        ArrayList<Class> lista=new ArrayList<Class>();
        Cursor c = db.rawQuery("select _id, name,matricula, grade, falta from class", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                String name = c.getString(c.getColumnIndex("name"));
                String matricula = c.getString(c.getColumnIndex("matricula"));
                Integer grade = c.getInt(c.getColumnIndex("grade"));
                Integer falta = c.getInt(c.getColumnIndex("falta"));
                int id=c.getInt(c.getColumnIndex("_id"));
                Class cl =new Class(id,name,matricula, grade, falta);
                lista.add(cl);
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }
    public ArrayList<String> getClassesString(){
        ArrayList<String> lista=new ArrayList<String>();
        Cursor c = db.rawQuery("select _id, name,matricula, grade, falta from class", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                String name = c.getString(c.getColumnIndex("name"));
                String matricula = c.getString(c.getColumnIndex("matricula"));
                Integer grade = c.getInt(c.getColumnIndex("grade"));
                Integer falta = c.getInt(c.getColumnIndex("falta"));
                int id=c.getInt(c.getColumnIndex("_id"));
                Class cl =new Class(id,name,matricula, grade, falta);
                lista.add(cl.toString());
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }

    //------------Group
    public void insertarGroup(String name){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        db.insert("groups", null, cv);
    }

    public void borrarGroups(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("groups", "_id=?", args);
    }

    public ArrayList<Group> getGroups(){
        ArrayList<Group> lista=new ArrayList<Group>();
        Cursor c = db.rawQuery("select _id, name from groups", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                String name = c.getString(c.getColumnIndex("name"));
                int id=c.getInt(c.getColumnIndex("_id"));
                Group gr =new Group(id,name);
                lista.add(gr);
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }

    //------------alClass
    public void insertaralClass(int idAlumn, int idClass, int faltasPermitidad, int faltas){
        ContentValues cv = new ContentValues();
        cv.put("idAlumn", idAlumn);
        cv.put("idClass", idClass);
        cv.put("faltasPermitidad", faltasPermitidad);
        cv.put("faltas", faltas);
        db.insert("alclass", null, cv);
    }

    public void borraralClass(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("alclass", "_id=?", args);
    }

    public ArrayList<AlumnosMaterias> getalClass(){
        ArrayList<AlumnosMaterias> lista=new ArrayList<AlumnosMaterias>();
        Cursor c = db.rawQuery("select * from alclass", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                int idAlumn = c.getInt(c.getColumnIndex("idAlumn"));
                int idClass = c.getInt(c.getColumnIndex("idClass"));
                int faltasPermitidad = c.getInt(c.getColumnIndex("faltasPermitidad"));
                int faltas = c.getInt(c.getColumnIndex("faltas"));
                int id=c.getInt(c.getColumnIndex("_id"));
                AlumnosMaterias al =new AlumnosMaterias(id,idAlumn,idClass, faltasPermitidad, faltas);

                lista.add(al);
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }

    public ArrayList<Class> getAlumnClass(String idAl){
        ArrayList<AlumnosMaterias> lista=new ArrayList<AlumnosMaterias>();
        Cursor c = db.rawQuery("select * from alclass where idAlumn = ?",new String[]{idAl});
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                int idAlumn = c.getInt(c.getColumnIndex("idAlumn"));
                int idClass = c.getInt(c.getColumnIndex("idClass"));
                int faltasPermitidad = c.getInt(c.getColumnIndex("faltasPermitidad"));
                int faltas = c.getInt(c.getColumnIndex("faltas"));
                int id=c.getInt(c.getColumnIndex("_id"));
                AlumnosMaterias al =new AlumnosMaterias(id,idAlumn,idClass, faltasPermitidad, faltas);

                lista.add(al);
            } while (c.moveToNext());
        }
        c.close();

        ArrayList<Class> listaC=new ArrayList<Class>();
        for (int i = 0; i < lista.size(); i++){
            Cursor d = db.rawQuery("select * from class where _id = ?", new String[]{Integer.toString(lista.get(i).getIdClass())});
            if (d != null && d.getCount()>0) {
                d.moveToFirst();
                do {
                    String name = d.getString(d.getColumnIndex("name"));
                    String matricula = d.getString(d.getColumnIndex("matricula"));
                    Integer grade = d.getInt(d.getColumnIndex("grade"));
                    Integer falta = d.getInt(d.getColumnIndex("falta"));
                    int idc= d.getInt(d.getColumnIndex("_id"));
                    Class cl =new Class(idc,name,matricula, grade, falta);
                    listaC.add(cl);
                } while (d.moveToNext());
            }

            d.close();
        }
        return listaC;
    }
}