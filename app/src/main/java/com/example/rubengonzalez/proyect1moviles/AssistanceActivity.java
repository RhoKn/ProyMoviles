package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AssistanceActivity extends AppCompatActivity {
    private ListView listClass;
    private TextView returnMenu;
    //Declaración del spinner y su Adapter
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<Class> lista;
    private Class cl;

    //Controlador de bases de datos
    private MyOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance);
        returnMenu = (TextView)findViewById(R.id.backToACls);
        listClass = (ListView)findViewById(R.id.assistanceClassLst);
        listClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Intent listAlOfC = new Intent(view.getContext(), AssistanceAlumnsActivity.class);
                listAlOfC.putExtra("classId", Integer.toString(((Class) item).getId()));
                listAlOfC.putExtra("className", ((Class) item).getName());
                startActivityForResult(listAlOfC,0);
            }

        });

        db = new MyOpenHelper(this);
        init();

    }
    public void init(){
        lista = db.getClasses();

        returnMenu.setOnClickListener((v)->{returnToMenu(v);});
        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        listClass.setAdapter(spinnerAdapter);
    }
    public void returnToMenu(View v){
        Intent rtMenu = new Intent(v.getContext(), MenuActivity.class);
        startActivityForResult(rtMenu,0);
    }
}
