package comprehensive;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;
import com.example.om.mygame.VisualOnlyWithDistraction;

import authentication.Authenticate;

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

            if(gaming == true)
            {
                comprehensive.VisualOnlyWithDistraction.gaming = true;

                TextView ins1 = (TextView) findViewById(R.id.instruction_1_text);
                ins1.setText(R.string.instruction_w_gaming_1);

                TextView ins2 = (TextView) findViewById(R.id.instruction_2_text);
                ins2.setText(R.string.instruction_w_gaming_2);

                TextView ins3 = (TextView) findViewById(R.id.instruction_3_text);
                ins3.setText(R.string.instruction_w_gaming_3);

                TextView ins4 = (TextView) findViewById(R.id.instruction_4_text);
                ins4.setText(R.string.instruction_w_gaming_4);
            }
            else
            {
                comprehensive.VisualOnlyWithDistraction.gaming = false;

                TextView ins1 = (TextView) findViewById(R.id.instruction_1_text);
                ins1.setText(R.string.instruction_w_trial_1);

                TextView ins2 = (TextView) findViewById(R.id.instruction_2_text);
                ins2.setText(R.string.instruction_w_trial_2);

                TextView ins3 = (TextView) findViewById(R.id.instruction_3_text);
                ins3.setText(R.string.instruction_w_trial_3);

                TextView ins4 = (TextView) findViewById(R.id.instruction_4_text);
                ins4.setText(R.string.instruction_w_trial_4);
            }

            start_this_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(comprehensive.TrialsWDistraction.this,comprehensive.VisualOnlyWithDistraction.class);
                    startActivity(myIntent);
                }
            });
        }
        else
        {

        }

    }

    @Override
    public void onBackPressed()
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Are you sure ? You will have to login again.")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(TrialsWDistraction.this, Authenticate.class);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
