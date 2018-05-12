package com.example.om.mygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Leaderboard extends AppCompatActivity {
    public static JSONObject leaderboard_score_json = new JSONObject();
    public static JSONArray leaderboard_score_array = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leaderboard);
        getSupportActionBar().hide();

        String name;
        String rank;
        String score;

        for(int i=0;i<leaderboard_score_array.length();i++) {
            try {
                Log.d("Top-scorers",leaderboard_score_array.getJSONObject(i).getString("user_name")+"");
                Log.d("Top-scorers",leaderboard_score_array.getJSONObject(i).getString("score")+"");
                Log.d("Top-scorers",leaderboard_score_array.getJSONObject(i).getString("rank")+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
