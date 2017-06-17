package authentication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.om.mygame.API;
import com.example.om.mygame.Connectivity;
import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Register extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mContext = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button Login =(Button) findViewById(R.id.Register);
        final EditText institute = (EditText) findViewById(R.id.InstituteField);
        final EditText grade_10 = (EditText) findViewById(R.id.Grade_10Field);
        final Spinner gender = (Spinner) findViewById(R.id.GenderField);
        final EditText age = (EditText) findViewById(R.id.AgeField);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final EditText email  = (EditText) findViewById(R.id.EmailField);
        final EditText password = (EditText) findViewById(R.id.PasswordField);
        final EditText confirmPassword = (EditText) findViewById(R.id.ConfirmPasswordField);
        final EditText name = (EditText) findViewById(R.id.NameField);
        final EditText phone = (EditText) findViewById(R.id.PhoneField);
        final TextView testing = (TextView) findViewById(R.id.testing);
        testing.setPaintFlags(testing.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /**
                * todo: Code to send post request to web server
                * */

                if(institute.getText().toString().trim().equals("") || age.getText().toString().trim().equals("") || gender.getSelectedItem().toString().trim().equals("") || grade_10.getText().toString().trim().equals("") || name.getText().toString().trim().equals("") || email.getText().toString().trim().equals("") || password.getText().toString().trim().equals("") || phone.getText().toString().trim().equals("") || confirmPassword.getText().toString().trim().equals(""))
                {
                    testing.setText("** All fields are required **");
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
                {
                    testing.setText("** Email not valid **");
                }
                else if(!password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    testing.setText("** Password didn't match **");
                }
                else if(password.getText().toString().length()<=4)
                {
                    testing.setText("** Length of password should be at least 5 **");
                }
                else
                {
                    if(Connectivity.isConnected(mContext,Authenticate.url)) {
                        testing.setText("");
                        JSONObject postDataParams = new JSONObject();
                        try {
                            postDataParams.put("email", email.getText().toString().trim());
                            postDataParams.put("password", password.getText().toString().trim());
                            postDataParams.put("name", name.getText().toString().trim());
                            postDataParams.put("phone", phone.getText().toString().trim());
                            postDataParams.put("gender", gender.getSelectedItem().toString());
                            postDataParams.put("age", age.getText().toString().trim());
                            postDataParams.put("grade_10", grade_10.getText().toString().trim());
                            postDataParams.put("institute", institute.getText().toString().trim());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        API api = new API(PersonCredentials.oid, postDataParams, Authenticate.url + "/register", testing, mContext, 0, progressBar);

                        progressBar.setVisibility(View.VISIBLE);
                        api.execute();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

}


