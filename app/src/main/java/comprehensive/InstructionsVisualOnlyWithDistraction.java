package comprehensive;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class InstructionsVisualOnlyWithDistraction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instructions_visual_only_with_distraction);
        setTitle("Gaming 'Visual And Audio With Distraction'");
        Button startTest = (Button) findViewById(R.id.StartTestWO);

        /*TextView ins1 = (TextView) findViewById(R.id.instruction_1_text);
        ins1.setText(R.string.instruction_w_gaming_1);

        TextView ins2 = (TextView) findViewById(R.id.instruction_2_text);
        ins2.setText(R.string.instruction_w_gaming_2);

        TextView ins3 = (TextView) findViewById(R.id.instruction_3_text);
        ins3.setText(R.string.instruction_w_gaming_3);

        TextView ins4 = (TextView) findViewById(R.id.instruction_4_text);
        ins4.setText(R.string.instruction_w_gaming_4);*/

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(InstructionsVisualOnlyWithDistraction.this,comprehensive.VisualOnlyWithDistraction.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Not recommended to leave the game before you complete all 3 sets.")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(InstructionsVisualOnlyWithDistraction.this, HomePage.class);
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
