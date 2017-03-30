package com.example.om.mygame;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class HomePage extends AppCompatActivity {

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(this.isConnected = isNetworkAvailable(getApplicationContext()))
        {
            setContentView(R.layout.activity_home_page);

            Button comprehensiveTestButton = (Button) findViewById(R.id.ComprehensiveTestButton);
            Button specificTestButton = (Button) findViewById(R.id.SpecificTestButton);

            comprehensiveTestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

        }

    }
}
