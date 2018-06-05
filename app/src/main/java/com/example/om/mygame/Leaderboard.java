package com.example.om.mygame;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

public class Leaderboard extends AppCompatActivity {
    public static JSONArray leaderboard_score_array = new JSONArray();
    public static String[] name;
    public static String[] rank;
    public static String[] score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leaderboard);
        getSupportActionBar().hide();

        ListView listView = (ListView) findViewById(R.id.listView);

        CustomerAdapter customerAdapter = new CustomerAdapter();

        listView.setAdapter(customerAdapter);
    }

    class CustomerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return leaderboard_score_array.length();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            view = getLayoutInflater().inflate(R.layout.leader_board_list_element,null);

            TextView tv_rank = (TextView)view.findViewById(R.id.scorer_rank);
            TextView tv_name = (TextView)view.findViewById(R.id.scorer_name);
            TextView tv_score = (TextView)view.findViewById(R.id.scorer_score);

            tv_rank.setText(rank[i]);
            tv_name.setText(name[i]);
            tv_score.setText(score[i]);

            Log.d("log-specific","rank:\t"+tv_rank.getText()+"");
            Log.d("log-specific","name:\t"+tv_name.getText()+"");
            Log.d("log-specific","score:\t"+tv_score.getText()+"");
            Log.d("log-specific","i:\t"+i+"");

            return view;
        }
    }
}
