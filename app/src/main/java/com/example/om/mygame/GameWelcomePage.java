package com.example.om.mygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class GameWelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_welcome_page);

        Button start_game = (Button) findViewById(R.id.StartGame);

        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisualAndAuditoryPage.for_game =true;
                Intent myIntent = new Intent(GameWelcomePage.this,VisualAndAuditoryPage.class);
                startActivity(myIntent);
            }
        });
    }

}
