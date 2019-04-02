package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NGroupActivity extends AppCompatActivity {
    private TextView rtnTLast;
    private EditText name;
    private Button rTA;
    private MyOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngroup);
        ini();
    }
    public void ini(){
        rtnTLast = (TextView)findViewById(R.id.backGP);
        rtnTLast.setOnClickListener((v)->{returnToAl(v);});


        db = new MyOpenHelper(this);
        name = (EditText)findViewById(R.id.etgpName);
        rTA = (Button)findViewById(R.id.addGPbtn);
        rTA.setOnClickListener((v)->{ addGroup();});
    }
    public void addGroup(){
        String tmpName = name.getText().toString();
        if(tmpName.length()>0){
            db.insertarGroup(tmpName);
            Toast.makeText(this, "Materia Registrada con Exito",
                    Toast.LENGTH_LONG).show();
            name.setText("");
        }else{
            Toast.makeText(this, "Ingrese todos los campos",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void returnToAl(View v){
        Intent gpActivity = new Intent(v.getContext(), GroupActivity.class);
        startActivityForResult(gpActivity,0);
    }
}
