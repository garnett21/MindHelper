package com.example.mindhelper.mindhelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Belal on 8/6/2015.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://220.133.75.9/connection/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String password, String age,String doctor,String sex,String email,String address, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("p_name", name);
        params.put("p_account", username);
        params.put("p_password", password);
        params.put("p_auth", age);
        params.put("p_doctor", doctor);
        params.put("p_gender", sex);
        params.put("p_email", email);
        params.put("p_address", address);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
