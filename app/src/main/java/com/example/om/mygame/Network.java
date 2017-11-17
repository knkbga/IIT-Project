package com.example.om.mygame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import authentication.LandingPage;
import authentication.LogIn;
import authentication.Register;


public class Network extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Context mContext =getBaseContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        getSupportActionBar().hide();

        Button proceed = (Button) findViewById(R.id.ProceedButton);
        final RadioButton internet = (RadioButton) findViewById(R.id.radio_internet);
        final RadioButton localhost = (RadioButton) findViewById(R.id.radio_localhost);
        final EditText localhostAddress = (EditText) findViewById(R.id.LocalhostAddress);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                Boolean can_proceed=false;

                if (internet.isChecked()) {
                    LandingPage.url = "http://nameless-castle-57165.herokuapp.com";
                    can_proceed = true;
                } else if (localhost.isChecked()) {

                    if(!localhostAddress.getText().toString().trim().equals(""))
                    {
                        LandingPage.url = "http://"+localhostAddress.getText().toString();
                        can_proceed = true;
                    }
                    else {
                        new AlertDialog.Builder(Network.this)
                            .setTitle("You cannot proceed")
                            .setMessage("You have not provided the localhost address.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();
                    }
                } else {
                    new AlertDialog.Builder(Network.this)
                        .setTitle("You cannot proceed")
                        .setMessage("You have to select an option before you proceed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                            }
                        }).show();
                }

                if (can_proceed && Connectivity.isConnected(mContext, LandingPage.url))
                {
                    if (LandingPage.reg_log == 0) {
                        Intent myIntent = new Intent(Network.this, Register.class);
                        startActivity(myIntent);
                    } else if (LandingPage.reg_log == 1) {
                        Intent myIntent = new Intent(Network.this, LogIn.class);
                        startActivity(myIntent);
                    }
                }
                else
                {
                    Toast.makeText(mContext, "Couldn't connect to the network selected. Try other one",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}



