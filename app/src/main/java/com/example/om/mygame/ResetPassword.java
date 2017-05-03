package com.example.om.mygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import authentication.Authenticate;
import authentication.LogIn;
import authentication.PersonCredentials;

public class ResetPassword extends AppCompatActivity {

    Context mContext;
    static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext =getBaseContext();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset_password);

        Button reset_pass = (Button) findViewById(R.id.post_forgot_password_button);
        final EditText code = (EditText) findViewById(R.id.post_forgot_pass_code);
        final EditText new_pass = (EditText) findViewById(R.id.post_forgot_pass_new_pass);
        final TextView testing = (TextView) findViewById(R.id.testing);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        testing.setPaintFlags(testing.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject request = new JSONObject();

                try {
                    request.put("email", email);
                    request.put("code",code.getText().toString());
                    request.put("newpass",new_pass.getText().toString());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                API api = new API(PersonCredentials.oid, request, Authenticate.url + "/api/resetpass/chg", testing, mContext, 4, progressBar);

                api.execute();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent myIntent = new Intent(ResetPassword.this, LogIn.class);
        startActivity(myIntent);
    }
}
