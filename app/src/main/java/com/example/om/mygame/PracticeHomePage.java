package com.example.om.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import authentication.LogIn;

public class PracticeHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void visualAndAuditory(View view)
    {
        Intent intent = new Intent(this, practice.VisualAndAuditory.class);
        startActivity(intent);
    }
    public void visualOnlyWithDistraction(View view)
    {
        Intent intent = new Intent(this, practice.VisualOnlyWithDistraction.class);
        startActivity(intent);
    }
    public void visualOnly(View view)
    {
        Intent intent = new Intent(this, practice.VisualOnly.class);
        startActivity(intent);
    }
    public void audioOnly(View view)
    {
        Intent intent = new Intent(this, practice.AudioOnly.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent myIntent = new Intent(PracticeHomePage.this, HomePage.class);
        startActivity(myIntent);
    }
}
