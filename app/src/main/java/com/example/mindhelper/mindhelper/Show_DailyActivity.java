package com.example.mindhelper.mindhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class Show_DailyActivity extends AppCompatActivity {
    private TextView id_update_edit;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "); //HH:mm
    private TextView dateedit, activityedit,p_id;
    private TextView textViewtime,textViewhold,textViewmood;

    private Daily daily;

    // 資料庫物件
    private DailyDAO dailyDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_daily);

        // 取得資料庫物件
        dailyDAO = new DailyDAO(this);

        Intent intent = getIntent();
        // 讀取修改資料的編號
        long id = intent.getLongExtra("id", -1); ///
        // 取得指定編號的物件
        daily = dailyDAO.get(id);

        processViews();


    }

    private void processViews() {
        id_update_edit = (TextView) findViewById(R.id.id_update_edit);
        textViewtime = (TextView)findViewById(R.id.textViewtime);
        textViewmood = (TextView)findViewById(R.id.textViewmood);
        textViewhold = (TextView)findViewById(R.id.textViewhold);
        // 設定畫面元件顯示的資料
        id_update_edit.setText(Long.toString(daily.getId()));

        dateedit = (TextView) findViewById(R.id.dateedit);

        dateedit.setText(daily.getDate());

        activityedit = (TextView) findViewById(R.id.activityedit);
        p_id = (TextView) findViewById(R.id.p_id);
       activityedit.setText(daily.getActivity());
        p_id.setText(daily.getP_id());
        textViewtime.setText(daily.getTime());
        textViewhold.setText(daily.getHold());
       textViewmood.setText(daily.getMood());

    }
    public void clickCancel(View view) {
        // 結束
        finish();
    }



}
