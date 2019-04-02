package com.example.rubengonzalez.proyect1moviles;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddClassesActivity extends AppCompatActivity {
    private String alumnName;
    private int alumnId;
    private MyOpenHelper db;
    private ArrayList<Class> lista;
    private Class cl;
    private boolean[] checkedItems;
    private ArrayList<Integer> checkedClassesList;
    private Button btnAC;
    private TextView v2Show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classes);
        Intent intent = getIntent();
        db = new MyOpenHelper(this);
        alumnName = intent.getStringExtra("alumnNm");
        alumnId = Integer.parseInt(intent.getStringExtra("alumnId"));
        ini();

    }
    public void ini(){
        btnAC = (Button)findViewById(R.id.idAddClassesToAl);
        v2Show = (TextView) findViewById(R.id.tvShowSelectedClass);
        lista = db.getClasses();
        checkedItems = new boolean[lista.size()];

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder blder = new AlertDialog.Builder(AddClassesActivity.this);
                blder.setTitle("Clases para asignar");
                blder.setMultiChoiceItems(lista, checkedItems, new DialogInterface.OnMultiChoiceClickListener(){

                });
                blder
            }
        });
    }
}
