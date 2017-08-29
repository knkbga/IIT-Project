package authentication;

import android.content.Context;
import android.graphics.Paint;
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
import com.example.om.mygame.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    public Context mContext;

    private boolean isValidDate(String inDate) {
        String regex = "[0-9]{2}[-|\\/]{1}[0-9]{2}[-|\\/]{1}[0-9]{4}";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(inDate);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mContext = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button Login =(Button) findViewById(R.id.Register);
        final EditText institute = (EditText) findViewById(R.id.InstituteField);


        final EditText jeeRollNumber = (EditText) findViewById(R.id.jeeRollNumberField);
        final EditText jeeTotalScore = (EditText) findViewById(R.id.jeeTotalScoreField);
        final EditText jeePhysicsScore = (EditText) findViewById(R.id.jeePhysicsScoreField);
        final EditText jeeMathsScore = (EditText) findViewById(R.id.jeeMathsScoreField);
        final EditText jeeChemistryScore = (EditText) findViewById(R.id.jeeChemistryScoreField);



        final EditText grade_10 = (EditText) findViewById(R.id.Grade_10Field);
        final Spinner gender = (Spinner) findViewById(R.id.GenderField);
        final EditText dob = (EditText) findViewById(R.id.dob);
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

                if(jeeChemistryScore.getText().toString().trim().equals("") || jeeMathsScore.getText().toString().trim().equals("") ||  jeePhysicsScore.getText().toString().trim().equals("") || jeeTotalScore.getText().toString().trim().equals("") ||jeeRollNumber.getText().toString().trim().equals("") || institute.getText().toString().trim().equals("") || dob.getText().toString().trim().equals("") || gender.getSelectedItem().toString().trim().equals("") || grade_10.getText().toString().trim().equals("") || name.getText().toString().trim().equals("") || email.getText().toString().trim().equals("") || password.getText().toString().trim().equals("") || phone.getText().toString().trim().equals("") || confirmPassword.getText().toString().trim().equals(""))
                {
                    testing.setText("** All fields are required **");
                }
                else if (!isValidDate(dob.getText().toString().trim()) )
                {
                    testing.setText("** Date of Birth format is not correct **");
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches())
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
                            postDataParams.put("dob", dob.getText().toString().trim());
                            postDataParams.put("grade_10", grade_10.getText().toString().trim());
                            postDataParams.put("institute", institute.getText().toString().trim());

                            postDataParams.put("jeeRollNumber", jeeRollNumber.getText().toString().trim());
                            postDataParams.put("jeeTotalScore", jeeTotalScore.getText().toString().trim());
                            postDataParams.put("jeePhysicsScore", jeePhysicsScore.getText().toString().trim());
                            postDataParams.put("jeeMathsScore", jeeMathsScore.getText().toString().trim());
                            postDataParams.put("jeeChemistryScore", jeeChemistryScore.getText().toString().trim());

                            postDataParams.put("app_code", "iit");
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


