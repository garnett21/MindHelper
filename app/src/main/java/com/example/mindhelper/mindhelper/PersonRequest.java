package com.example.mindhelper.mindhelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/8/11.
 */
public class PersonRequest extends StringRequest {
    private static final String Person_REQUEST_URL = "http://220.133.75.9/connection/update.php";
    private Map<String, String> params;

    public PersonRequest(String name, String username, String password,int id,String doctor,String sex,String email,String address,   Response.Listener<String> listener) {
        super(Method.POST, Person_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("p_name", name);
        params.put("p_account", username);
        params.put("p_password", password);

        params.put("p_doctor", doctor );
        params.put("p_gender", sex);
        params.put("p_email", email);
        params.put("p_index", id+"");
        params.put("p_address", address);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
