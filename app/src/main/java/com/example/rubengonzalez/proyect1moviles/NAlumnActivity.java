package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NAlumnActivity extends AppCompatActivity {
    private EditText name;
    private EditText lastName;
    private EditText group;
    private EditText grade;
    private Button rTA;
    private TextView rtnTLast;
    private MyOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nalumn);

        ini();
    }
    public void ini(){
        db = new MyOpenHelper(this);
        name = (EditText)findViewById(R.id.etName);
        lastName = (EditText)findViewById(R.id.etLName);
        group = (EditText)findViewById(R.id.etGroup);
        grade = (EditText)findViewById(R.id.etGrade);
        rtnTLast = (TextView)findViewById(R.id.backAlmn);
        rtnTLast.setOnClickListener((v)->{returnToAl(v);});
        rTA = (Button)findViewById(R.id.addAlumnBtn);
        rTA.setOnClickListener((v)->{ addUser();});
    }

    public void returnToAl(View v){
        Intent alActivity = new Intent(v.getContext(), AlumnActivity.class);
        startActivityForResult(alActivity,0);
    }

    public void addUser(){
        String tmpName = name.getText().toString();
        String tmpLname = lastName.getText().toString();
        String tmpGrade = grade.getText().toString();
        String tmpGroup = group.getText().toString();

        if(tmpLname.length()>0 && tmpName.length()>0 && tmpGroup.length()>0 && tmpGrade.length()>0){
            db.insertar(tmpName,tmpLname,Integer.parseInt(tmpGrade),tmpGroup);
            Toast.makeText(this, "Alumno Registrado con Exito",
                    Toast.LENGTH_LONG).show();
            name.setText("");
            lastName.setText("");
            group.setText("");
            grade.setText("");
        }else{
            Toast.makeText(this, "Ingrese todos los campos",
                    Toast.LENGTH_LONG).show();
        }
    }
}
