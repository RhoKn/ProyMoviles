package com.example.rubengonzalez.proyect1moviles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class FiluresActivity extends AppCompatActivity {

    private ListView listAlsCls;
    //Declaración del spinner y su Adapter
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<AlumnosMaterias> lista;
    private ArrayList<Alumno> alumns;
    private ArrayList<Class> classes;
    private ArrayList<String> ToShow;
    private AlumnosMaterias cl;

    //Controlador de bases de datos
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filures);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listAlsCls = (ListView)findViewById(R.id.listAlsCls);
        db = new MyOpenHelper(this);
        init();
    }
    public void init(){
        lista = db.getFailedCAl();

        alumns = db.getAlumns();
        classes = db.getClasses();
        ToShow = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++){
            String x = "";
            for (int j = 0; j < classes.size(); j++){
                if(classes.get(j).getId() == lista.get(i).getIdClass()){
                    x = x + classes.get(j).getName();
                }
            }
            for (int k = 0; k < classes.size(); k++){
                if(alumns.get(k).getId() == lista.get(i).getIdClass()){
                    x = x + "   " + alumns.get(k).getName();
                }
            }
            x = x + "   " + lista.get(i).getFaltas();
            ToShow.add(x);
        }
        System.out.println(ToShow);
        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,ToShow);
        listAlsCls.setAdapter(spinnerAdapter);
    }
}
