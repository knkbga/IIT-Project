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
    public static JSONObject reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mContext = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button Login =(Button) findViewById(R.id.Register);
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

                if(name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || phone.getText().toString().equals("") || confirmPassword.getText().toString().equals(""))
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
                            postDataParams.put("email", email.getText().toString());
                            postDataParams.put("password", password.getText().toString());
                            postDataParams.put("name", name.getText().toString());
                            postDataParams.put("phone", phone.getText().toString());
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


