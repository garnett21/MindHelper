package com.example.mindhelper.mindhelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/9/11.
 */
public class DailyDeleteRequest extends StringRequest {
    private static final String Person_REQUEST_URL = "http://220.133.75.9/connection/dailydelete.php";
    private Map<String, String> params;

    public DailyDeleteRequest(String date,String pid,String time, Response.Listener<String> listener) {
        super(Request.Method.POST, Person_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("date", date);
        params.put("pid", pid);
        params.put("time", time);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

