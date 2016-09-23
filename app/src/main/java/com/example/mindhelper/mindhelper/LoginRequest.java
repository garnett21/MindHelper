package com.example.mindhelper.mindhelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/8/6.
 */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://220.133.75.9/connection/Loginapp.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("p_account", username);
        params.put("p_password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
