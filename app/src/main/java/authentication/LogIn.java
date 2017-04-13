package authentication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.om.mygame.API;
import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LogIn extends AppCompatActivity {

    public static JSONObject log;
    public Context mContext;

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

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
                {
                    testing.setText("**  Email not valid **");
                }
                else
                {
                    JSONObject postDataParams = new JSONObject();
                    try {
                        postDataParams.put("email", email.getText().toString());
                        postDataParams.put("password", password.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    API api = new API(PersonCredentials.oid,postDataParams, Authenticate.url+"/login",testing,mContext,1,progressBar);

                    Log.d("URL",Authenticate.url+"/login");

                    api.execute();

                }
            }
        });

    }

}
