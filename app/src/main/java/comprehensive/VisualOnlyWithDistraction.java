package comprehensive;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.om.mygame.API;
import com.example.om.mygame.HomePage;
import com.example.om.mygame.R;
import com.example.om.mygame.Sessions;
import com.tekle.oss.android.audio.VolumeControl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TimeZone;

import authentication.LandingPage;
import authentication.PersonCredentials;

public class VisualOnlyWithDistraction extends AppCompatActivity {
    private JSONObject request;
    public String route;
    protected int lives_left;
    private int level = Sessions.starting_level;
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
    private String TotalVolume;
    private int individual_event_score;
    private int game_score;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        game_score = 0;
        lives_left = Sessions.max_lives_every_game;

        setContentView(R.layout.activity_visual_only_with_distraction);
        setNumber = (TextView) findViewById(R.id.setNumber);

        setNumber.setText("Set = "+ Sessions.Sets_game);

        different_events = new JSONArray();
        request = new JSONObject();
        mContext= getBaseContext();
        df = new SimpleDateFormat( "dd-MM-yyyy 'at' kk:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("IST"));

        try {
            request.put("set", Sessions.Sets_game);
            request.put("_id",PersonCredentials.oid);
            request.put("start_session",df.format(new java.util.Date()));
            request.put("session_token", Sessions.session_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("TIME",df.format(new java.util.Date()));

        route = "/comprehensive/gaming/with";

        super.onCreate(savedInstanceState);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent() ;
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_visual_and_auditory_page);
        levelLabel = (TextView) findViewById(R.id.levelIndicator);
        levelLabel.setText("Level - "+(level));
        timer = (Chronometer) findViewById(R.id.timer);
        myGameLoop(level) ;
    }

    public void myGameLoop(int level)
    {
        TotalVolume = "";
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

        try
        {
            individual_event.put("time_of_start",df.format(new java.util.Date()));
            individual_event.put("level",level);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        for(i = 0;i<lenth;i++)
        {
            Random r = new Random();
            int rand = r.nextInt(10) ;  // Generates a random number from 0-9
            randomArray[i] = rand ;
            questionString = questionString + Integer.toString(rand) ;


            // Delay of 1 Has to inserted here
            total_delay_time = 1000 + 1000*(i+1);
            printWithDelayDigit("",total_delay_time-50); //Just for clearness between the first number and the next number
            printWithDelayDigit(Integer.toString(rand),total_delay_time);

            int distraction_audio = new Random().nextInt(10);

            if (distraction_audio==0)
            {
                audio =  MediaPlayer.create(this, R.raw.zero);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==1)
            {
                audio =  MediaPlayer.create(this, R.raw.one);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==2)
            {
                audio =  MediaPlayer.create(this, R.raw.two);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==3)
            {
                audio =  MediaPlayer.create(this, R.raw.three);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==4)
            {
                audio =  MediaPlayer.create(this, R.raw.four);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==5)
            {
                audio =  MediaPlayer.create(this, R.raw.five);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==6)
            {
                audio =  MediaPlayer.create(this, R.raw.six);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==7)
            {
                audio =  MediaPlayer.create(this, R.raw.seven);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==8)
            {
                audio =  MediaPlayer.create(this, R.raw.eight);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
            else if (distraction_audio==9)
            {
                audio =  MediaPlayer.create(this, R.raw.nine);
                setAudioStartTime(audio,total_delay_time);
                setAudioStopTime(audio,total_delay_time+1000-50);
            }
        }

        try
        {
            individual_event.put("string_question",questionString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Delay of 2 Has to be inserted here
        printWithDelayDigit("",total_delay_time + 1500);
        //// Input Text & Submit Button Only Visible when all numbers have already been displayed in the outputTextView////
        final EditText inputText = (EditText) findViewById(R.id.visual_only_with_distraction_input);
        final Button submit_button  = (Button)findViewById(R.id.visual_only_with_distraction_submit_button) ;
        inputText.setEnabled(false); inputText.setVisibility(View.GONE);
        inputText.setInputType(InputType.TYPE_NULL);
        submit_button.setVisibility(View.INVISIBLE) ;
        Handler input_textbox_handler = new Handler();
        {
            int delay_time = total_delay_time + 1500 ;
            input_textbox_handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do after delay_time milli seconds
                    // 1 second = 1000 milli second
                    inputText.setEnabled(true); inputText.setVisibility(View.VISIBLE);
                    inputText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
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
                        visualOnlyWithDistractionSubmitButton();
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

    public void visualOnlyWithDistractionSubmitButton(View view) throws InterruptedException {
        visualOnlyWithDistractionSubmitButton();
    }

    public void visualOnlyWithDistractionSubmitButton() throws InterruptedException {
        try {
            request.put("name",PersonCredentials.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Button submit_button  = (Button)findViewById(R.id.visual_only_with_distraction_submit_button) ;
        try {
            individual_event.put("time_of_submission",df.format(new java.util.Date()));
            TotalVolume = TotalVolume.substring(0, TotalVolume.length() - 1);
            individual_event.put("total_volume",TotalVolume);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        number_of_events++;
        String answerString ;
        TextView outputTextView = (TextView) findViewById(R.id.visual_only_with_distraction_output);
        final EditText inputText = (EditText) findViewById(R.id.visual_only_with_distraction_input);
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
//            different_events = new JSONArray();
            try {
                individual_event_score = level*10 -(Sessions.max_lives_every_game-lives_left);
                Sessions.session_score += individual_event_score;
                game_score += individual_event_score;

                // adding current ongoing event's variables
                individual_event.put("lives_till_used",(Sessions.max_lives_every_game-lives_left+1));
                individual_event.put("success","true");
                individual_event.put("individual_event_score",individual_event_score);

                // adding individual_event in different_events
                different_events.put(individual_event);

                request.put("end_session", df.format(new java.util.Date()));

                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            API obj1 = new API(PersonCredentials.oid,request,LandingPage.url+route,null,mContext,2,progressBar);

            obj1.execute();*/

            Log.d("Params","Correct but not final level"+request.toString());

            outputTextView.setText("Correct Answer ! Get Ready for level : "+Integer.toString((level)+1));
            Thread.sleep(1500);
            level = level + 1;
            lives_left= Sessions.max_lives_every_game;
            inputText.setText("");
            levelLabel.setText("Level - "+(level));
            myGameLoop(level);
        }
        else if ((questionString.equals(answerString))&&(lenth == Sessions.max_levels_every_game))// correct and final level
        {
//            different_events = new JSONArray();
            try {
                individual_event_score = level*10 -(Sessions.max_lives_every_game-lives_left);
                Sessions.session_score += individual_event_score;
                game_score += individual_event_score;

                // adding current ongoing event's variables
                individual_event.put("lives_till_used",(Sessions.max_lives_every_game-lives_left+1));
                individual_event.put("success","true");
                individual_event.put("individual_event_score",individual_event_score);

                // adding individual_event in different_events
                different_events.put(individual_event);

                request.put("end_session", df.format(new java.util.Date()));

                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            outputTextView.setText("Congratulations! All Levels Completed successfully. Starting next game...");
            Thread.sleep(1500);

            try {
                request.put("end_session",df.format(new java.util.Date()));
                request.put("game_score",game_score);
                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            API obj3 = new API(PersonCredentials.oid,request, LandingPage.url+route,null,mContext,2,progressBar);

            obj3.execute();


            Sessions.Sets_game++;

            printWithDelay("Starting your next Sessions",1500);
            Thread.sleep(1500);

            submit_button.setVisibility(View.INVISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent mainIntent = new Intent(comprehensive.VisualOnlyWithDistraction.this, Sessions.class);
                    VisualOnlyWithDistraction.this.startActivity(mainIntent);
                    VisualOnlyWithDistraction.this.finish();
                }
            }, 1500);


        }
        else//wrong answer given
        {
//            different_events = new JSONArray();
            try {
                individual_event_score = 0;
                Sessions.session_score += individual_event_score;
                game_score += individual_event_score;

                // adding current ongoing event's variables
                individual_event.put("lives_till_used",(Sessions.max_lives_every_game-lives_left+1));
                individual_event.put("success","false");
                individual_event.put("individual_event_score",individual_event_score);

                // adding individual_event in different_events
                different_events.put(individual_event);

                request.put("end_session", df.format(new java.util.Date()));

                request.put("different_events",different_events);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lives_left--;
            if(lives_left > 0)
            {
                /*API obj4 = new API(PersonCredentials.oid,request,LandingPage.url+route,null,mContext,2,progressBar);
                obj4.execute();*/
                String str = "";
                str = (lives_left == 1)?("You have one life left."):("You have only "+lives_left+" lives left.");
                outputTextView.setText(str);
                inputText.setEnabled(false); inputText.setVisibility(View.GONE);
                inputText.setInputType(InputType.TYPE_NULL); ;
                Thread.sleep(1500);
                levelLabel.setText("Level - "+(level));
                myGameLoop(level);
            }
            else
            {

                try {
                    request.put("end_session", df.format(new java.util.Date()));
                    request.put("game_score",game_score);
                    request.put("different_events",different_events);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

                API obj4 = new API(PersonCredentials.oid,request, LandingPage.url+route,null,mContext,2,progressBar);

                obj4.execute();

                lives_left = Sessions.max_lives_every_game;
                inputText.setEnabled(false); inputText.setVisibility(View.GONE);
                inputText.setInputType(InputType.TYPE_NULL); ;

                Sessions.Sets_game++;

                printWithDelay("You have lost all your lives. Starting your next Sessions...",1500);
                Thread.sleep(1500);

                submit_button.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent mainIntent = new Intent(comprehensive.VisualOnlyWithDistraction.this, Sessions.class);
                        VisualOnlyWithDistraction.this.startActivity(mainIntent);
                        VisualOnlyWithDistraction.this.finish();
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
                    TextView outputTextView = (TextView) findViewById(R.id.visual_only_with_distraction_output);
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
                    VolumeControl.sharedVolumeControl().configure(VisualOnlyWithDistraction.this, audio);
                    TotalVolume += Math.round(VolumeControl.sharedVolumeControl().getVolume()*10)+"-";
                    Log.d("Volume","Start"+Math.round(VolumeControl.sharedVolumeControl().getVolume()*10)+"");
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
        builder.setMessage("Not recommended to leave the game before you complete all 3 sets. Any unsaved data will be lost...")
                .setCancelable(false)
                .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(comprehensive.VisualOnlyWithDistraction.this,HomePage.class);
                        try {
//                            different_events = new JSONArray();
                            request.put("end_session", df.format(new java.util.Date()));

                            request.put("different_events",different_events);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /*ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                        API obj5 = new API(PersonCredentials.oid,request,LandingPage.url+route,null,mContext,2,progressBar);
                        obj5.execute();*/

                        Log.d("Params","Request to api:: '"+ LandingPage.url+route+"'"+request.toString());

                        startActivity(myIntent);
                        VisualOnlyWithDistraction.super.onBackPressed();
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