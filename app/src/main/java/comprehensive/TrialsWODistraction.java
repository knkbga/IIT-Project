package comprehensive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.om.mygame.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class TrialsWODistraction extends AppCompatActivity {

    public static Boolean gaming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trials_wodistraction);
        Button startTest = (Button) findViewById(R.id.StartTestWO);
        if(gaming == true)
        {
            comprehensive.VisualAndAuditoryPage.gaming = true;

            TextView ins1 = (TextView) findViewById(R.id.instruction_1_text);
            ins1.setText(R.string.instruction_wo_gaming_1);

            TextView ins2 = (TextView) findViewById(R.id.instruction_2_text);
            ins2.setText(R.string.instruction_wo_gaming_2);

            TextView ins3 = (TextView) findViewById(R.id.instruction_3_text);
            ins3.setText(R.string.instruction_wo_gaming_3);

            TextView ins4 = (TextView) findViewById(R.id.instruction_4_text);
            ins4.setText(R.string.instruction_wo_gaming_4);
        }
        else
        {
            comprehensive.VisualAndAuditoryPage.gaming = false;

            TextView ins1 = (TextView) findViewById(R.id.instruction_1_text);
            ins1.setText(R.string.instruction_wo_trial_1);

            TextView ins2 = (TextView) findViewById(R.id.instruction_2_text);
            ins2.setText(R.string.instruction_wo_trial_2);

            TextView ins3 = (TextView) findViewById(R.id.instruction_3_text);
            ins3.setText(R.string.instruction_wo_trial_3);

            TextView ins4 = (TextView) findViewById(R.id.instruction_4_text);
            ins4.setText(R.string.instruction_wo_trial_4);
        }

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(comprehensive.TrialsWODistraction.this,comprehensive.VisualAndAuditoryPage.class);
                startActivity(myIntent);
            }
        });
    }

}
