package com.example.mindhelper.mindhelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    private ArrayAdapter<String> arrayList;
    private Context mContext;
    private String[] gender = {"男", "女"};
    private String[] stringdata = {"male", "female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this.getApplicationContext();
        spinner = (Spinner) findViewById(R.id.spinner);
        arrayList = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, stringdata);
        spinner.setAdapter(arrayList);


        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);


        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAddress = (EditText) findViewById(R.id.etAddress);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();

                final String password = etPassword.getText().toString();

                final String sex = spinner.getSelectedItem().toString();
                final String email = etEmail.getText().toString();
                final String address = etAddress.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                             if(etUsername.getText().toString().matches("")||etAddress.getText().toString().matches("")||etEmail.getText().toString().matches("")||etName.getText().toString().matches("")||etPassword.getText().toString().matches("")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("欄位不得為空!")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
else if(isEmail(email)==false){
                                 AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                 builder.setMessage("郵件格式錯誤")
                                         .setNegativeButton("Retry", null)
                                         .create()
                                         .show();



                             }
                            else if (success) {


                                AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(RegisterActivity.this)
                                        .setMessage("註冊成功");
                                DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(RegisterActivity.this, Login.class);
                                        RegisterActivity.this.startActivity(intent);
                                    }
                                };
                                ;
                                MyAlertDialog.setPositiveButton("OK", OkClick);
                                MyAlertDialog.show();
                            }
else {
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

                RegisterRequest registerRequest = new RegisterRequest(name, username, password, sex, email, address, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


    }
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
}