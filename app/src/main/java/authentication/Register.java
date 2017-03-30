package authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button Login =(Button) findViewById(R.id.Register);
        final EditText email  = (EditText) findViewById(R.id.email_input_register);
        final EditText password = (EditText) findViewById(R.id.password_input_register);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /**
                 * todo: Code to send post request to web server
                 * */
                Intent myIntent = new Intent(Register.this , HomePage.class);
                startActivity(myIntent);
            }
        });

    }
}
