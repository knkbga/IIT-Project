package com.example.om.mygame;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import authentication.LogIn;

/**
 * Created by root on 4/17/17.
 */

public class Connectivity extends AsyncTask<String , String , Boolean> {

    public String _url;
    public Context context;
    public Boolean isConnected;
    public int which;

    public Connectivity(String _url,Context context,Boolean isConnected,int which)
    {
        this._url = _url;
        this.context = context;
        this.isConnected = isConnected;
    }
/*
    public static boolean isConnected(Context context) {

        Log.d("_url",_url);

        ConnectivityManager cm = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL(_url);
                HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
                urlc.setRequestProperty("User-Agent", "test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(5000); // mTimeout is in seconds
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                Log.i("warning", "Error checking internet connection", e);
                return false;
            }
        }

        return false;

    }*/

    @Override
    protected Boolean doInBackground(String... params) {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL(_url);
                HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
                urlc.setRequestProperty("User-Agent", "test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(5000); // mTimeout is in seconds
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    isConnected = true;
                } else {
                    isConnected = false;
                }
            } catch (IOException e) {
                Log.i("warning", "Error checking internet connection", e);
                isConnected = false;
            }
        }

        isConnected = false;

        Log.d("why","variables returned");

        return isConnected;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        isConnected = aBoolean;
        Log.d("why",isConnected.toString());
        switch (which)
        {
            case 0:
            {
                LogIn.isConnected = aBoolean;
            }
            break;
            case 1:
            {
                HomePage.isConnected = aBoolean;
            }
            default:
            {
                Log.d("why","In switch");
            }
        }

    }
}
