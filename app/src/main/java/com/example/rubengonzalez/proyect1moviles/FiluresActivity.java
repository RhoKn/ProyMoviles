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
        lista = db.getalClass();
        System.out.println(lista);
        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        listAlsCls.setAdapter(spinnerAdapter);
    }
}
