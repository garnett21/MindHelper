package com.example.mindhelper.mindhelper;

/**
 * Created by user on 2016/8/23.
 */

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ItemUpdate extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://220.133.75.9/connection/cardupdate.php";
    private Map<String, String> params;

    public ItemUpdate(String contentText,String titleText,String doctor,long id, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("card_content", contentText);
        params.put("card_title", titleText);
        params.put("card_patient",doctor);
        params.put("card_index",id+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}