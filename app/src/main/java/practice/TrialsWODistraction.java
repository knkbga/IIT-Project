package practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.om.mygame.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class TrialsWODistraction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instructions_visual_and_auditory);
        Button startTest = (Button) findViewById(R.id.StartTestWO);

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(practice.TrialsWODistraction.this,practice.VisualAndAuditoryPage.class);
                startActivity(myIntent);
            }
        });
    }

}
