package com.example.mindhelper.mindhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Insert_Tcr extends AppCompatActivity {

    private EditText note_edit;
    private EditText ans1_edit, ans2_edit, ans3_edit, ans4_edit, ans5_edit, ans6_edit, ans7_edit,p_id;
    private TcrDAO tcrDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tcr);



        processViews();

        // 取得資料庫物件
        tcrDAO = new TcrDAO(this);
    }

    private void processViews(){

        note_edit = (EditText) findViewById(R.id.note_edit);
        ans1_edit = (EditText) findViewById(R.id.ans1_edit);
        ans2_edit = (EditText) findViewById(R.id.ans2_edit);
        ans3_edit = (EditText) findViewById(R.id.ans3_edit);
        ans4_edit = (EditText) findViewById(R.id.ans4_edit);
        ans5_edit = (EditText) findViewById(R.id.ans5_edit);
        ans6_edit = (EditText) findViewById(R.id.ans6_edit);
        ans7_edit = (EditText) findViewById(R.id.ans7_edit);
        p_id = (EditText) findViewById(R.id.p_id);
    }

    public void clickOk(View view){
        // 讀取使用者輸入的資料
        String note = note_edit.getText().toString();
        String ans1 = ans1_edit.getText().toString();
        String ans2 = ans2_edit.getText().toString();
        String ans3 = ans3_edit.getText().toString();
        String ans4 = ans4_edit.getText().toString();
        String ans5 = ans5_edit.getText().toString();
        String ans6 = ans6_edit.getText().toString();
        String ans7 = ans7_edit.getText().toString();
       String pid = p_id.getText().toString();
        // 建立準備新增資料的物件
        Tcr tcr = new Tcr();

        // 把讀取的資料設定給物件
        tcr.setDatetime(System.currentTimeMillis());
        tcr.setNote(note);
        tcr.setPid(pid);
        tcr.setAns1(ans1);
        tcr.setAns2(ans2);
        tcr.setAns3(ans3);
        tcr.setAns4(ans4);
        tcr.setAns5(ans5);
        tcr.setAns6(ans6);
        tcr.setAns7(ans7);

        // 新增
        tcr = tcrDAO.insert(tcr);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(Insert_Tcr.this);
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

        TcrRequest tcrRequest = new TcrRequest(ans1, ans2,ans3,ans4,ans5,ans6,ans7,note,pid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Insert_Tcr.this);
        queue.add(tcrRequest);
        // 顯示新增成功

    }

    public void clickCancel(View view) {
        // 結束
        finish();
    }

}
