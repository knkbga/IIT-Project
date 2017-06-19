package com.example.om.mygame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import authentication.Authenticate;
import comprehensive.*;


public class HomePage extends AppCompatActivity {

    public static boolean isConnected=true;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        if(isConnected)
        {
            setContentView(R.layout.activity_home_page);

            Button comprehensiveTestButton = (Button) findViewById(R.id.ComprehensiveTestButton);
            Button specificTestButton = (Button) findViewById(R.id.SpecificTestButton);

            comprehensiveTestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Set.Sets_game=1;
                    Intent myIntent = new Intent(HomePage.this, Set.class);
                    startActivity(myIntent);
                }
            });

            specificTestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(HomePage.this,PracticeHomePage.class);
                    startActivity(myIntent);
                }
            });
        }
        else
        {
            Toast.makeText(getApplication(), "Your internet connection is no longer active", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed()
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Are you sure ? You will have to login again.")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(HomePage.this,Authenticate.class);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }
}
