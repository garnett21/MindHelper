package com.example.mindhelper.mindhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class CBT extends AppCompatActivity {

    private TextView textView_home;
    private TextView textView_daily;
    private TextView textView_tcr;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbt);

        textView_home = (TextView)findViewById(R.id.textView_home3);
        textView_daily = (TextView)findViewById(R.id.textView_daily);
        textView_tcr= (TextView)findViewById(R.id.textView_tcr);
        textView_home.setOnClickListener(new View.OnClickListener() { //回首頁
            public void onClick(View v) {
                Intent intent = new Intent(CBT.this, User_main.class);
                startActivity(intent);
                CBT.this.finish();
            }
        });

        textView_daily.setOnClickListener(new View.OnClickListener() { //去每日活動排程
            public void onClick(View v) {
                Intent intent = new Intent(CBT.this, Daily_Activity.class);
                startActivity(intent);
                CBT.this.finish();
            }
        });
        textView_tcr.setOnClickListener(new View.OnClickListener() { //去每日活動排程
            public void onClick(View v) {
                Intent intent = new Intent(CBT.this, Tcr_Activity.class);
                startActivity(intent);
                CBT.this.finish();
            }
        });
        //以下日曆部分
        calendar=(CalendarView)findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
            }

        });
        //以上日曆部分




    }
}
