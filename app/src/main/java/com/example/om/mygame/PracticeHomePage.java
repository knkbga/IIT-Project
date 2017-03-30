package com.example.om.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PracticeHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void visualAndAuditory(View view)
    {
        Intent intent = new Intent(this, practice.VisualAndAuditoryPage.class);
        startActivity(intent);
    }
    public void visualOnlyWithDistraction(View view)
    {
        Intent intent = new Intent(this, practice.VisualOnlyWithDistraction.class);
        startActivity(intent);
    }
}
