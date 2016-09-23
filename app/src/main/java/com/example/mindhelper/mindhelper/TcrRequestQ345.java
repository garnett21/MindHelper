package com.example.mindhelper.mindhelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/9/13.
 */
public class TcrRequestQ345 extends StringRequest {  private static final String Person_REQUEST_URL = "http://220.133.75.9/connection/tcrq345.php";
    private Map<String, String> params;

    public TcrRequestQ345(String ans1_when,String ans1_who,String ans1_what,String ans1_where,String ans2_emo,String ans2_per,String ans3,String ans4,String ans5,String ans6,String ans7,String note,String pid,  Response.Listener<String> listener) {
        super(Request.Method.POST, Person_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("t_when", ans1_when);
        params.put("t_who", ans1_who);
        params.put("t_where", ans1_where);
        params.put("t_what", ans1_what);
        params.put("t_emotion", ans2_emo);
        params.put("t_percent", ans2_per);
        params.put("t_thought", ans3);
        params.put("t_support", ans4);
        params.put("t_unsupport", ans5);
        params.put("t_balance", ans6);
        params.put("t_mood2", ans7);
        params.put("t_message", note);
        params.put("t_patient", pid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
