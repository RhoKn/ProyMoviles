package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AlumnActivity extends AppCompatActivity {

    private ListView listAlumns;
    //Declaración del spinner y su Adapter
    private Spinner spinAlumnos;
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<Alumno> lista;
    private Alumno al;

    //Controlador de bases de datos
    private MyOpenHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listAlumns = (ListView)findViewById(R.id.listAlumns);
        listAlumns.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Intent aaddCActiv = new Intent(view.getContext(), AddClassesActivity.class);
                aaddCActiv.putExtra("alumnId", Integer.toString(((Alumno) item).getId()));
                aaddCActiv.putExtra("alumnNm", ((Alumno) item).getName());
                startActivityForResult(aaddCActiv,0);
            }

        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new MyOpenHelper(this);
        init();
    }

    private void init (){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {startNewAlumn(v);});
        lista = db.getAlumns();
        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        listAlumns.setAdapter(spinnerAdapter);
    }

    private void startNewAlumn(View v){
        Intent newAlumnActivity = new Intent(v.getContext(), NAlumnActivity.class);
        startActivityForResult(newAlumnActivity,0);
    }
}
