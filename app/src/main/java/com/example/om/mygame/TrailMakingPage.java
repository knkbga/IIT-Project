package com.example.om.mygame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;
//import java.lang.Thread ;
//import android.os.Handler;

public class TrailMakingPage extends AppCompatActivity {
    int touch_sequence =0;
    boolean game_on = true;
    int time_taken = 0 ;   // This is the total time taken to complete the game in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_making_page);
        calculateTimeTaken();
    }

    public void calculateTimeTaken()
    {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (game_on==true)
                {
                    time_taken = time_taken + 1;
                }
                else
                {
                    //Game Over or Completed ie. game_on==false
                    timer.cancel();
                    //timer = null ;
                }
            }
        }, 0, 1000);
    }

    public void trailImage1(View view)
    {
        int trail_number = 1 ;
        ImageButton button1=(ImageButton) findViewById(R.id.trail_Image_Button1);
        button1.setAlpha(190);
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage2(View view)
    {
        ImageButton button1=(ImageButton) findViewById(R.id.trail_Image_Button1);
        ImageButton button2=(ImageButton) findViewById(R.id.trail_Image_Button2);
        button1.setAlpha(255);
        button2.setAlpha(190);
        int trail_number = 2 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage3(View view)
    {
        ImageButton button2=(ImageButton) findViewById(R.id.trail_Image_Button2);
        ImageButton button3=(ImageButton) findViewById(R.id.trail_Image_Button3);
        button2.setAlpha(255);
        button3.setAlpha(190);
        int trail_number = 3 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage4(View view)
    {
        ImageButton button3=(ImageButton) findViewById(R.id.trail_Image_Button3);
        ImageButton button4=(ImageButton) findViewById(R.id.trail_Image_Button4);
        button3.setAlpha(255);
        button4.setAlpha(190);
        int trail_number = 4 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage5(View view)
    {
        ImageButton button4=(ImageButton) findViewById(R.id.trail_Image_Button4);
        ImageButton button5=(ImageButton) findViewById(R.id.trail_Image_Button5);
        button4.setAlpha(255);
        button5.setAlpha(190);
        int trail_number = 5 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage6(View view)
    {
        ImageButton button5=(ImageButton) findViewById(R.id.trail_Image_Button5);
        ImageButton button6=(ImageButton) findViewById(R.id.trail_Image_Button6);
        button5.setAlpha(255);
        button6.setAlpha(190);
        int trail_number = 6 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage7(View view)
    {
        ImageButton button6=(ImageButton) findViewById(R.id.trail_Image_Button6);
        ImageButton button7=(ImageButton) findViewById(R.id.trail_Image_Button7);
        button6.setAlpha(255);
        button7.setAlpha(190);
        int trail_number = 7 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage8(View view)
    {
        ImageButton button7=(ImageButton) findViewById(R.id.trail_Image_Button7);
        ImageButton button8=(ImageButton) findViewById(R.id.trail_Image_Button8);
        button7.setAlpha(255);
        button8.setAlpha(190);
        int trail_number = 8 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage9(View view)
    {
        ImageButton button8=(ImageButton) findViewById(R.id.trail_Image_Button8);
        ImageButton button9=(ImageButton) findViewById(R.id.trail_Image_Button9);
        button8.setAlpha(255);
        button9.setAlpha(190);
        int trail_number = 9 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage10(View view)
    {
        ImageButton button9=(ImageButton) findViewById(R.id.trail_Image_Button9);
        ImageButton button10=(ImageButton) findViewById(R.id.trail_Image_Button10);
        button9.setAlpha(255);
        button10.setAlpha(190);
        int trail_number = 10 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage11(View view)
    {
        ImageButton button10=(ImageButton) findViewById(R.id.trail_Image_Button10);
        ImageButton button11=(ImageButton) findViewById(R.id.trail_Image_Button11);
        button10.setAlpha(255);
        button11.setAlpha(190);
        int trail_number = 11 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage12(View view)
    {
        ImageButton button11=(ImageButton) findViewById(R.id.trail_Image_Button11);
        ImageButton button12=(ImageButton) findViewById(R.id.trail_Image_Button12);
        button11.setAlpha(255);
        button12.setAlpha(190);
        int trail_number = 12 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage13(View view)
    {
        ImageButton button12=(ImageButton) findViewById(R.id.trail_Image_Button12);
        ImageButton button13=(ImageButton) findViewById(R.id.trail_Image_Button13);
        button12.setAlpha(255);
        button13.setAlpha(190);
        int trail_number = 13 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage14(View view)
    {
        ImageButton button13=(ImageButton) findViewById(R.id.trail_Image_Button13);
        ImageButton button14=(ImageButton) findViewById(R.id.trail_Image_Button14);
        button13.setAlpha(255);
        button14.setAlpha(190);
        int trail_number = 14 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage15(View view)
    {
        ImageButton button14=(ImageButton) findViewById(R.id.trail_Image_Button14);
        ImageButton button15=(ImageButton) findViewById(R.id.trail_Image_Button15);
        button14.setAlpha(255);
        button15.setAlpha(190);
        int trail_number = 15 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage16(View view)
    {
        ImageButton button15=(ImageButton) findViewById(R.id.trail_Image_Button15);
        ImageButton button16=(ImageButton) findViewById(R.id.trail_Image_Button16);
        button15.setAlpha(255);
        button16.setAlpha(190);
        int trail_number = 16 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage17(View view)
    {
        ImageButton button16=(ImageButton) findViewById(R.id.trail_Image_Button16);
        ImageButton button17=(ImageButton) findViewById(R.id.trail_Image_Button17);
        button16.setAlpha(255);
        button17.setAlpha(190);
        int trail_number = 17 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage18(View view)
    {
        ImageButton button17=(ImageButton) findViewById(R.id.trail_Image_Button17);
        ImageButton button18=(ImageButton) findViewById(R.id.trail_Image_Button18);
        button17.setAlpha(255);
        button18.setAlpha(190);
        int trail_number = 18 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage19(View view)
    {
        ImageButton button18=(ImageButton) findViewById(R.id.trail_Image_Button18);
        ImageButton button19=(ImageButton) findViewById(R.id.trail_Image_Button19);
        button18.setAlpha(255);
        button19.setAlpha(190);
        int trail_number = 19 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage20(View view)
    {
        ImageButton button19=(ImageButton) findViewById(R.id.trail_Image_Button19);
        ImageButton button20=(ImageButton) findViewById(R.id.trail_Image_Button20);
        button19.setAlpha(255);
        button20.setAlpha(190);
        int trail_number = 20 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage21(View view)
    {
        ImageButton button20=(ImageButton) findViewById(R.id.trail_Image_Button20);
        ImageButton button21=(ImageButton) findViewById(R.id.trail_Image_Button21);
        button20.setAlpha(255);
        button21.setAlpha(190);
        int trail_number = 21 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage22(View view)
    {
        ImageButton button21=(ImageButton) findViewById(R.id.trail_Image_Button21);
        ImageButton button22=(ImageButton) findViewById(R.id.trail_Image_Button22);
        button21.setAlpha(255);
        button22.setAlpha(190);
        int trail_number = 22 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage23(View view)
    {
        ImageButton button22=(ImageButton) findViewById(R.id.trail_Image_Button22);
        ImageButton button23=(ImageButton) findViewById(R.id.trail_Image_Button23);
        button22.setAlpha(255);
        button23.setAlpha(190);
        int trail_number = 23 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage24(View view)
    {
        ImageButton button23=(ImageButton) findViewById(R.id.trail_Image_Button23);
        ImageButton button24=(ImageButton) findViewById(R.id.trail_Image_Button24);
        button23.setAlpha(255);
        button24.setAlpha(190);
        int trail_number = 24 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void trailImage25(View view)
    {
        ImageButton button24=(ImageButton) findViewById(R.id.trail_Image_Button24);
        //ImageButton button25=(ImageButton) findViewById(R.id.trail_Image_Button25);
        button24.setAlpha(255);
        //button25.setAlpha(190);
        int trail_number = 25 ;
        if(trail_number == touch_sequence+1)
        {
            // Right touch sequence
            touch_sequence = trail_number ;
            setButtonsUnClickable();
            game_on = false ;
            startTrailMakingLevel2();
            /*
            Context context = this;
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle("Congratulations!!");
            builder1.setMessage("You completed the game in " + Integer.toString(time_taken)+ " seconds.");
            AlertDialog alert11 = builder1.create();
            alert11.show();
            */
        }
        else
        {
            // Show Error message and game stops
            touch_sequence = 0 ;
            showErrorMessage();
        }
    }
    public void setButtonsUnClickable()
    {
        ImageButton button1=(ImageButton) findViewById(R.id.trail_Image_Button1);
        button1.setEnabled(false);
        ImageButton button2=(ImageButton) findViewById(R.id.trail_Image_Button2);
        button2.setEnabled(false);
        ImageButton button3=(ImageButton) findViewById(R.id.trail_Image_Button3);
        button3.setEnabled(false);
        ImageButton button4=(ImageButton) findViewById(R.id.trail_Image_Button4);
        button4.setEnabled(false);
        ImageButton button5=(ImageButton) findViewById(R.id.trail_Image_Button5);
        button5.setEnabled(false);
        ImageButton button6=(ImageButton) findViewById(R.id.trail_Image_Button6);
        button6.setEnabled(false);
        ImageButton button7=(ImageButton) findViewById(R.id.trail_Image_Button7);
        button7.setEnabled(false);
        ImageButton button8=(ImageButton) findViewById(R.id.trail_Image_Button8);
        button8.setEnabled(false);
        ImageButton button9=(ImageButton) findViewById(R.id.trail_Image_Button9);
        button9.setEnabled(false);
        ImageButton button10=(ImageButton) findViewById(R.id.trail_Image_Button10);
        button10.setEnabled(false);
        ImageButton button11=(ImageButton) findViewById(R.id.trail_Image_Button11);
        button11.setEnabled(false);
        ImageButton button12=(ImageButton) findViewById(R.id.trail_Image_Button12);
        button12.setEnabled(false);
        ImageButton button13=(ImageButton) findViewById(R.id.trail_Image_Button13);
        button13.setEnabled(false);
        ImageButton button14=(ImageButton) findViewById(R.id.trail_Image_Button14);
        button14.setEnabled(false);
        ImageButton button15=(ImageButton) findViewById(R.id.trail_Image_Button15);
        button15.setEnabled(false);
        ImageButton button16=(ImageButton) findViewById(R.id.trail_Image_Button16);
        button16.setEnabled(false);
        ImageButton button17=(ImageButton) findViewById(R.id.trail_Image_Button17);
        button17.setEnabled(false);
        ImageButton button18=(ImageButton) findViewById(R.id.trail_Image_Button18);
        button18.setEnabled(false);
        ImageButton button19=(ImageButton) findViewById(R.id.trail_Image_Button19);
        button19.setEnabled(false);
        ImageButton button20=(ImageButton) findViewById(R.id.trail_Image_Button20);
        button20.setEnabled(false);
        ImageButton button21=(ImageButton) findViewById(R.id.trail_Image_Button21);
        button21.setEnabled(false);
        ImageButton button22=(ImageButton) findViewById(R.id.trail_Image_Button22);
        button22.setEnabled(false);
        ImageButton button23=(ImageButton) findViewById(R.id.trail_Image_Button23);
        button23.setEnabled(false);
        ImageButton button24=(ImageButton) findViewById(R.id.trail_Image_Button24);
        button24.setEnabled(false);
        ImageButton button25=(ImageButton) findViewById(R.id.trail_Image_Button25);
        button25.setEnabled(false);
    }
    public void startTrailMakingLevel2()
    {
        Intent intent = new Intent(this, Trail2Game.class);
        startActivity(intent);
    }

    public void showErrorMessage()
    {
        game_on = false;
        setButtonsUnClickable();
        Context context = this;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Game Terminated");
        builder1.setMessage("Oops! You tapped a wrong sequence. Your time taken is " + Integer.toString(time_taken)+" seconds.");
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
