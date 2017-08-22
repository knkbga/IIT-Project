package com.example.om.mygame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import authentication.Authenticate;
import authentication.PersonCredentials;
import comprehensive.InstructionsVisualAndAuditory;

public class Set extends AppCompatActivity {
    public static int Sets_game;
    public static int starting_level=6;
    public static Boolean new_session = true;
    private static int max_sets=3;
    public static int max_lives_every_game=3;
    public static int max_levels_every_game=20;
    public static int session_score=0;
    private Context mContext;
    public static String session_token;

    private JSONObject request;
    private SimpleDateFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        final RelativeLayout sets_instructions_label  = (RelativeLayout) findViewById(R.id.sets_instructions_label);
        final TextView set_number_label = (TextView) findViewById(R.id.set_number_label);
        set_number_label.setPaintFlags(set_number_label.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        Button proceed_button = (Button) findViewById(R.id.proceed_button);
        sets_instructions_label.setVisibility(View.VISIBLE);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView testing = (TextView)findViewById(R.id.testing);

        df = new SimpleDateFormat( "dd-MM-yyyy 'at' kk:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("IST"));
        mContext = getBaseContext();
        request =new JSONObject();

        if(Sets_game<=max_sets){
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

                if(Sets_game<=max_sets)
                {
                    if(new_session) {
                        try {
                            request.put("start_session", df.format(new java.util.Date()));
                            request.put("_id", PersonCredentials.oid);
                            API obj = new API(PersonCredentials.oid, request, Authenticate.url + "/start_session", testing, mContext, 5, progressBar);

                            Log.v("API", "API called from Set Activity.");
                            obj.execute();
                        } catch (Exception e) {
                            Log.v("API", e.getClass().toString());
                            Toast.makeText(getApplication(), "Some error occurred. Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Intent myIntent = new Intent(Set.this, InstructionsVisualAndAuditory.class);
                        startActivity(myIntent);
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
        if(Sets_game<=max_sets)
        {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setMessage("Recommended to not leave the game.")
                    .setCancelable(false)
                    .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(Set.this, HomePage.class);
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
        else
        {
            Intent myIntent = new Intent(Set.this, HomePage.class);
            startActivity(myIntent);
        }
    }
}
