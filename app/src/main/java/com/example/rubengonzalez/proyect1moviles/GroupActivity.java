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

public class GroupActivity extends AppCompatActivity {

    private ListView listGroups;
    //Declaración del spinner y su Adapter
    private Spinner spinGroups;
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<Group> lista;
    private Group gr;

    //Controlador de bases de datos
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listGroups = (ListView)findViewById(R.id.listGroups);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {startNewGP(v);});
        db = new MyOpenHelper(this);
        init();
    }

    public void init(){
        lista = db.getGroups();
        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        listGroups.setAdapter(spinnerAdapter);
    }
    private void startNewGP(View v){
        Intent startNewGP = new Intent(v.getContext(), NGroupActivity.class);
        startActivityForResult(startNewGP,0);
    }
}
