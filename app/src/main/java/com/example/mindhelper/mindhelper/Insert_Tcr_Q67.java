package com.example.mindhelper.mindhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Insert_Tcr_Q67 extends AppCompatActivity {

    private Button ok_button, cancle_button, back_button;
    private EditText ans6_edit, ans7_edit, note_edit;
    private TcrDAO tcrDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert__tcr__q67);
        processViews();

        // 取得資料庫物件
        tcrDAO = new TcrDAO(this);

        // 讀取修改資料的編號


        // 取得指定編號的物件

        ok_button.setOnClickListener(new View.OnClickListener() { //前往問題3~5
            @Override
            public void onClick(View v) { //讀取輸入資料+換頁面
                String ans6 = ans6_edit.getText().toString();
                String ans7 = ans7_edit.getText().toString();
                String note = note_edit.getText().toString();
                Intent intent = getIntent();
                long id = intent.getLongExtra("id", -1);
                String ans1_who = intent.getStringExtra("ans1_who");
                String ans1_where = intent.getStringExtra("ans1_where");
                String ans1_what = intent.getStringExtra("ans1_what");
                String ans1_when = intent.getStringExtra("ans1_when");
                String ans2_emo = intent.getStringExtra("ans2_emo");
                String ans2_per = intent.getStringExtra("ans2_per");
                String ans3 = intent.getStringExtra("ans3");
                String ans4 = intent.getStringExtra("ans4");
                String ans5 = intent.getStringExtra("ans5");
                // 建立準備新增資料的物件
                Tcr tcr = new Tcr();
             /*   String ans1_what = tcr.getAns1_what();
                String ans1_who = tcr.getAns1_who();
                String ans1_where = tcr.getAns1_where();
                String ans1_when = tcr.getAns1_when();
                String ans2_emo = tcr.getAns2_emo();
                String ans2_per= tcr.getAns2_per();
                String ans3= tcr.getAns3();
                String ans4= tcr.getAns4();
                String ans5= tcr.getAns5();*/
                // 把讀取的資料設定給物件
                tcr.setAns6(ans6);
                tcr.setAns7(ans7);
                tcr.setNote(note);
                tcr.setId(id);
                tcr.setAns3(ans3);
                tcr.setAns4(ans4);
                tcr.setAns5(ans5);
                tcr.setId(id);
                tcr.setAns1_what(ans1_what);
                tcr.setAns1_when(ans1_when);
                tcr.setAns1_where(ans1_where);
                tcr.setAns1_who(ans1_who);
                tcr.setAns2_emo(ans2_emo);
                tcr.setAns2_per(ans2_per);
                // 新增
                 tcrDAO.update(tcr);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                                Intent intent = getIntent();
                                // 設定回傳結果
                                setResult(Activity.RESULT_OK, intent);
                                // 結束
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Insert_Tcr_Q67.this);
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

                TcrRequestQ67 tcrRequestQ67 = new TcrRequestQ67(ans1_when, ans1_who,ans1_where,ans1_what,ans2_emo,ans2_per,ans3,ans4,ans5,ans6,ans7,note, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Insert_Tcr_Q67.this);
                queue.add(tcrRequestQ67);

            } });

        cancle_button.setOnClickListener(new View.OnClickListener() { //回list列表
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Insert_Tcr_Q67.this, Tcr_Activity.class);
                startActivity(intent);
                Insert_Tcr_Q67.this.finish();
            } });

        back_button.setOnClickListener(new View.OnClickListener() { //回list列表
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Insert_Tcr_Q67.this, Insert_Tcr_Q345.class);
                startActivity(intent);
                Insert_Tcr_Q67.this.finish();
            } });
    }

    private void processViews(){
        ok_button = (Button) findViewById(R.id.ok_button);
        cancle_button = (Button) findViewById(R.id.cancle_button);
        back_button = (Button)findViewById(R.id.back_button);

        ans6_edit = (EditText) findViewById(R.id.ans6_edit);
        ans7_edit = (EditText) findViewById(R.id.ans7_edit);
        note_edit = (EditText) findViewById(R.id.note_edit);
    }

}
