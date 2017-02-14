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

public class TrialsWDistraction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trials_wdistraction);
        Button start_this_test = (Button) findViewById(R.id.StartTestW);

        start_this_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TrialsWDistraction.this,VisualOnlyWithDistraction.class);
                startActivity(myIntent);
            }
        });
    }

}
