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
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Update_Tcr extends AppCompatActivity {

    private EditText id_update_edit, datetime_update_edit, note_update_edit;
    private EditText when_update_edit, what_update_edit, ans3_update_edit, ans4_update_edit,
            ans5_update_edit, ans6_update_edit, ans7_update_edit;
    private Spinner who_spinner, where_spinner, emotion_spinner, percent_spinner;  //宣告 Spinner 物件
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "); //HH:mm

    private String[] who_list = {"家人","親戚","朋友", "同學", "同事", "其他"};
    private String[] where_list = {"住家","學校","工作場所", "交通途中", "其他"};
    private String[] emotion_list = {"開心", "愉悅", "憤怒", "難過", "沮喪", "哀傷", "其他"};
    private String[] percent_list = {"100%", "90%", "80%", "70%", "60%", "50%", "40%", "30%", "20%", "10%", "0%"};

    private ArrayAdapter<String> adapter_who, adapter_where, adapter_emotion, adapter_percent;

    private Tcr tcr;

    // 資料庫物件
    private TcrDAO tcrDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tcr);

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

        id_update_edit = (EditText) findViewById(R.id.id_update_edit);
        datetime_update_edit = (EditText) findViewById(R.id.datetime_update_edit);
        note_update_edit = (EditText) findViewById(R.id.note_update_edit);

        when_update_edit = (EditText) findViewById(R.id.when_update_edit);
        what_update_edit = (EditText) findViewById(R.id.what_update_edit);
        ans3_update_edit = (EditText) findViewById(R.id.ans3_update_edit);
        ans4_update_edit = (EditText) findViewById(R.id.ans4_update_edit);
        ans5_update_edit = (EditText) findViewById(R.id.ans5_update_edit);
        ans6_update_edit = (EditText) findViewById(R.id.ans6_update_edit);
        ans7_update_edit = (EditText) findViewById(R.id.ans7_update_edit);

        setSpinnerView();

        // 設定畫面元件顯示的資料
        id_update_edit.setText(Long.toString(tcr.getId()));
        datetime_update_edit.setText(tcr.getDatetime());
        note_update_edit.setText(tcr.getNote());

        //who_spinner.setText(tcr.getAns1_who());
        when_update_edit.setText(tcr.getAns1_when());
        //where_spinner.setText(tcr.getAns1_where());
        what_update_edit.setText(tcr.getAns1_what());
        //emotion_spinner.setText(tcr.getAns2_emo());
       // percent_spinner.setText(tcr.getAns2_per());
        ans3_update_edit.setText(tcr.getAns3());
        ans4_update_edit.setText(tcr.getAns4());
        ans5_update_edit.setText(tcr.getAns5());
        ans6_update_edit.setText(tcr.getAns6());
        ans7_update_edit.setText(tcr.getAns7());

        // 設定為今天的日期
        Date date = new Date();
        when_update_edit.setText(dateFormat.format(date));
    }

    private void setSpinnerView() {
        //將可選内容與ArrayAdapter連接起來
        adapter_who = new ArrayAdapter<String>(this,R.layout.spinnerstyle,who_list);
        //對應控件
        who_spinner = (Spinner) findViewById(R.id.spinner_who_update);
        //設置下拉列表的風格
        adapter_who.setDropDownViewResource(R.layout.spinnerstyle);
        //將adapter 添加到spinner中
        who_spinner.setAdapter(adapter_who);

        //將可選内容與ArrayAdapter連接起來
        adapter_where = new ArrayAdapter<String>(this,R.layout.spinnerstyle,where_list);
        //對應控件
        where_spinner = (Spinner) findViewById(R.id.spinner_where_update);
        //設置下拉列表的風格
        adapter_where.setDropDownViewResource(R.layout.spinnerstyle);
        //將adapter 添加到spinner中
        where_spinner.setAdapter(adapter_where);

        //將可選内容與ArrayAdapter連接起來
        adapter_emotion = new ArrayAdapter<String>(this,R.layout.spinnerstyle,emotion_list);
        //對應控件
        emotion_spinner = (Spinner) findViewById(R.id.spinner_emotion_update);
        //設置下拉列表的風格
        adapter_emotion.setDropDownViewResource(R.layout.spinnerstyle);
        //將adapter 添加到spinner中
        emotion_spinner.setAdapter(adapter_emotion);

        //將可選内容與ArrayAdapter連接起來
        adapter_percent = new ArrayAdapter<String>(this,R.layout.spinnerstyle,percent_list);
        //對應控件
        percent_spinner = (Spinner) findViewById(R.id.spinner_percent_update);
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
            = new AdapterView.OnItemSelectedListener()
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0)
        {
            // TODO Auto-generated method stub
        }
    };

    public void clickOk(View view) {
        // 讀取使用者輸入的資料
        String datetimeValue = datetime_update_edit.getText().toString();
        String noteValue = note_update_edit.getText().toString();

        String whoValue = who_spinner.getSelectedItem().toString();
        String whenValue = when_update_edit.getText().toString();
        String whereValue = where_spinner.getSelectedItem().toString();
        String whatValue = what_update_edit.getText().toString();
        String emoValue = emotion_spinner.getSelectedItem().toString();
        String perValue = percent_spinner.getSelectedItem().toString();
        String ans3Value = ans3_update_edit.getText().toString();
        String ans4Value = ans4_update_edit.getText().toString();
        String ans5Value = ans5_update_edit.getText().toString();
        String ans6Value = ans6_update_edit.getText().toString();
        String ans7Value = ans7_update_edit.getText().toString();

        // 把讀取的資料設定給物件
        tcr.setDatetime(datetimeValue);
        tcr.setNote(noteValue);

        tcr.setAns1_who(whoValue);
        tcr.setAns1_when(whenValue);
        tcr.setAns1_where(whereValue);
        tcr.setAns1_what(whatValue);
        tcr.setAns2_emo(emoValue);
        tcr.setAns2_per(perValue);
        tcr.setAns3(ans3Value);
        tcr.setAns4(ans4Value);
        tcr.setAns5(ans5Value);
        tcr.setAns6(ans6Value);
        tcr.setAns7(ans7Value);

        // 修改
        tcrDAO.update(tcr);

        // 顯示修改成功
        Toast.makeText(this, "Update success!", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        // 設定回傳結果
        setResult(Activity.RESULT_OK, intent);
        // 結束
        finish();
    }

    public void clickCancel(View view) {
        // 結束
        finish();
    }

    public void clickDateSearch(View view) {
        String dateValue = when_update_edit.getText().toString();
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
                when_update_edit.setText(dateFormat.format(calendar.getTime()));
            } };
        // 建立日期對話框物件
        DatePickerDialog dialog = new DatePickerDialog(this, listener, year, month, day);
        dialog.show(); // 顯示日期對話框

        //時間對話框設定監聽物件
       /* TimePickerDialog.OnTimeSetListener listener2 = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 設定畫面元件為設定的時間
                Calendar calendar = Calendar.getInstance();
                calendar.set(hourOfDay, minute);
                when_edit.setText(dateFormat.format(calendar.getTime()));
            } };
        // 建立時間對話框物件
        TimePickerDialog dialog2 = new TimePickerDialog(this, listener2, hour, min, false);
        dialog2.show(); // 顯示時間對話框 */

    }
}
