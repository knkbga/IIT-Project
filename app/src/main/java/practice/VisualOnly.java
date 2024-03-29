package practice;

import android.content.Context;
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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.om.mygame.PracticeHomePage;
import com.example.om.mygame.R;
import com.example.om.mygame.Sessions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TimeZone;

import authentication.LandingPage;
import authentication.PersonCredentials;

public class VisualOnly extends AppCompatActivity {
    private JSONObject request;
    public String route;
    protected int lives_left;
    private int level = 6;
    private String questionString;
    private static MediaPlayer audio;
    private static int back_button_pressed;
    private TextView levelLabel;
    private Chronometer timer;
    private SimpleDateFormat df;
    private Context mContext;
    private ProgressBar progressBar;
    private JSONArray different_events;
    private JSONObject individual_event;
    private int number_of_events =-1;
    private TextView setNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lives_left = Sessions.max_lives_every_game;

        setContentView(R.layout.activity_visual_only);
        setNumber = (TextView) findViewById(R.id.setNumber);
        setNumber.setVisibility(View.INVISIBLE);

        different_events = new JSONArray();
        request = new JSONObject();
        mContext= getBaseContext();
        df = new SimpleDateFormat( "dd-MM-yyyy 'at' kk:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("IST"));

        Log.d("_id",PersonCredentials.oid);

        try {
            request.put("_id",PersonCredentials.oid);
            request.put("start_session",df.format(new java.util.Date()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        route = "/comprehensive/gaming/visual";

        super.onCreate(savedInstanceState);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent() ;
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_visual_only);
        levelLabel = (TextView) findViewById(R.id.levelIndicator);
        levelLabel.setText("Level - "+(level));
        timer = (Chronometer) findViewById(R.id.timer);
        myGameLoop(level) ;
    }

    public void myGameLoop(int level)
    {
        individual_event = new JSONObject();
        timer.setVisibility(View.VISIBLE);
        timer.start();
        questionString = "" ;
        int i, lenth = level ;
        int total_delay_time ;
        back_button_pressed = 0;
        int[] randomArray = new int[lenth] ;
        printWithDelay("",1000);
        total_delay_time = 1000;
        for(i = 0;i<lenth;i++)
        {
            Random r = new Random();
            int rand = r.nextInt(10) ;  // Generates a random number from 0-9
            randomArray[i] = rand ;
            questionString = questionString + Integer.toString(rand) ;

            try
            {
                individual_event.put("time_of_start",df.format(new java.util.Date()));
                individual_event.put("string_question",questionString);
                individual_event.put("level",level);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            // Delay of 1 Has to inserted here
            total_delay_time = 1000 + 1000*(i+1);
            printWithDelayDigit("",total_delay_time-50); //Just for clearness between the first number and the next number
            printWithDelayDigit(Integer.toString(rand),total_delay_time);
        }
        // Delay of 2 Has to be inserted here
        printWithDelayDigit("",total_delay_time + 1500);
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
        try {
            individual_event.put("time_of_end",df.format(new java.util.Date()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // From here the answer that the user gives will be checked by clicking on the Submit button on the visual only page
    } // The work of this loop is to display the random numbers with their audio after appropriate delay & make questionString

    public void visualOnlySubmitButton(View view) throws InterruptedException {
        visualOnlySubmitButton();
    }

    public void visualOnlySubmitButton() throws InterruptedException {
        Button submit_button  = (Button)findViewById(R.id.visual_only_submit_button);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        try {
            individual_event.put("time_of_submission",df.format(new java.util.Date()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        number_of_events++;
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

        try {
            individual_event.put("string_answer",answerString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //////////////////////////////////////////////////////////////////////
        if ((questionString.equals(answerString))&&(lenth < Sessions.max_levels_every_game))//correct but not final level
        {
            different_events = new JSONArray();

            try {

                // adding current ongoing event's variables
                individual_event.put("set_number", Sessions.Sets_game);
                individual_event.put("lives_till_used",(Sessions.max_lives_every_game-lives_left+1));
                individual_event.put("success","true");

                // adding individual_event in different_events
                different_events.put(individual_event);

                request.put("point_end", level);
                request.put("end_session", df.format(new java.util.Date()));

                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            outputTextView.setText("Correct Answer ! Get Ready for level : "+Integer.toString((level)+1));
            Thread.sleep(1500);
            level = level + 1;
            lives_left= Sessions.max_lives_every_game;
            inputText.setText("");
            levelLabel.setText("Level - "+(level));
            myGameLoop(level);
        }
        else if ((questionString.equals(answerString))&&(lenth == Sessions.max_levels_every_game)) // correct and final level
        {
            different_events = new JSONArray();
            try {

                // adding current ongoing event's variables
                individual_event.put("set_number", Sessions.Sets_game);
                individual_event.put("lives_till_used",(Sessions.max_lives_every_game+1));
                individual_event.put("success","true");

                // adding individual_event in different_events
                different_events.put(individual_event);

                request.put("point_end", level);
                request.put("end_session", df.format(new java.util.Date()));

                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                request.put("end_session",df.format(new java.util.Date()));
                request.put("point_end",level);

                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            printWithDelay("Congratulations! All Levels Completed successfully. Starting next game...",1500);
            Thread.sleep(1500);

            submit_button.setVisibility(View.INVISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent mainIntent = new Intent(VisualOnly.this, PracticeHomePage.class);
                    VisualOnly.this.startActivity(mainIntent);
                    VisualOnly.this.finish();
                }
            }, 1500);

        }
        else//wrong answer given
        {
            different_events = new JSONArray();
            try {

                // adding current ongoing event's variables
                individual_event.put("set_number", Sessions.Sets_game);
                individual_event.put("lives_till_used",(Sessions.max_lives_every_game+1));
                individual_event.put("success","false");

                // adding individual_event in different_events
                different_events.put(individual_event);

                request.put("point_end", level);
                request.put("end_session", df.format(new java.util.Date()));

                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lives_left--;
            if(lives_left > 0)
            {
                String str = "";
                str = (lives_left == 1)?("You have one life left."):("You have only "+lives_left+" lives left.");
                outputTextView.setText(str);
                inputText.setVisibility(View.INVISIBLE);
                Thread.sleep(1500);
                levelLabel.setText("Level - "+(level));
                myGameLoop(level);
            }
            else // 0 lives left
            {

                try {
                    request.put("end_session", df.format(new java.util.Date()));
                    request.put("point_end", level);

                    request.put("different_events",different_events);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                inputText.setVisibility(View.INVISIBLE);
                Thread.sleep(1500);
                lives_left = Sessions.max_lives_every_game;

                printWithDelay("You have lost all your lives. Starting next game...",1500);
                Thread.sleep(1500);

                submit_button.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent mainIntent = new Intent(VisualOnly.this, PracticeHomePage.class);
                        VisualOnly.this.startActivity(mainIntent);
                        VisualOnly.this.finish();
                    }
                }, 1500);

            }
        }
        inputText.setText("");
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
                    TextView outputTextView = (TextView) findViewById(R.id.visual_only_output);
                    outputTextView.setText(string_to_print);
                }
            }, delay_time);
        }
    }// Prints the string_to_print with delay_time in the OutputTextView

    public void printWithDelayDigit(final String string_to_print, final int delay_time) // time in milli seconds
    {
        Handler handler = new Handler();
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // D0 something after delay_time milli seconds
                    // 1 second = 1000 milli second
                    TextView outputTextView = (TextView) findViewById(R.id.digits_show);
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
        Intent intent = new Intent(VisualAndAuditory.this, PracticeHomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to leave ?")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(VisualOnly.this,PracticeHomePage.class);
                        try {
                            different_events = new JSONArray();

                            request.put("point_end", level);
                            request.put("end_session", df.format(new java.util.Date()));

                            request.put("different_events",different_events);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

                        Log.d("API","Request to api:: '"+ LandingPage.url+route+"'"+request.toString());

                        startActivity(myIntent);
                        VisualOnly.super.onBackPressed();
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