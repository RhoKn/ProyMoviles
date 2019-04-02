package com.example.rubengonzalez.proyect1moviles;

import android.content.Intent;
import android.hardware.camera2.CaptureFailure;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private CardView firstCard;
    private CardView secondCard;
    private CardView thirdCard;
    private CardView forthCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ini();
    }

    private void ini(){
        firstCard = (CardView)findViewById(R.id.firstCard);
        secondCard = (CardView)findViewById(R.id.secondCard);
        thirdCard = (CardView)findViewById(R.id.thirdCard);
        forthCard = (CardView)findViewById(R.id.forthCard);
        firstCard.setOnClickListener(v -> {startClassActivity(v);});
        secondCard.setOnClickListener(v -> {startGroupActivity(v);});
        thirdCard.setOnClickListener(v -> {startAlumnActivity(v);});
        forthCard.setOnClickListener(v -> {startFailuresActivity(v);});

    }

     private void startClassActivity(View v){
              Intent classActivity = new Intent(v.getContext(), ClassActivity.class);
              startActivityForResult(classActivity,0);
          }
    private void startAlumnActivity(View v){
        Intent alumnActivity = new Intent(v.getContext(), AlumnActivity.class);
        startActivityForResult(alumnActivity,0);
    }
    private void startGroupActivity(View v){
        Intent groupActivity = new Intent(v.getContext(), GroupActivity.class);
        startActivityForResult(groupActivity,0);
    }
    private void startFailuresActivity(View v){
        Intent filuresActivity = new Intent(v.getContext(), FiluresActivity.class);
        startActivityForResult(filuresActivity,0);
    }

}
