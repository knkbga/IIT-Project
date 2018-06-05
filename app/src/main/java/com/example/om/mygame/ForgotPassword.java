package com.example.om.mygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import authentication.LandingPage;
import authentication.LogIn;
import authentication.PersonCredentials;

public class ForgotPassword extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = getBaseContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        RelativeLayout pre_change = (RelativeLayout) findViewById(R.id.pre_change);
        pre_change.setVisibility(View.VISIBLE);

        //Pre Process

        Button pre_forget = (Button) findViewById(R.id.pre_forget);
        final EditText email = (EditText) findViewById(R.id.pre_forget_EmailField);
        final TextView testing = (TextView) findViewById(R.id.pre_testing);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        testing.setPaintFlags(testing.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        pre_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
                {
                    testing.setText("** "+"Email is not valid"+" **");
                }
                else
                {
                    ResetPassword.email = email.getText().toString();
                    JSONObject postDataParams = new JSONObject();
                    try {
                        postDataParams.put("email", email.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    API api = new API(PersonCredentials.oid, postDataParams, LandingPage.url + "/api/resetpass", testing, mContext, 3, progressBar);

                    Log.d("URL", LandingPage.url + "/login");

                    api.execute();
                }

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent myIntent = new Intent(ForgotPassword.this, LogIn.class);
        startActivity(myIntent);
    }
}
