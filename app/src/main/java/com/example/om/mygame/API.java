package com.example.om.mygame;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import authentication.LogIn;
import authentication.PersonCredentials;
import authentication.Register;


/**
 * Created by root on 3/31/17.
 */


/////////////////////////AsyncTask<Params, Progress, Result>
public class API extends AsyncTask<String , String , String>
{
    public JSONObject jsonObject;
    public Context mContext;
    public String requestURL;
    public TextView testing;
    public int which_class;
    public ProgressBar progressBar;
    public static String oid;

    public API(String oid,JSONObject jsonObject, String requestURL, TextView testing, Context mContext, int which_class, ProgressBar progressBar)
    {
        this.oid = oid;
        this.requestURL = requestURL;
        this.testing = testing;
        this.mContext = mContext;
        this.which_class = which_class;
        this.progressBar = progressBar;
        this.jsonObject = jsonObject;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        Log.d("URLEncoder",result.toString());

        return result.toString();
    }

    @Override
    protected void onPreExecute()
    {

    }

    @Override
    protected void onProgressUpdate(String... strings)
    {

    }

    @Override
    protected String doInBackground(String... strings) {
        progressBar.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        String response = "";
        try {

            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonObject.toString());
            wr.flush();

//display what returns the POST request

            StringBuilder sb = new StringBuilder();
            int responseCode = con.getResponseCode();


            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                try {
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                response = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.post(new Runnable()
        {

            @Override
            public void run()
            {
                progressBar.setVisibility(View.GONE);
            }
        });
        try
        {
            switch (which_class)
            {
                case 0: // register
                {
                    JSONObject res = new JSONObject(result);
                    Boolean success = res.getBoolean("success");
                    if(success)
                    {
                        Intent myIntent = new Intent(mContext,HomePage.class);
                        mContext.startActivity(myIntent);
                    }
                    else
                    {
                        testing.setText("** "+res.getString("response")+" **");
                    }
                }
                break;
                case 1: // login
                {
                    JSONObject res = new JSONObject(result);
                    Boolean success = res.getBoolean("success");
                    if(success)
                    {
                        oid = res.getString("_id");
                        Intent myIntent = new Intent(mContext,HomePage.class);
                        mContext.startActivity(myIntent);
                    }
                    else
                    {
                        testing.setText("** "+res.getString("response")+" **");
                    }
                }
                case 2: //comprehensive...
                {

                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}