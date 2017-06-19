package com.example.om.mygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import authentication.Authenticate;
import comprehensive.InstructionsAudioOnly;
import comprehensive.InstructionsVisualAndAuditory;
import comprehensive.VisualAndAuditory;
import comprehensive.VisualOnlyWithDistraction;

public class Set extends AppCompatActivity {
    public static int Sets_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        final RelativeLayout sets_instructions_label  = (RelativeLayout) findViewById(R.id.sets_instructions_label);
        final TextView set_number_label = (TextView) findViewById(R.id.set_number_label);
        Button proceed_button = (Button) findViewById(R.id.proceed_button);
        sets_instructions_label.setVisibility(View.VISIBLE);

        if(Sets_game<=3){
            proceed_button.setText("PROCEED");
            set_number_label.setText("Now starting set - '"+Sets_game+"'");
        }
        else
        {
            proceed_button.setText("HOME SCREEN");
            sets_instructions_label.setVisibility(View.GONE);
            set_number_label.setText("You can now go back to home screen.");
        }

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Sets_game<=3)
                {
                    try
                    {
                        Intent myIntent = new Intent(Set.this, InstructionsVisualAndAuditory.class);
                        startActivity(myIntent);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(getApplication(), "Some error occurred. Please try again",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Intent myIntent = new Intent(Set.this, HomePage.class);
                    startActivity(myIntent);
                    Toast.makeText(getApplication(), "Sets finished...",Toast.LENGTH_LONG).show();
                }
            }
        });

        setTitle("GAMES");

    }
    @Override
    public void onBackPressed()
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Recommended to not leave the game.")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(Set.this, Authenticate.class);
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
