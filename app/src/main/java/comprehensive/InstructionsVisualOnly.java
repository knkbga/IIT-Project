package comprehensive;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;

import authentication.Authenticate;

public class InstructionsVisualOnly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //is connected to the internet
        if(HomePage.isConnected)
        {
            setContentView(R.layout.activity_instructions_visual_only);
            setTitle("Gaming 'Visual Only'");
            Button start_this_test = (Button) findViewById(R.id.StartTestV);

            /*TextView ins1 = (TextView) findViewById(R.id.instruction_1_text);
            ins1.setText(R.string.instruction_visual_only_1);

            TextView ins2 = (TextView) findViewById(R.id.instruction_2_text);
            ins2.setText(R.string.instruction_visual_only_2);

            TextView ins3 = (TextView) findViewById(R.id.instruction_3_text);
            ins3.setText(R.string.instruction_visual_only_3);

            TextView ins4 = (TextView) findViewById(R.id.instruction_4_text);
            ins4.setText(R.string.instruction_visual_only_4);*/


            start_this_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(InstructionsVisualOnly.this,comprehensive.VisualOnly.class);
                    startActivity(myIntent);
                }
            });
        }
        else
        {
            Toast.makeText(getApplication(), "You have lost your connection. Please try again.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed()
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Not recommended to leave the game before you complete all 3 sets.")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(InstructionsVisualOnly.this, HomePage.class);
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
