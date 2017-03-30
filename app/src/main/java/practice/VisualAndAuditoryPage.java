package practice;

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

import java.util.Random;

public class VisualAndAuditoryPage extends AppCompatActivity {
    private int level = 6 ;
    private String questionString  ;
    private static MediaPlayer audio ;
    private static int back_button_pressed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_and_auditory_page);
        Intent intent = getIntent() ;
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_visual_and_auditory_page);
        myGameLoop(level) ;
    }

    public void myGameLoop(int level)
    {
        questionString = "" ;
        int i, lenth = level ;
        int total_delay_time ;
        back_button_pressed = 0;
        int[] randomArray = new int[lenth] ;
        TextView outputTextView = (TextView) findViewById(R.id.visual_and_auditory_output);
        printWithDelay("",1000);
        total_delay_time = 1000 ;
        for(i = 0;i<lenth;i++)
        {
            Random r = new Random();
            int rand = r.nextInt(10) ;  // Generates a random number from 0-9
            randomArray[i] = rand ;
            questionString = questionString + Integer.toString(rand) ;
            // Delay of 1 Has to inserted here
            total_delay_time = 1000 + 1000*(i+1);
            printWithDelay("",total_delay_time-50); //Just for clearness between the first number and the next number
            printWithDelay(Integer.toString(rand),total_delay_time);
            if (rand==0)
            {
                audio =  MediaPlayer.create(this, R.raw.zero);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==1)
            {
                audio =  MediaPlayer.create(this, R.raw.one);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==2)
            {
                audio =  MediaPlayer.create(this, R.raw.two);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==3)
            {
                audio =  MediaPlayer.create(this, R.raw.three);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==4)
            {
                audio =  MediaPlayer.create(this, R.raw.four);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==5)
            {
                audio =  MediaPlayer.create(this, R.raw.five);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==6)
            {
                audio =  MediaPlayer.create(this, R.raw.six);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==7)
            {
                audio =  MediaPlayer.create(this, R.raw.seven);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==8)
            {
                audio =  MediaPlayer.create(this, R.raw.eight);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (rand==9)
            {
                audio =  MediaPlayer.create(this, R.raw.nine);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
        }
        // Delay of 2 Has to be inserted here
        printWithDelay("",total_delay_time + 1500);
        //// Input Text & Submit Button Only Visible when all numbers have already been displayed in the outputTextView////
        final EditText inputText = (EditText) findViewById(R.id.visual_and_auditory_input);
        final Button submit_button  = (Button)findViewById(R.id.visual_and_auditory_submit_button) ;
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
        // From here the answer that the user gives will be checked by clicking on the Submit button on the visual only page
    } // The work of this loop is to display the random numbers with their audio after appropriate delay & make questionString

    public void visualOnlySubmitButton(View view) throws InterruptedException {
        visualOnlySubmitButton();
    }

    public void visualOnlySubmitButton() throws InterruptedException {
        String answerString ;
        TextView outputTextView = (TextView) findViewById(R.id.visual_and_auditory_output);
        final EditText inputText = (EditText) findViewById(R.id.visual_and_auditory_input);
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
            outputTextView.setText("Correct Answer ! Get Ready for level : "+Integer.toString(level-5));
            level = level + 1 ;
            inputText.setText("") ;
            myGameLoop(level);
        }
        else if ((questionString.equals(answerString))&&(lenth ==14))
        {
            outputTextView.setText("Congratulations! All Levels Completed successfully");
            startActivity(new Intent(practice.VisualAndAuditoryPage.this, practice.TrialsWDistraction.class));
        }
        else
        {
            outputTextView.setText("Oops Wrong Answer. Try Again!");
            inputText.setVisibility(View.INVISIBLE);
            Thread.sleep(1500);
            myGameLoop(level);
        }
    }// This is the Submit Answer button in the visual only page

    public void printWithDelay(final String string_to_print, final int delay_time) // time in milli seconds
    {
        Handler handler = new Handler();
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // D0 something after delay_time milli seconds
                    // 1 second = 1000 milli second
                    TextView outputTextView = (TextView) findViewById(R.id.visual_and_auditory_output);
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
                    audio.pause();
                    audio.reset();
                    //audio.release();

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
    }// Stops the audio after delay_time milliseconds

    @Override
    public void onBackPressed()
    {
        /*super.onBackPressed();
        if((audio !=null)&&(audio.isPlaying()==true))
        {
            audio.pause();
            audio.reset();
            //audio = null ;
            //audio.release();
        }
        else if((audio !=null)&&(audio.isPlaying()==false))
        {
            audio.pause();
            audio.reset();
            //audio = null ;
            //audio.release();
        }
        back_button_pressed = 1 ;
        Intent intent = new Intent(VisualAndAuditoryPage.this, PracticeHomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to leave?")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(practice.VisualAndAuditoryPage.this,HomePage.class);
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
