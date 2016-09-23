package com.example.mindhelper.mindhelper;

import android.app.Activity;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Insert_Tcr_Q12 extends AppCompatActivity {

    private EditText what_edit, when_edit;
    private Spinner who_spinner, where_spinner, emotion_spinner, percent_spinner;  //宣告 Spinner 物件
    private ArrayAdapter<String> adapter_who, adapter_where, adapter_emotion, adapter_percent;
    private TcrDAO tcrDAO;

    private String[] who_list = {"家人", "親戚", "朋友", "同學", "同事", "其他"};
    private String[] where_list = {"住家", "學校", "工作場所", "交通途中", "其他"};
    private String[] emotion_list = {"開心", "愉悅", "憤怒", "難過", "沮喪", "哀傷", "其他"};
    private String[] percent_list = {"100%", "90%", "80%", "70%", "60%", "50%", "40%", "30%", "20%", "10%", "0%"};

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "); //HH:mm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert__tcr__q12);

        processViews();

        // 取得資料庫物件
        tcrDAO = new TcrDAO(this);}


    private void processViews() {
        what_edit = (EditText) findViewById(R.id.what_edit);
        when_edit = (EditText) findViewById(R.id.when_edit);

        // 設定為今天的日期
        Date date = new Date();
        when_edit.setText(dateFormat.format(date));

        setSpinnerView();
    }


    private void setSpinnerView() {
        //將可選内容與ArrayAdapter連接起來
        adapter_who = new ArrayAdapter<String>(this, R.layout.spinnerstyle, who_list);
        //對應控件
        who_spinner = (Spinner) findViewById(R.id.spinner_who);
        //設置下拉列表的風格
        adapter_who.setDropDownViewResource(R.layout.spinnerstyle);
        //將adapter 添加到spinner中
        who_spinner.setAdapter(adapter_who);

        //將可選内容與ArrayAdapter連接起來
        adapter_where = new ArrayAdapter<String>(this, R.layout.spinnerstyle, where_list);
        //對應控件
        where_spinner = (Spinner) findViewById(R.id.spinner_where);
        //設置下拉列表的風格
        adapter_where.setDropDownViewResource(R.layout.spinnerstyle);
        //將adapter 添加到spinner中
        where_spinner.setAdapter(adapter_where);

        //將可選内容與ArrayAdapter連接起來
        adapter_emotion = new ArrayAdapter<String>(this, R.layout.spinnerstyle, emotion_list);
        //對應控件
        emotion_spinner = (Spinner) findViewById(R.id.spinner_emotion);
        //設置下拉列表的風格
        adapter_emotion.setDropDownViewResource(R.layout.spinnerstyle);
        //將adapter 添加到spinner中
        emotion_spinner.setAdapter(adapter_emotion);

        //將可選内容與ArrayAdapter連接起來
        adapter_percent = new ArrayAdapter<String>(this, R.layout.spinnerstyle, percent_list);
        //對應控件
        percent_spinner = (Spinner) findViewById(R.id.spinner_percent);
        //設置下拉列表的風格
        adapter_percent.setDropDownViewResource(R.layout.spinnerstyle);
        //將adapter 添加到spinner中
        percent_spinner.setAdapter(adapter_percent);

        who_spinner.setOnItemSelectedListener(spnOnItemSelected);
        where_spinner.setOnItemSelectedListener(spnOnItemSelected);
        emotion_spinner.setOnItemSelectedListener(spnOnItemSelected);
        percent_spinner.setOnItemSelectedListener(spnOnItemSelected);
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
        String who = who_spinner.getSelectedItem().toString();
        String where = where_spinner.getSelectedItem().toString();
        String when = when_edit.getText().toString();
        String what = what_edit.getText().toString();
        String emo = emotion_spinner.getSelectedItem().toString();
        String per = percent_spinner.getSelectedItem().toString();

        final Tcr tcr = new Tcr();
        // 把讀取的資料設定給物件
        tcr.setDatetime(System.currentTimeMillis());

        tcr.setAns1_when(when);
        tcr.setAns1_what(what);
        tcr.setAns1_who(who);
        tcr.setAns1_where(where);
        tcr.setAns2_emo(emo);
        tcr.setAns2_per(per);

        // 新增
       tcrDAO.insert(tcr);



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
                        intent2.setClass(Insert_Tcr_Q12.this, Insert_Tcr_Q345.class);
                        startActivity(intent2);
                        Insert_Tcr_Q12.this.finish();


    }






    public void clickCancel(View v) {
        // 結束
        finish();
    }

    public void clickSs(View view) {
        String dateValue = when_edit.getText().toString();
        Date date = new Date();
        try {
            // 轉換目前的日期為Date物件
            date = dateFormat.parse(dateValue);
        } catch (ParseException e) {
        }

        // 讀取日期物件中的年、月、日
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // int hour = calendar.get(Calendar.HOUR);
        // int min = calendar.get(Calendar.MINUTE);

        // 日期對話框設定監聽物件
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 設定畫面元件為設定的日期
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                when_edit.setText(dateFormat.format(calendar.getTime()));
            }
        };
        // 建立日期對話框物件
        DatePickerDialog dialog = new DatePickerDialog(this, listener, year, month, day);
        dialog.show(); // 顯示日期對話框

    }
}
