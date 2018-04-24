package com.example.om.mygame;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class Leaderboard extends AppCompatActivity {
    public static JSONObject leaderboard_score_json = new JSONObject();
    public static JSONArray leaderboard_score_array = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leaderboard);
        getSupportActionBar().hide();
    }

}
