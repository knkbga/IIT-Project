package com.example.om.mygame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import authentication.LandingPage;
import authentication.PersonCredentials;


public class HomePage extends AppCompatActivity {

    public static boolean isConnected=true;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        JSONObject request = new JSONObject();

        try {
            API obj = new API(PersonCredentials.oid, request, LandingPage.url + "/api/scorecard/", null, mContext, 6, null,"POST");
            Log.v("API", "API called from Homepage Activity.");
            obj.execute();
        } catch (Exception e) {
            Log.v("API", e.getClass().toString());
            Toast.makeText(getApplication(), "Some error occurred. Please try again", Toast.LENGTH_LONG).show();
        }

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Sessions.new_session = true;

        if(isConnected)
        {
            setContentView(R.layout.activity_home_page);

            Button comprehensiveTestButton = (Button) findViewById(R.id.ComprehensiveTestButton);
            Button specificTestButton = (Button) findViewById(R.id.SpecificTestButton);
            Button leaderboardButton = (Button) findViewById(R.id.LeaderBoardButton);

            comprehensiveTestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sessions.Sets_game=1;
                    Intent myIntent = new Intent(HomePage.this, Sessions.class);
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

            leaderboardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(HomePage.this,Leaderboard.class);
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
                        Intent myIntent = new Intent(HomePage.this, LandingPage.class);
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
