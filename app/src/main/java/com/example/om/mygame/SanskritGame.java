package com.example.om.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SanskritGame extends AppCompatActivity {
    private static int gameNumber ;
    private static String gameName ;
    private boolean game_selected ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanskrit_game);

        final ListView listView = (ListView) findViewById(R.id.sanskrit_games_list);
        String[] values = new String[] {"Pictorial Matching Game","Card Game","Flash Card Game","Hangman Game","Fill ups","Speaking Exercises","Listening and Writing"} ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // ListView Clicked item index
                gameNumber  = position;   // starting from 0 to (Total number of games) - 1
                // ListView Clicked item value
                gameName  = (String) listView.getItemAtPosition(position);
                game_selected = true ;

                if ((gameNumber==0)&&(game_selected)) // Pictorial Matching Game
                {
                    startPictorialMatchingGame();
                }
                else if ((gameNumber==1)&&(game_selected)) // Card Game
                {
                    startCardGame();
                }
                else if((gameNumber==2)&&(game_selected)) // Flash Card Game
                {
                    startFlashCardGame();
                }
                else if((gameNumber==3)&&(game_selected)) // Hangman Game
                {
                    startHangmanGame();
                }
                else if ((gameNumber==4)&&(game_selected)) // Fill ups Game
                {
                    startFillUpsGame();
                }
                else if ((gameNumber==5)&&(game_selected)) // Speaking Exercises Game
                {
                    startSpeakingExercisesGame();
                }
                else if ((gameNumber==6)&&(game_selected)) // Listening and Writing Game
                {
                    startListeningAndWritingGame();
                }

            }
        });
    }

    public void startPictorialMatchingGame()
    {
        //Pictorial Matching Game (1)
        Intent intent = new Intent(this, PictorialMatching.class);
        startActivity(intent);
        game_selected = false ;
    }

    public void startCardGame()
    {
        // Card Game (1)
        Intent intent = new Intent(this, CardGame.class);
        startActivity(intent);
        game_selected = false ;
    }

    public void startFlashCardGame()
    {
        // Flash Card Game (2)
        Intent intent = new Intent(this, FlashCard.class);
        startActivity(intent);
        game_selected = false ;
    }

    public void startHangmanGame()
    {
        // Hangman Game (3)
        Intent intent = new Intent(this, Hangman.class);
        startActivity(intent);
        game_selected = false ;
    }

    public void startFillUpsGame()
    {
        //  Fill Ups Game (4)
        Intent intent = new Intent(this, FillUps.class);
        startActivity(intent);
        game_selected = false ;
    }

    public void startSpeakingExercisesGame()
    {
        //Speaking Exercises Game (5)
        Intent intent = new Intent(this, SpeakingExercises.class);
        startActivity(intent);
        game_selected = false ;
    }

    public void startListeningAndWritingGame()
    {
        //Listening And Writing Game (7)
        Intent intent = new Intent(this, ListeningAndWriting.class);
        startActivity(intent);
        game_selected = false ;
    }

}
