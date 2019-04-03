package com.example.rubengonzalez.proyect1moviles;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddClassesActivity extends AppCompatActivity {
    private String alumnName;
    private int alumnId;
    private MyOpenHelper db;
    private ArrayList<Class> lista;
    private Class cl;

    private TextView nameToShow;
    private TextView backTAct;
    private ListView allClasses;
    private ArrayAdapter spinnerAdapter;

    private ArrayList<Class> lista2;
    private ListView classTB;
    private ArrayAdapter spinnerAdapter2;

    private ArrayList<Class> lista3;
    private Button asign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classes);
        Intent intent = getIntent();
        backTAct = (TextView)findViewById(R.id.back2AlmnsFASn);
        backTAct.setOnClickListener((v)->{returnToAl(v);});
        asign = (Button)findViewById(R.id.asignClassBtn);
        asign.setOnClickListener((v)->{ asignCl();});

        db = new MyOpenHelper(this);
        alumnName = intent.getStringExtra("alumnNm");
        alumnId = Integer.parseInt(intent.getStringExtra("alumnId"));
        nameToShow = (TextView)findViewById(R.id.tvShowAlNm);
        allClasses = (ListView)findViewById(R.id.classesTAddLs);
        allClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Class tmp = new Class(((Class)item).getId(),((Class)item).getName(),((Class)item).getMatricula(),((Class)item).getGrade(),((Class)item).getFaltas());
                lista2.add(tmp);
                lista.remove(position);

                System.out.println("Se removio posicion " + position);
                spinnerAdapter2.notifyDataSetChanged();
                spinnerAdapter.notifyDataSetChanged();
            }

        });

        classTB = (ListView)findViewById(R.id.addedClassesLs);
        classTB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter2.getItem(position);
                Class tmp = new Class(((Class)item).getId(),((Class)item).getName(),((Class)item).getMatricula(),((Class)item).getGrade(),((Class)item).getFaltas());
                lista.add(tmp);
                lista2.remove(position);

                System.out.println("Se removio posicion " + position);
                spinnerAdapter2.notifyDataSetChanged();
                spinnerAdapter.notifyDataSetChanged();
            }

        });
        ini();

    }
    public void ini(){
        nameToShow.setText(alumnName);
        lista = db.getClasses();
        lista2 = db.getAlumnClass(Integer.toString(alumnId));
        lista3 = db.getAlumnClass(Integer.toString(alumnId));


        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        allClasses.setAdapter(spinnerAdapter);

        spinnerAdapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista2);
        classTB.setAdapter(spinnerAdapter2);

        for (int i = 0; i <lista2.size(); i++){
            System.out.println("simon");
            for (int j = 0; j < lista.size(); j++){
                if(lista2.get(i).getId() == lista.get(j).getId()){
                    lista.remove(j);
                    spinnerAdapter.notifyDataSetChanged();
                    j--;
                }
            }
        }

    }
    public void returnToAl(View v){
        Intent alActivity = new Intent(v.getContext(), AlumnActivity.class);
        startActivityForResult(alActivity,0);
    }
    public void asignCl(){
        if(lista2.size() == 0){
            Toast.makeText(this, "De click al menos en una materia disponible",
                    Toast.LENGTH_LONG).show();
        }else{
            for (int i = 0; i <lista3.size(); i++){
                for (int j = 0; j < lista2.size(); j++){
                    if(lista3.get(i).getId() == lista2.get(j).getId()){
                        lista2.remove(j);
                        j--;
                    }
                }
            }
            if(lista2.size()>0){
                for (int i = 0; i < lista2.size(); i++){
                    db.insertaralClass(alumnId,lista2.get(i).getId(),lista2.get(i).getFaltas(),0);
                }
            }
            Toast.makeText(this, "Materias asignadas",
                    Toast.LENGTH_LONG).show();
        }
    }
}
