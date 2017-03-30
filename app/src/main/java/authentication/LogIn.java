package authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;

public class LogIn extends AppCompatActivity {

    public static String oid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button Login =(Button) findViewById(R.id.LogIn);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LogIn.this , HomePage.class);
                startActivity(myIntent);
            }
        });

    }

}
