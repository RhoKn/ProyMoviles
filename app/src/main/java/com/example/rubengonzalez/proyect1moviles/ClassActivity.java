package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
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

public class ClassActivity extends AppCompatActivity {

    private ListView listClass;
    //Declaración del spinner y su Adapter
    private Spinner spinClasses;
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<Class> lista;
    private Class cl;

    //Controlador de bases de datos
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listClass = (ListView)findViewById(R.id.listClass);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {startNewClass(v);});
        db = new MyOpenHelper(this);
        init();
    }

    public void init(){
        lista = db.getClasses();
        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        listClass.setAdapter(spinnerAdapter);
    }
    private void startNewClass(View v){
        Intent newClassActivity = new Intent(v.getContext(), NClassActivity.class);
        startActivityForResult(newClassActivity,0);
    }

}
