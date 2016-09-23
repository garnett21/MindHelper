package com.example.mindhelper.mindhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Insert_Tcr_Q345 extends AppCompatActivity {

    private Button ok_button, cancle_button, back_button;
    private EditText ans3_edit, ans4_edit, ans5_edit;
    private TcrDAO tcrDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert__tcr__q345);
        Intent intent = getIntent();
        final long id = intent.getLongExtra("id", -1);
       final String ans1_who = intent.getStringExtra("ans1_who");
       final String ans1_where = intent.getStringExtra("ans1_where");
       final String ans1_what = intent.getStringExtra("ans1_what");
       final String ans1_when = intent.getStringExtra("ans1_when");
        final String ans2_emo = intent.getStringExtra("ans2_emo");
        final String ans2_per = intent.getStringExtra("ans2_per");
        processViews();

        // 取得資料庫物件
        tcrDAO = new TcrDAO(this);

        // 取得指定編號的物件

        ok_button.setOnClickListener(new View.OnClickListener() { //前往問題3~5
            @Override
            public void onClick(View v) { //讀取輸入資料+換頁面
                String ans3 = ans3_edit.getText().toString();
                String ans4 = ans4_edit.getText().toString();
                String ans5 = ans5_edit.getText().toString();

                // 讀取修改資料的編號

                // 建立準備新增資料的物件
                final Tcr tcr = new Tcr();
                        // 把讀取的資料設定給物件
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



                                Intent intent = getIntent();
                                // 設定回傳結果
                                setResult(Activity.RESULT_OK, intent);

                                Intent intent2 = new Intent();
                                intent2.putExtra("id", tcr.getId());
                                intent2.putExtra("ans2_emo", tcr.getAns2_emo());
                                intent2.putExtra("ans2_per", tcr.getAns2_per());
                                intent2.putExtra("ans1_who", tcr.getAns1_who());
                                intent2.putExtra("ans1_when", tcr.getAns1_when());
                                intent2.putExtra("ans1_what", tcr.getAns1_what());
                                intent2.putExtra("ans1_where", tcr.getAns1_where());
                                intent2.putExtra("ans3", tcr.getAns3());
                                intent2.putExtra("ans4", tcr.getAns4());
                                intent2.putExtra("ans5", tcr.getAns5());
                                intent2.setClass(Insert_Tcr_Q345.this, Insert_Tcr_Q67.class);
                                startActivity(intent2);
                                Insert_Tcr_Q345.this.finish();


            } });

        cancle_button.setOnClickListener(new View.OnClickListener() { //回list列表
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Insert_Tcr_Q345.this, Tcr_Activity.class);
                startActivity(intent);
                Insert_Tcr_Q345.this.finish();
            } });

        back_button.setOnClickListener(new View.OnClickListener() { //回list列表
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Insert_Tcr_Q345.this, Insert_Tcr_Q12.class);
                startActivity(intent);
                Insert_Tcr_Q345.this.finish();
            } });
    }

    private void processViews(){
        ok_button = (Button) findViewById(R.id.ok_button);
        cancle_button = (Button) findViewById(R.id.cancle_button);
        back_button = (Button)findViewById(R.id.back_button);

        ans3_edit = (EditText) findViewById(R.id.ans3_edit);
        ans4_edit = (EditText) findViewById(R.id.ans4_edit);
        ans5_edit = (EditText) findViewById(R.id.ans5_edit);
    }

}
