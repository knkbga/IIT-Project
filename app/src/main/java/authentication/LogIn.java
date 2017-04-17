package authentication;

import android.content.Context;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.om.mygame.API;
import com.example.om.mygame.Connectivity;
import com.example.om.mygame.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogIn extends AppCompatActivity {

    public static JSONObject log;
    public Context mContext;
    public static Boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button Login =(Button) findViewById(R.id.LogIn);
        final EditText email = (EditText) findViewById(R.id.EmailField);
        final EditText password = (EditText) findViewById(R.id.PasswordField);
        final TextView testing = (TextView) findViewById(R.id.testing);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        testing.setPaintFlags(testing.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            progressBar.setVisibility(View.VISIBLE);

            if(email.getText().toString().equals("") || password.getText().toString().equals(""))
            {
                testing.setText("**  All fields should be filled **");
                progressBar.setVisibility(View.GONE);
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
            {
                testing.setText("**  Email not valid **");
                progressBar.setVisibility(View.GONE);
            }
            else
            {
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

                            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                            if (activeNetwork != null && activeNetwork.isConnected()) {
                                try {
                                    URL url = new URL(Authenticate.url);
                                    HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
                                    urlc.setRequestProperty("User-Agent", "test");
                                    urlc.setRequestProperty("Connection", "close");
                                    urlc.setConnectTimeout(5000); // mTimeout is in seconds
                                    urlc.connect();
                                    if (urlc.getResponseCode() == 200) {
                                        isConnected = true;
                                    } else {
                                        isConnected = false;
                                    }
                                } catch (IOException e) {
                                    Log.i("warning", "Error checking internet connection", e);
                                    isConnected = false;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            isConnected =false;
                        }
                    }
                });

                thread.start();

                try {
                    thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(isConnected) {
                    JSONObject postDataParams = new JSONObject();
                    try {
                        postDataParams.put("email", email.getText().toString());
                        postDataParams.put("password", password.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    API api = new API(PersonCredentials.oid, postDataParams, Authenticate.url + "/login", testing, mContext, 1, progressBar);

                    Log.d("URL", Authenticate.url + "/login");

                    api.execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Couldn't connect to network. Check your connection",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
            }
        });

    }

}

