package comprehensive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;
import com.example.om.mygame.VisualOnlyWithDistraction;

public class TrialsWDistraction extends AppCompatActivity {

    public static Boolean gaming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //is connected to the internet
        if(HomePage.isConnected)
        {
            setContentView(R.layout.activity_trials_wdistraction);
            Button start_this_test = (Button) findViewById(R.id.StartTestW);

            start_this_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(gaming == true)
                    {
                        comprehensive.VisualOnlyWithDistraction.gaming = true;
                    }
                    else
                    {
                        comprehensive.VisualOnlyWithDistraction.gaming = false;
                    }
                    Intent myIntent = new Intent(comprehensive.TrialsWDistraction.this,comprehensive.VisualOnlyWithDistraction.class);
                    startActivity(myIntent);
                }
            });
        }
        else
        {

        }

    }

}
