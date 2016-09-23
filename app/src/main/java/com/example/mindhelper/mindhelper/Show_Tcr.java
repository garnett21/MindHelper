package com.example.mindhelper.mindhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Show_Tcr extends AppCompatActivity {

    private TextView show_id, show_datetime, show_note;
    private TextView show_who, show_when, show_where, show_what, show_emo, show_per,show_ans3,show_ans4,show_ans5,show_ans6,show_ans7 ;

    private Tcr tcr;

    // 資料庫物件
    private TcrDAO tcrDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__tcr);

        // 取得資料庫物件
        tcrDAO = new TcrDAO(this);

        Intent intent = getIntent();
        // 讀取修改資料的編號
        long id = intent.getLongExtra("id", -1);
        // 取得指定編號的物件
        tcr = tcrDAO.get(id);

        processViews();
    }

    private void processViews() {

        show_id = (TextView) findViewById(R.id.show_id);
        show_datetime = (TextView) findViewById(R.id.show_datetime);
        show_note = (TextView) findViewById(R.id.show_note);

        show_who = (TextView) findViewById(R.id.show_who);
        show_when = (TextView) findViewById(R.id.show_when);
        show_where = (TextView) findViewById(R.id.show_where);
        show_what = (TextView) findViewById(R.id.show_what);
        show_emo = (TextView) findViewById(R.id.show_emotion);
        show_per = (TextView) findViewById(R.id.show_percent);
        show_ans3 = (TextView) findViewById(R.id.show_ans3);
        show_ans4 = (TextView) findViewById(R.id.show_ans4);
        show_ans5 = (TextView) findViewById(R.id.show_ans5);
        show_ans6 = (TextView) findViewById(R.id.show_ans6);
        show_ans7 = (TextView) findViewById(R.id.show_ans7);

        // 設定畫面元件顯示的資料
        show_id.setText(Long.toString(tcr.getId()));
        show_datetime.setText(tcr.getDatetime());
        show_note.setText(tcr.getNote());

        show_who.setText(tcr.getAns1_who());
        show_when.setText(tcr.getAns1_when());
        show_where.setText(tcr.getAns1_where());
        show_what.setText(tcr.getAns1_what());
        show_emo.setText(tcr.getAns2_emo());
        show_per.setText(tcr.getAns2_per());
        show_ans3.setText(tcr.getAns3());
        show_ans4.setText(tcr.getAns4());
        show_ans5.setText(tcr.getAns5());
        show_ans6.setText(tcr.getAns6());
        show_ans7.setText(tcr.getAns7());

    }

}
