package com.example.om.mygame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import authentication.Authenticate;


public class HomePage extends AppCompatActivity {

    public static boolean isConnected=true;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView welcome = (TextView) findViewById(R.id.NameDisplay);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // todo
//        welcome.setText("Welcome "+ PersonCredentials.name);


        if(isConnected)
        {
            setContentView(R.layout.activity_home_page);

            Button comprehensiveTestButton = (Button) findViewById(R.id.ComprehensiveTestButton);
            Button specificTestButton = (Button) findViewById(R.id.SpecificTestButton);

            comprehensiveTestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comprehensive.TrialsWODistraction.gaming = false;
                    Intent myIntent = new Intent(HomePage.this,comprehensive.TrialsWODistraction.class);
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
            // todo
            new AlertDialog.Builder(getBaseContext())
                    .setTitle("Connection not available.")
                    .setMessage("Your connection is no longer available please check your network connection.")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(isConnected)
                            {
                                setContentView(R.layout.activity_home_page);

                                Button comprehensiveTestButton = (Button) findViewById(R.id.ComprehensiveTestButton);
                                Button specificTestButton = (Button) findViewById(R.id.SpecificTestButton);

                                comprehensiveTestButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        comprehensive.TrialsWODistraction.gaming = false;
                                        Intent myIntent = new Intent(HomePage.this,comprehensive.TrialsWODistraction.class);
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
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }
}
