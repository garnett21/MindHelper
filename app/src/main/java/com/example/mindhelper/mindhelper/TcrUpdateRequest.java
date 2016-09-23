package com.example.mindhelper.mindhelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/9/4.
 */
public class TcrUpdateRequest extends StringRequest {  private static final String Person_REQUEST_URL = "http://220.133.75.9/connection/tcrupdate.php";
    private Map<String, String> params;

    public TcrUpdateRequest(String ans1, String ans2,String ans3,String ans4,String ans5,String ans6,String ans7,String note,String pid,long id,  Response.Listener<String> listener) {
        super(Request.Method.POST, Person_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("t_situation", ans1);
        params.put("t_mood", ans2);
        params.put("t_thought", ans3);
        params.put("t_support", ans4);
        params.put("t_unsupport", ans5);
        params.put("t_balance", ans6);
        params.put("t_mood2", ans7);
        params.put("t_message", note);
        params.put("t_patient", pid);
        params.put("t_index", id+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

