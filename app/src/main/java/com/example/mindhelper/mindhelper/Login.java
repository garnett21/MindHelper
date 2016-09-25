package com.example.mindhelper.mindhelper;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2016/8/1.
 */
public class Login extends AppCompatActivity {
    private Button button;
    private TextView textView5;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                final EditText etUsername = (EditText) findViewById(R.id.etUsername);
                final EditText etPassword = (EditText) findViewById(R.id.etPassword);
                final TextView tvRegisterLink = (TextView) findViewById(R.id.textView5);
                final Button bLogin = (Button) findViewById(R.id.button);

                tvRegisterLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(Login.this, RegisterActivity.class);
                        Login.this.startActivity(registerIntent);
                    }
                });

                bLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String username = etUsername.getText().toString();
                        final String password = etPassword.getText().toString();

                        // Response received from the server
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        String name = jsonResponse.getString("p_name");
                                        String age =jsonResponse.getString("p_auth");
                                        int id =jsonResponse.getInt("p_index");
                                        String doctor =jsonResponse.getString("p_doctor");
                                        String sex = jsonResponse.getString("p_gender");
                                        String email = jsonResponse.getString("p_email");
                                        String address = jsonResponse.getString("p_address");
                                        Intent intent = new Intent(Login.this, drawer.class);
                                        intent.putExtra("p_name", name);
                                        intent.putExtra("p_password", password);
                                        intent.putExtra("p_account", username);
                                        intent.putExtra("p_index", id);
                                        intent.putExtra("p_gender", sex);
                                        intent.putExtra("p_email", email);
                                        intent.putExtra("p_address", address);
                                        Login.this.startActivity(intent);
                                    } else if ( etUsername.getText().toString().matches("") || etPassword.getText().toString().matches("")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                            builder.setMessage("請輸入帳號或密碼")
                                                    .setNegativeButton("Retry", null)
                                                    .create()
                                                    .show();
                                        }
                                    else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                            builder.setMessage("Login Failed")
                                                    .setNegativeButton("Retry", null)
                                                    .create()
                                                    .show();
                                        }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(Login.this);
                        queue.add(loginRequest);
                    }


        });

    }

}
