package com.example.mindhelper.mindhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class Update_DailyActivity extends AppCompatActivity {

    private TextView id_update_edit;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "); //HH:mm
    private TextView dateedit, p_id;
    private EditText activityedit;
    private TextView textViewmood,textViewhold,textViewtime;
    private Spinner spinnermood, spinnerhold;
    private String[] time_list = {"4:00", "5:00", "6:00", "7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","00:00","1:00","2:00","3:00"};
    private String[] mood_list = {"1", "2", "3", "4","5", "6", "7","8","9","10"};
    private String[] hold_list = {"1", "2", "3", "4","5", "6", "7","8","9","10"};
    private ArrayAdapter<String> timeList;
    private ArrayAdapter<String> moodList;
    private ArrayAdapter<String> holdList;

    private Daily daily;

    // 資料庫物件
    private DailyDAO dailyDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__daily);

        // 取得資料庫物件
        dailyDAO = new DailyDAO(this);

        Intent intent = getIntent();
        // 讀取修改資料的編號
        long id = intent.getLongExtra("id", -1);
        // 取得指定編號的物件
        daily = dailyDAO.get(id);

        processViews();
    }

    private void processViews() {
        id_update_edit = (TextView) findViewById(R.id.id_update_edit);
        textViewhold = (TextView) findViewById(R.id.textViewhold);
        textViewmood = (TextView) findViewById(R.id.textViewmood);
        textViewtime = (TextView) findViewById(R.id.textViewtime);
        // 設定畫面元件顯示的資料
        id_update_edit.setText(Long.toString(daily.getId()));
        spinnermood = (Spinner) findViewById(R.id.spinnermood);
        spinnermood.setOnItemSelectedListener(spnOnItemSelected);
        spinnerhold = (Spinner) findViewById(R.id.spinnerhold);
        spinnerhold.setOnItemSelectedListener(spnOnItemSelected);
        dateedit = (TextView) findViewById(R.id.dateedit);
        dateedit.setText(daily.getDate());
        setSpinnerView();
        activityedit = (EditText) findViewById(R.id.activityedit);
        p_id = (TextView) findViewById(R.id.p_id);
       activityedit.setText(daily.getActivity());
        p_id.setText(daily.getP_id());
        textViewhold.setText(daily.getHold());
        textViewtime.setText(daily.getTime());
        textViewmood.setText(daily.getMood());

    }
    private void setSpinnerView() {
        moodList = new ArrayAdapter<String>(Update_DailyActivity.this, android.R.layout.simple_spinner_item, mood_list);
        spinnermood.setAdapter(moodList);
        holdList = new ArrayAdapter<String>(Update_DailyActivity.this, android.R.layout.simple_spinner_item, hold_list);
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
        // 讀取使用者輸入的資料
        /* double latitudeValue = Double.parseDouble(latitude_update_edit.getText().toString());
        double longitudeValue = Double.parseDouble(longitude_update_edit.getText().toString());
        double accuracyValue = Double.parseDouble(accuracy_update_edit.getText().toString()); */
        long idValue = Long.parseLong(id_update_edit.getText().toString());
        String activity = activityedit.getText().toString();
        String time = textViewtime.getText().toString();
        String date = dateedit.getText().toString();
        String mood = spinnermood.getSelectedItem().toString();
        String hold = spinnerhold.getSelectedItem().toString();
        String pid = p_id.getText().toString();
        Daily daily = new Daily();
        daily.setId(idValue);
        daily.setActivity(activity);
        daily.setTime(time);
        daily.setHold(hold);
        daily.setMood(mood);
        daily.setP_id(pid);
        daily.setDate(date);

        // 把讀取的資料設定給物件
        /* daily.setLatitude(latitudeValue);
        daily.setLongitude(longitudeValue);
        daily.setAccuracy(accuracyValue); */





        // 修改
        dailyDAO.update(daily);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(Update_DailyActivity.this);
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

        DailyUpdateRequest dailyUpdateRequest = new DailyUpdateRequest(activity,date,time,mood,hold,pid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Update_DailyActivity.this);
        queue.add(dailyUpdateRequest);
        // 顯示新增成功


        // 顯示修改成功
        Toast.makeText(this, "Update success!", Toast.LENGTH_SHORT).show();


    }


    public void clickCancel(View view) {
        // 結束
        finish();
    }


}
