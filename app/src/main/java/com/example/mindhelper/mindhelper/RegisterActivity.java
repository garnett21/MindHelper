package com.example.mindhelper.mindhelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    private ArrayAdapter<String> arrayList;
    private Context mContext;
    private String[] gender= {"男", "女"};
    private String[] stringdata= {"male", "female"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this.getApplicationContext();
        spinner = (Spinner)findViewById(R.id.spinner);
        arrayList = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, stringdata);
        spinner.setAdapter(arrayList);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final EditText etdoctor = (EditText) findViewById(R.id.etdoctor);

        final  EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final  EditText etAddress = (EditText) findViewById(R.id.etAddress);
               bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String age = etAge.getText().toString();
                final String password = etPassword.getText().toString();
                final String doctor =etdoctor.getText().toString();
                final String sex = spinner.getSelectedItem().toString();
                final String email = etEmail.getText().toString();
                final String address = etAddress.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, Login.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, password,  age, doctor,sex,email,address, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}