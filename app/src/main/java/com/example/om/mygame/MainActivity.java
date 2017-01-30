package com.example.om.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void visualAndAuditory(View view)
    {
        Intent intent = new Intent(this, VisualAndAuditoryPage.class);
        startActivity(intent);
    }
    public void visualOnly(View view)
    {
        Intent intent = new Intent(this, VisualOnlyPage.class);
        //int distraction_int = 0 ;                  //Without Distraction
        //intent.putExtra("distraction_int",distraction_int ) ;
        startActivity(intent);
    }
    public void visualOnlyWithDistraction(View view)
    {
        Intent intent = new Intent(this, VisualOnlyWithDistraction.class);
        //int distraction_int = 1 ;
        //intent.putExtra("distraction_int", distraction_int) ;    // With Distraction
        startActivity(intent);
    }
    public void auditoryOnly(View view)
    {
        Intent intent = new Intent(this, AuditoryOnlyPage.class);
        startActivity(intent);
    }
    public void auditoryOnlyWithDistraction(View view)
    {
        Intent intent = new Intent(this, AuditoryOnlyWithDistraction.class);
        startActivity(intent);
    }
    public void trailMaking(View view)
    {
        Intent intent = new Intent(this, TrailMakingPage.class);
        startActivity(intent);
    }
    public void sanskritGame(View view)
    {
        Intent intent = new Intent(this, SanskritGame.class);
        startActivity(intent);
    }
}
