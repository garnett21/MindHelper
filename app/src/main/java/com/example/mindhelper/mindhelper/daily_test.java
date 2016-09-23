package com.example.mindhelper.mindhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2016/9/9.
 */
public class daily_test extends AppCompatActivity {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "); //HH:mm
    private EditText dateedit, activityedit,p_id;
    private DailyDAO dailyDAO;
    private Spinner spinnertime, spinnermood, spinnerhold;
    private String[] time_list = {"4:00", "5:00", "6:00", "7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","00:00","1:00","2:00","3:00"};
    private String[] mood_list = {"1", "2", "3", "4","5", "6", "7","8","9","10"};
    private String[] hold_list = {"1", "2", "3", "4","5", "6", "7","8","9","10"};
    private ArrayAdapter<String> timeList;
    private ArrayAdapter<String> moodList;
    private ArrayAdapter<String> holdList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__daily_);

        processViews();

        // 取得資料庫物件
        dailyDAO = new DailyDAO(this);
    }


    private void processViews() {

        spinnertime = (Spinner) findViewById(R.id.spinnertime);
        spinnertime.setOnItemSelectedListener(spnOnItemSelected);
        spinnermood = (Spinner) findViewById(R.id.spinnermood);
        spinnermood.setOnItemSelectedListener(spnOnItemSelected);
        spinnerhold = (Spinner) findViewById(R.id.spinnerhold);
        spinnerhold.setOnItemSelectedListener(spnOnItemSelected);
        dateedit = (EditText) findViewById(R.id.dateedit);
        Date date = new Date();
        dateedit.setText(dateFormat.format(date));
        setSpinnerView();
        activityedit = (EditText) findViewById(R.id.activityedit);
        p_id = (EditText) findViewById(R.id.p_id);

    }

    private void setSpinnerView() {
        timeList = new ArrayAdapter<String>(daily_test.this, android.R.layout.simple_spinner_item, time_list);
        spinnertime.setAdapter(timeList);
        moodList = new ArrayAdapter<String>(daily_test.this, android.R.layout.simple_spinner_item, mood_list);
        spinnermood.setAdapter(moodList);
       holdList = new ArrayAdapter<String>(daily_test.this, android.R.layout.simple_spinner_item, hold_list);
        spinnerhold.setAdapter(holdList);
    }

    private AdapterView.OnItemSelectedListener spnOnItemSelected
            = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };

    public void clickOk(View view) {

        String activity = activityedit.getText().toString();
        String time = spinnertime.getSelectedItem().toString();
        String date = dateedit.getText().toString();
        String mood = spinnermood.getSelectedItem().toString();
        String hold = spinnerhold.getSelectedItem().toString();
        String pid = p_id.getText().toString();
        Daily daily = new Daily();
        daily.setActivity(activity);
        daily.setTime(time);
        daily.setHold(hold);
        daily.setMood(mood);
        daily.setP_id(pid);
        daily.setDate(date);
        daily = dailyDAO.insert(daily);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(daily_test.this);
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

        dailytestRequest dailytestRequest = new dailytestRequest(activity,date,time,mood,hold,pid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(daily_test.this);
        queue.add(dailytestRequest);
        // 顯示新增成功

    }
    public void clickDateSearch(View view) {
        String dateValue = dateedit.getText().toString();
        Date date = new Date();
        try {
            // 轉換目前的日期為Date物件
            date = dateFormat.parse(dateValue);
        } catch (ParseException e) {  }

        // 讀取日期物件中的年、月、日
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        // 日期對話框設定監聽物件
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 設定畫面元件為設定的日期
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
               dateedit.setText(dateFormat.format(calendar.getTime()));
            } };


    }
    public void clickCancel(View view) {
        // 結束
        finish();
    }
}