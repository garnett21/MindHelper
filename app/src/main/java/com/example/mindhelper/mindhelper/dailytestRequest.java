package com.example.mindhelper.mindhelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/9/9.
 */
public class dailytestRequest extends StringRequest {  private static final String Person_REQUEST_URL = "http://220.133.75.9/connection/daily.php";
    private Map<String, String> params;

    public dailytestRequest(String activity,String date,String time,String mood,String hold,String pid,Response.Listener<String> listener) {
        super(Request.Method.POST, Person_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("activity", activity);
        params.put("date", date);
        params.put("time", time);
        params.put("mood", mood);
        params.put("hold", hold);
        params.put("pid", pid);


    }
        public Map<String, String> getParams() {
            return params;
        }


}
