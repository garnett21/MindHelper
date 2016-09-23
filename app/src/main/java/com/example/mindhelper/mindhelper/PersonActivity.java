package com.example.mindhelper.mindhelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PersonActivity extends User_main {

    private TextView textView_home;
    private Button button_confirm;
    private Spinner spinner;
    private ArrayAdapter<String> arrayList;
    private String[] stringdata= {"male", "female"};
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        mContext = this.getApplicationContext();
        spinner = (Spinner)findViewById(R.id.spinner2);
        arrayList = new ArrayAdapter<String>(PersonActivity.this,
                android.R.layout.simple_spinner_item, stringdata);
        spinner.setAdapter(arrayList);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etid = (EditText) findViewById(R.id.id);
        final EditText etDoctor = (EditText) findViewById(R.id.etdoctor);
        final Button button_update = (Button) findViewById(R.id.button_update);
        //final EditText etSex = (EditText) findViewById(R.id.etSex);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAddress = (EditText) findViewById(R.id.etAddress);
       // textView_home = (TextView)findViewById(R.id.textView_home2);
       // button_confirm = (Button) findViewById(R.id.button_confirm);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("p_name");
        final String username = intent.getStringExtra("p_account");
        final String password = intent.getStringExtra("p_password");
        final String age = intent.getStringExtra("p_auth");
        final int id = intent.getIntExtra("p_index", -1);
        final String doctor = intent.getStringExtra("p_doctor");
        final String sex = intent.getStringExtra("p_gender");
        final String email = intent.getStringExtra("p_email");
        final String address = intent.getStringExtra("p_address");
        //實做OnClickListener界面
 /*      textView_home.setOnClickListener(new View.OnClickListener() {  //回主畫面

            public void onClick(View v) {
                //初始化Intent物件
                Intent intent = new Intent(PersonActivity.this, User_main.class);
                //從MainActivity 到Main2Activity
                //開啟Activity
                startActivity(intent);
                PersonActivity.this.finish();
            }
        });

       button_confirm.setOnClickListener(new View.OnClickListener() {  //回主畫面

            public void onClick(View v) {

                Intent intent = new Intent(PersonActivity.this, User_main.class);
                intent.putExtra("p_name", name);
                intent.putExtra("p_password", password);
                intent.putExtra("p_account", username);
                intent.putExtra("p_auth", age);
                intent.putExtra("p_index", id);
                intent.putExtra("p_doctor", doctor);
                intent.putExtra("p_gender", sex);
                intent.putExtra("p_email", email);
                intent.putExtra("p_address", address);
                startActivity(intent);
                PersonActivity.this.finish();
            }

        });*/

        etUsername.setText(username);
        etPassword.setText(password);
        etName.setText(name);
//        etAge.setText(String.valueOf(age));
        etid.setText(String.valueOf(id));
        etDoctor.setText(String.valueOf(doctor));
        //etSex.setText(sex);
        etEmail.setText(email);
        etAddress.setText(address);
        button_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String age = etAge.getText().toString();
                final int id = Integer.parseInt(etid.getText().toString());
                 final String doctor = etDoctor.getText().toString();
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
                                Intent intent = new Intent(PersonActivity.this, Login.class);
                                PersonActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PersonActivity.this);
                                builder.setMessage("update Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                PersonRequest personRequest = new PersonRequest(name, username, password, age, id,doctor,sex,email,address, responseListener);
                RequestQueue queue = Volley.newRequestQueue(PersonActivity.this);
                queue.add(personRequest);
            }
        });

}
}