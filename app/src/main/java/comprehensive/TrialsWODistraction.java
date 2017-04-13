package comprehensive;

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

    public static Boolean gaming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trials_wodistraction);
        Button startTest = (Button) findViewById(R.id.StartTestWO);

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gaming == true)
                {
                    comprehensive.VisualAndAuditoryPage.gaming = true;
                }
                else
                {
                    comprehensive.VisualAndAuditoryPage.gaming = false;
                }
                Intent myIntent = new Intent(comprehensive.TrialsWODistraction.this,comprehensive.VisualAndAuditoryPage.class);
                startActivity(myIntent);
            }
        });
    }

}
