package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AssistanceAlumnsActivity extends AppCompatActivity {

    private String className;
    private int classId;
    private MyOpenHelper db;
    private ArrayList<Alumno> lista;
    private Alumno al;

    private TextView nameToShow;
    private TextView backTAct;
    private ListView absences;
    private ArrayAdapter spinnerAdapter;

    private ArrayList<Alumno> lista2;
    private ListView noProblem;
    private ArrayAdapter spinnerAdapter2;

    private ArrayList<Class> lista3;
    private Button takeL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance_alumns);


        Intent intent = getIntent();
        backTAct = (TextView)findViewById(R.id.backToClsLst);
        backTAct.setOnClickListener((v)->{returnToClsLs(v);});
        takeL = (Button)findViewById(R.id.TomarList);
        takeL.setOnClickListener((v)->{ takeList();});

        db = new MyOpenHelper(this);
        className = intent.getStringExtra("className");
        classId = Integer.parseInt(intent.getStringExtra("classId"));
        nameToShow = (TextView)findViewById(R.id.lvlMatName);
        absences = (ListView)findViewById(R.id.abscents);
        absences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Alumno tmp = new Alumno(((Alumno)item).getId(),((Alumno)item).getName(),((Alumno)item).getLastName(),((Alumno)item).getGrade(),((Alumno)item).getGroup());
                lista2.add(tmp);
                lista.remove(position);

                System.out.println("Se removio posicion " + position);
                spinnerAdapter2.notifyDataSetChanged();
                spinnerAdapter.notifyDataSetChanged();
            }

        });

        noProblem = (ListView)findViewById(R.id.noProblem);
        noProblem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter2.getItem(position);
                Alumno tmp = new Alumno(((Alumno)item).getId(),((Alumno)item).getName(),((Alumno)item).getLastName(),((Alumno)item).getGrade(),((Alumno)item).getGroup());
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
        nameToShow.setText(className);
        lista = db.getAlumnsOnTheClass(Integer.toString(classId));
        lista2 = new ArrayList<>();


        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        absences.setAdapter(spinnerAdapter);

        spinnerAdapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista2);
        noProblem.setAdapter(spinnerAdapter2);


    }

    public void returnToClsLs(View v){
        Intent alActivity = new Intent(v.getContext(), AssistanceActivity.class);
        startActivityForResult(alActivity,0);
    }

    public void takeList(){
        if(lista.size() == 0){
            Toast.makeText(this, "Ningun alumno faltó",
                    Toast.LENGTH_LONG).show();
        }else{
            for (int i = 0; i < lista.size(); i++){
                db.getTheTableAC(lista.get(i).getId(),classId);
            }
            Toast.makeText(this, "Faltas Registradas",
                    Toast.LENGTH_LONG).show();
        }
    }
}
