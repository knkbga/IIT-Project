package comprehensive;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;
import com.example.om.mygame.TrialsWDistraction;

import java.util.Random;

public class VisualOnlyWithDistraction extends AppCompatActivity {
    protected int lives_left = 3;
    private int number_of_sets =0;
    private int level = 6 ;
    private String questionString  ;
    //private Random distraction = new Random() ;
    private int distraction_int = 1 ;                // With Auditory Distraction
    //private String distraction_string = getIntent().getExtras().getString("distraction_int") ;
    //private int distraction_int = Integer.valueOf(distraction_string) ;
    //private int distraction_int = getIntent().getExtras().getInt("distraction_int") ;
    private static MediaPlayer audio;  // Audio for distraction
    private static int back_button_pressed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_only_with_distraction);
        Intent intent = getIntent() ;
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_visual_only_with_distraction);
        if (distraction_int==0)
        {
            // Visual Only Game with no distraction.
            printWithDelay("With No Auditory Distraction",1450);
        }
        else if (distraction_int==1)
        {
            //Visual only game with auditory distraction
            printWithDelay("With Auditory Distraction",1450);
        }
        myGameLoop(level);
    }
    public void myGameLoop(int level)
    {
        int i, lenth = level ;
        int total_delay_time ;
        int[] randomArray = new int[lenth] ;
        back_button_pressed = 0 ;
        if (level != 6)
        {
            String level_string  = "Level : " + Integer.toString(level-3);
            printWithDelay(level_string,1450);
        }
        printWithDelay("",50);
        total_delay_time = 1500 ;   // 1450(distraction or no distraction notice)+ 50
        questionString = "" ;
        for(i = 0;i<lenth;i++)
        {
            Random r = new Random();
            int rand = r.nextInt(10) ;  // Generates a random number from 0-9
            randomArray[i] = rand ;
            questionString = questionString + Integer.toString(rand) ;
            // Delay of 1 Has to inserted here
            total_delay_time = 1500 + 1000*(i+1);
            printWithDelay("",total_delay_time-50); //Just for clearness between the forst number and the next number
            printWithDelay(Integer.toString(rand),total_delay_time);

            if (distraction_int==1)
            {
                // Visual Only Game with distraction.
                //audioDistraction.start();
                //setAudioStopTime(audioDistraction,total_delay_time+1500);
                Random distration_random_number = new Random() ;
                int distraction_number = distration_random_number.nextInt(10) ;
                if (distraction_number==0)
                {
                    audio =  MediaPlayer.create(this, R.raw.zero);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==1)
                {
                    audio =  MediaPlayer.create(this, R.raw.one);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==2)
                {
                    audio =  MediaPlayer.create(this, R.raw.two);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==3)
                {
                    audio =  MediaPlayer.create(this, R.raw.three);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==4)
                {
                    audio =  MediaPlayer.create(this, R.raw.four);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==5)
                {
                    audio =  MediaPlayer.create(this, R.raw.five);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==6)
                {
                    audio =  MediaPlayer.create(this, R.raw.six);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==7)
                {
                    audio =  MediaPlayer.create(this, R.raw.seven);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==8)
                {
                    audio =  MediaPlayer.create(this, R.raw.eight);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
                else if (distraction_number==9)
                {
                    audio =  MediaPlayer.create(this, R.raw.nine);
                    setAudioStartTime(audio,total_delay_time);
                    setAudioStopTime(audio,total_delay_time+1000-50);
                }
            }
        }
        // Delay of 2 Has to be inserted here
        printWithDelay("",total_delay_time + 1500);
        //// Input Text & Submit Button Only Visible when all numbers have already been displayed in the outputTextView////
        final EditText inputText = (EditText) findViewById(R.id.visual_only_input);
        final Button submit_button  = (Button)findViewById(R.id.visual_only_submit_button) ;
        inputText.setVisibility(View.INVISIBLE) ;
        submit_button.setVisibility(View.INVISIBLE) ;
        Handler input_textbox_handler = new Handler();
        {
            int delay_time = total_delay_time + 1500 ;
            input_textbox_handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do after delay_time milli seconds
                    // 1 second = 1000 milli second
                    inputText.setVisibility(View.VISIBLE) ;
                    submit_button.setVisibility(View.VISIBLE) ;
                }
            }, delay_time);
        }
        /////////////////////////////////////////////////////////////////
        inputText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // Perform action on key press
                    //Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                    try {
                        visualOnlySubmitButton();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
        ////////////////////////////////////////////////////////
        // From here the answer that the user gives will be checked by clicking on the Submit button on the visual only page
    } // The work of this loop is just to display the random numbers/audio with appropriate time delay and make questionString

    public void visualOnlySubmitButton(View view) throws InterruptedException {
        visualOnlySubmitButton();
    }

    public void visualOnlySubmitButton() throws InterruptedException {
        String answerString ;
        TextView outputTextView = (TextView) findViewById(R.id.visual_only_output);
        final EditText inputText = (EditText) findViewById(R.id.visual_only_input);
        int lenth = level ;
        ///////////////////////////////////////////////////////////////////////
        if(inputText != null || !inputText.getText().equals(""))
        {
            Log.v("EditText",inputText.getText().toString()) ;
            answerString = inputText.getText().toString() ;
        }
        else
        {
            answerString  = "" ;
        }
        //////////////////////////////////////////////////////////////////////
        if ((questionString.equals(answerString))&&(lenth <14))
        {
            outputTextView.setText("Correct Answer !");
            level = level + 1 ;
            lives_left=3;
            inputText.setText("");
            myGameLoop(level);
        }
        else if ((questionString.equals(answerString))&&(lenth == 14))
        {
            outputTextView.setText("Congratulations! All Levels Completed successfully");
            number_of_sets++;
            if(number_of_sets < 3)
            {
                outputTextView.setText("Now set number :: "+(number_of_sets+1)+" begins");
                level =6;
                myGameLoop(level);
            }
            else
            {
                startActivity(new Intent(comprehensive.VisualOnlyWithDistraction.this,comprehensive.TrialsWDistraction.class));
            }
        }
        else
        {
            lives_left--;
            if(lives_left > 0)
            {
                String str = "";
                str = (lives_left == 1)?("You have one life left."):("You have only "+lives_left+" lives left.");
                outputTextView.setText(str);
                inputText.setVisibility(View.INVISIBLE);
                Thread.sleep(1500);
                level = 6;
                myGameLoop(level);
            }
            else
            {
                outputTextView.setText("You have lost all your lives.");
                inputText.setVisibility(View.INVISIBLE);
                Thread.sleep(1500);
                lives_left = 3;
                Intent myIntent = new Intent(comprehensive.VisualOnlyWithDistraction.this,HomePage.class);
                startActivity(myIntent);
            }
        }
    }// This is the Submit Answer button in the visual only page

    public void printWithDelay(final String string_to_print, final int delay_time) // time in milli seconds
    {
        Handler handler = new Handler();
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do after delay_time milli seconds
                    // 1 second = 1000 milli second
                    TextView outputTextView = (TextView) findViewById(R.id.visual_only_output);
                    outputTextView.setText(string_to_print);
                }
            }, delay_time);
        }
    }// Prints the string_to_print with delay_time in the OutputTextView

    public void setAudioStopTime(final MediaPlayer audio , final int delay_time) // time in milli seconds
    {
        Handler audio_handler = new Handler();
        {
            audio_handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do after delay_time milli seconds
                    // 1 second = 1000 milli second
                    if (distraction_int==1)
                    {
                        // Visual Only Game with distraction.
                        audio.pause();
                        audio.reset();
                        //audio.release();
                    }
                }
            }, delay_time);
        }
    }// Stops the audio after delay_time milliseconds

    public void setAudioStartTime(final MediaPlayer audio , final int delay_time) // time in milli seconds
    {
        Handler audio_start_handler = new Handler();
        {
            audio_start_handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do after delay_time milli seconds
                    // 1 second = 1000 milli second
                    if ((audio != null)&&(back_button_pressed==0))
                        audio.start();
                }
            }, delay_time);
        }
    }// Starts the audio after delay_time milliseconds


    @Override
    public void onBackPressed()
    {
        /*super.onBackPressed();
        if((distraction_int==1)&&(audio !=null)&&(audio.isPlaying()==true))
        {
            audio.pause();
            audio.reset();
            //audio = null ;
            //audio.release();
        }
        else if((distraction_int==1)&&(audio !=null)&&(audio.isPlaying()==false))
        {
            audio.pause();
            audio.reset();
            //audio = null ;
            //audio.release();
        }
        back_button_pressed = 1 ;
        Intent intent = new Intent(VisualOnlyWithDistraction.this, PracticeHomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to leave?")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(comprehensive.VisualOnlyWithDistraction.this,HomePage.class);
                        startActivity(myIntent);
                        audio.pause();
                        audio.reset();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
