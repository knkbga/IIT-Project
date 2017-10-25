package com.example.om.mygame;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 23/9/17.
 */

public class VersionData {
    public static JSONObject version_data ;

    static
    {
        String version = "0.0.1";
        String date_of_issue = "23-09-2017";
        String new_features[] = { "API-call-after-every-game" , "Leader-board" };
        Boolean beta = true;
        int release_number =1;

        try {
            version_data = new JSONObject();

            version_data.put( "version" , version);
            version_data.put( "date_of_issue" , date_of_issue);
            version_data.put( "new_features" , new_features);
            version_data.put( "beta" , beta);
            version_data.put( "release_number" , release_number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void update(String query,String value) throws JSONException
    {
        version_data.getJSONObject(query).put(query,value);
    }
    public <T extends Object> T getValue(String query) throws JSONException {
        return (T)version_data.get(query);
    }
}
