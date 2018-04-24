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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import authentication.LogIn;
import authentication.PersonCredentials;
import comprehensive.InstructionsVisualAndAuditory;


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

    private String methodType = "POST";

    //constructor with method type
    public API(String oid,JSONObject jsonObject, String requestURL, TextView testing, Context mContext, int which_class, ProgressBar progressBar,String methodType) {
        this.oid = oid;
        this.requestURL = requestURL;
        this.testing = testing;
        this.mContext = mContext;
        this.which_class = which_class;
        this.progressBar = progressBar;
        this.jsonObject = jsonObject;
        this.methodType = methodType;
    }
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
        Log.d("API","Request params ::\t"+ jsonObject);
        if(progressBar!=null) {
            progressBar.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                }
            });
        }
        String response = "";
        try {

            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod(methodType);

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
            Log.d("API","Called to url ::\t"+ requestURL);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.d("API","response (for url :: "+requestURL+") ::\t"+response);
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        if(progressBar!=null) {
            progressBar.post(new Runnable() {

                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
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
                        Intent myIntent = new Intent(mContext,LogIn.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                        PersonCredentials.oid = res.getString("_id");
                        PersonCredentials.name = res.getString("name");
                        Intent myIntent = new Intent(mContext,HomePage.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(myIntent);
                    }
                    else
                    {
                        testing.setText("** "+res.getString("response")+" **");
                    }
                }
                case 2: //Comprehensive Events
                {

                }
                break;
                case 3: // Forgot password
                {
                    JSONObject res = new JSONObject(result);
                    Boolean success = res.getBoolean("success");
                    if(success)
                    {
                        Intent myIntent = new Intent(mContext,ResetPassword.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(myIntent);
                    }
                    else
                    {
                        testing.setText("** "+res.getString("response")+" **");
                    }
                }
                break;
                case 4:// Reset Password
                {
                    JSONObject res = new JSONObject(result);
                    Boolean success = res.getBoolean("success");
                    if(success)
                    {
                        Intent myIntent = new Intent(mContext,LogIn.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(myIntent);
                    }
                    else
                    {
                        testing.setText("** "+res.getString("response")+" **");
                    }
                }
                break;
                case 5: //start session
                {
                    JSONObject res = new JSONObject(result);
                    Boolean success = res.getBoolean("success");
                    if(success)
                    {
                        Sessions.session_token = res.getString("session_token");
                        Intent myIntent = new Intent(mContext, InstructionsVisualAndAuditory.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(myIntent);
                        Sessions.new_session = false;
                    }
                    else
                    {
                        testing.setText("** "+res.getString("response")+" **");
                    }
                }
                case 6: //get leaderboard members
                {
                    JSONObject res = new JSONObject(result);
                    Boolean success = res.getBoolean("success");
                    JSONObject top_scorers = new JSONObject( res.getString("top_scorers"));
                    Log.d("API","top scorers :\t"+top_scorers);
                    if(success) // list found
                    {

                    }
                    else // list not found
                    {

                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}