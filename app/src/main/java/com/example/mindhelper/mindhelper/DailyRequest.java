package com.example.mindhelper.mindhelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/8/27.
 */
public class DailyRequest extends StringRequest {  private static final String Person_REQUEST_URL = "http://220.133.75.9/connection/daily.php";
    private Map<String, String> params;

    public DailyRequest(String note,String a08, String a09, String a10,String a11,String a12,String a13,String a14,String a15,String a16,String a17,String a18,String a19,String a20,String a21,String m08,String m09,String m10,String m11,String m12,String m13,String m14,String m15,String m16,String m17,String m18,String m19,String m20,String m21,String p08,String p09,String p10,String p11,String p12,String p13,String p14,String p15,String p16,String p17,String p18,String p19,String p20,String p21,  Response.Listener<String> listener) {
        super(Request.Method.POST, Person_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("note", note);
        params.put("a08", a08);
        params.put("a09", a09);
        params.put("a10", a10);
        params.put("a11", a11);
        params.put("a12", a12);
        params.put("a13", a13);
        params.put("a14", a14);
        params.put("a15", a15);
        params.put("a16", a16);
        params.put("a17", a17);
        params.put("a18", a18);
        params.put("a19", a19);
        params.put("a20", a20);
        params.put("a21", a21);
        params.put("m08", m08);
        params.put("m09", m09);
        params.put("m10", m10);
        params.put("m11", m11);
        params.put("m12", m12);
        params.put("m13", m13);
        params.put("m14", m14);
        params.put("m15", m15);
        params.put("m16", m16);
        params.put("m17", m17);
        params.put("m18", m18);
        params.put("m19", m19);
        params.put("m20", m20);
        params.put("m21", m21);
        params.put("p08", p08);
        params.put("p09", p09);
        params.put("p10", p10);
        params.put("p11", p11);
        params.put("p12", p12);
        params.put("p13", p13);
        params.put("p14", p14);
        params.put("p15", p15);
        params.put("p16", p16);
        params.put("p17", p17);
        params.put("p18", p18);
        params.put("p19", p19);
        params.put("p20", p20);
        params.put("p21", p21);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

