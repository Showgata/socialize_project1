package com.example.kanuma.idtn2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ViewFlipper;

import jp.wasabeef.blurry.Blurry;

public class Intro extends AppCompatActivity {

    private ViewFlipper flipper;
    private Button getStartedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        getStartedButton =findViewById(R.id.getStarted);

        flipper =findViewById(R.id.flipper);


        flipper.setFlipInterval(2000);
        flipper.startFlipping();

        flipper.setInAnimation(this,R.anim.movein);
        flipper.setOutAnimation(this,R.anim.moveout);

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent =new Intent(Intro.this,LoginActivity.class);
                startActivity(newIntent);
                overridePendingTransition(R.anim.move_from_down,R.anim.move_to_up);
            }
        });

    }


}

