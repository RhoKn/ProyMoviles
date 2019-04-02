package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NClassActivity extends AppCompatActivity {
    private TextView rtnTLast;

    private EditText name;
    private EditText matricula;
    private EditText grade;
    private EditText faltas;
    private Button rTA;
    private MyOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nclass);
        ini();
    }

    public void ini(){
        rtnTLast = (TextView)findViewById(R.id.backMat);
        rtnTLast.setOnClickListener((v)->{returnToAl(v);});


        db = new MyOpenHelper(this);
        name = (EditText)findViewById(R.id.etmtName);
        matricula = (EditText)findViewById(R.id.etmtMatricula);
        grade = (EditText)findViewById(R.id.etMtGrado);
        faltas = (EditText)findViewById(R.id.etMtFaltas);

        rTA = (Button)findViewById(R.id.addMTbtn);
        rTA.setOnClickListener((v)->{ addClass();});
    }
    public void addClass(){
        String tmpName = name.getText().toString();
        String tmpMatr = matricula.getText().toString();
        String tmpGrade = grade.getText().toString();
        String tmpFaltas = faltas.getText().toString();

        if(tmpName.length()>0 && tmpMatr.length()>0 && tmpGrade.length()>0 && tmpFaltas.length()>0){
            db.insertarClass(tmpName,tmpMatr,Integer.parseInt(tmpGrade),Integer.parseInt(tmpFaltas));
            Toast.makeText(this, "Materia Registrada con Exito",
                    Toast.LENGTH_LONG).show();
            name.setText("");
            matricula.setText("");
            grade.setText("");
            faltas.setText("");
        }else{
            Toast.makeText(this, "Ingrese todos los datos",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void returnToAl(View v){
        Intent mtActivity = new Intent(v.getContext(), ClassActivity.class);
        startActivityForResult(mtActivity,0);
    }
}
