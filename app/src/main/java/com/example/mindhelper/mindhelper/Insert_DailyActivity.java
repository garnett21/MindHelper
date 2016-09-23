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

public class Insert_DailyActivity extends AppCompatActivity {

    private EditText note_edit;
    private EditText a08_edit, a09_edit, a10_edit, a11_edit, a12_edit, a13_edit, a14_edit, a15_edit, a16_edit,
                        a17_edit, a18_edit, a19_edit, a20_edit, a21_edit;
    private EditText m08_edit, m09_edit, m10_edit, m11_edit, m12_edit, m13_edit, m14_edit, m15_edit, m16_edit,
                      m17_edit, m18_edit, m19_edit, m20_edit, m21_edit;
    private EditText p08_edit, p09_edit, p10_edit, p11_edit, p12_edit, p13_edit, p14_edit, p15_edit, p16_edit,
                       p17_edit, p18_edit, p19_edit, p20_edit, p21_edit;

    private DailyDAO dailyDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_daily);

        processViews();

        // 取得資料庫物件
        dailyDAO = new DailyDAO(this);
    }

    private void processViews() {
        note_edit = (EditText) findViewById(R.id.note_edit);
        a08_edit = (EditText) findViewById(R.id.a08_edit);  a09_edit = (EditText) findViewById(R.id.a09_edit);
        a10_edit = (EditText) findViewById(R.id.a10_edit);  a11_edit = (EditText) findViewById(R.id.a11_edit);
        a12_edit = (EditText) findViewById(R.id.a12_edit);  a13_edit = (EditText) findViewById(R.id.a13_edit);
        a14_edit = (EditText) findViewById(R.id.a14_edit);  a15_edit = (EditText) findViewById(R.id.a15_edit);
        a16_edit = (EditText) findViewById(R.id.a16_edit);  a17_edit = (EditText) findViewById(R.id.a17_edit);
        a18_edit = (EditText) findViewById(R.id.a18_edit);  a19_edit = (EditText) findViewById(R.id.a19_edit);
        a20_edit = (EditText) findViewById(R.id.a20_edit);  a21_edit = (EditText) findViewById(R.id.a21_edit);

        m08_edit = (EditText) findViewById(R.id.m08_edit);  m09_edit = (EditText) findViewById(R.id.m09_edit);
        m10_edit = (EditText) findViewById(R.id.m10_edit);  m11_edit = (EditText) findViewById(R.id.m11_edit);
        m12_edit = (EditText) findViewById(R.id.m12_edit);  m13_edit = (EditText) findViewById(R.id.m13_edit);
        m14_edit = (EditText) findViewById(R.id.m14_edit);  m15_edit = (EditText) findViewById(R.id.m15_edit);
        m16_edit = (EditText) findViewById(R.id.m16_edit);  m17_edit = (EditText) findViewById(R.id.m17_edit);
        m18_edit = (EditText) findViewById(R.id.m18_edit);  m19_edit = (EditText) findViewById(R.id.m19_edit);
        m20_edit = (EditText) findViewById(R.id.m20_edit);  m21_edit = (EditText) findViewById(R.id.m21_edit);

        p08_edit = (EditText) findViewById(R.id.p08_edit);  p09_edit = (EditText) findViewById(R.id.p09_edit);
        p10_edit = (EditText) findViewById(R.id.p10_edit);  p11_edit = (EditText) findViewById(R.id.p11_edit);
        p12_edit = (EditText) findViewById(R.id.p12_edit);  p13_edit = (EditText) findViewById(R.id.p13_edit);
        p14_edit = (EditText) findViewById(R.id.p14_edit);  p15_edit = (EditText) findViewById(R.id.p15_edit);
        p16_edit = (EditText) findViewById(R.id.p16_edit);  p17_edit = (EditText) findViewById(R.id.p17_edit);
        p18_edit = (EditText) findViewById(R.id.p18_edit);  p19_edit = (EditText) findViewById(R.id.p19_edit);
        p20_edit = (EditText) findViewById(R.id.p20_edit);  p21_edit = (EditText) findViewById(R.id.p21_edit);

    }


    public void clickOk(View view) {
        // 讀取使用者輸入的資料
        /* double latitudeValue = Double.parseDouble(latitude_edit.getText().toString());
        double longitudeValue = Double.parseDouble(longitude_edit.getText().toString());
        double accuracyValue = Double.parseDouble(accuracy_edit.getText().toString()); */
        String note = note_edit.getText().toString();
        String a08 = a08_edit.getText().toString();  String a09 = a09_edit.getText().toString();
        String a10 = a10_edit.getText().toString();  String a11 = a11_edit.getText().toString();
        String a12 = a12_edit.getText().toString();  String a13 = a13_edit.getText().toString();
        String a14 = a14_edit.getText().toString();  String a15 = a15_edit.getText().toString();
        String a16 = a16_edit.getText().toString();  String a17 = a17_edit.getText().toString();
        String a18 = a18_edit.getText().toString();  String a19 = a19_edit.getText().toString();
        String a20 = a20_edit.getText().toString();  String a21 = a21_edit.getText().toString();

        String m08 = m08_edit.getText().toString();  String m09 = m09_edit.getText().toString();
        String m10 = m10_edit.getText().toString();  String m11 = m11_edit.getText().toString();
        String m12 = m12_edit.getText().toString();  String m13 = m13_edit.getText().toString();
        String m14 = m14_edit.getText().toString();  String m15 = m15_edit.getText().toString();
        String m16 = m16_edit.getText().toString();  String m17 = m17_edit.getText().toString();
        String m18 = m18_edit.getText().toString();  String m19 = m19_edit.getText().toString();
        String m20 = m20_edit.getText().toString();  String m21 = m21_edit.getText().toString();

        String p08 = p08_edit.getText().toString();  String p09 = p09_edit.getText().toString();
        String p10 = p10_edit.getText().toString();  String p11 = p11_edit.getText().toString();
        String p12 = p12_edit.getText().toString();  String p13 = p13_edit.getText().toString();
        String p14 = p14_edit.getText().toString();  String p15 = p15_edit.getText().toString();
        String p16 = p16_edit.getText().toString();  String p17 = p17_edit.getText().toString();
        String p18 = p18_edit.getText().toString();  String p19 = p19_edit.getText().toString();
        String p20 = p20_edit.getText().toString();  String p21 = p21_edit.getText().toString();

        // 建立準備新增資料的物件
        Daily daily = new Daily();

        // 把讀取的資料設定給物件
        /* place.setLatitude(latitudeValue);
        place.setLongitude(longitudeValue);
        place.setAccuracy(accuracyValue); */
        daily.setDatetime(System.currentTimeMillis());
        daily.setNote(note);
        daily.setA08(a08);  daily.setA09(a09);
        daily.setA10(a10);  daily.setA11(a11);
        daily.setA12(a12);  daily.setA13(a13);
        daily.setA14(a14);  daily.setA15(a15);
        daily.setA16(a16);  daily.setA17(a17);
        daily.setA18(a18);  daily.setA19(a19);
        daily.setA20(a20);  daily.setA21(a21);

        daily.setM08(m08);  daily.setM09(m09);
        daily.setM10(m10);  daily.setM11(m11);
        daily.setM12(m12);  daily.setM13(m13);
        daily.setM14(m14);  daily.setM15(m15);
        daily.setM16(m16);  daily.setM17(m17);
        daily.setM18(m18);  daily.setM19(m19);
        daily.setM20(m20);  daily.setM21(m21);

        daily.setP08(p08);  daily.setP09(p09);
        daily.setP10(p10);  daily.setP11(p11);
        daily.setP12(p12);  daily.setP13(p13);
        daily.setP14(p14);  daily.setP15(p15);
        daily.setP16(p16);  daily.setP17(p17);
        daily.setP18(p18);  daily.setP19(p19);
        daily.setP20(p20);  daily.setP21(p21);

        // 新增
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(Insert_DailyActivity.this);
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

        DailyRequest dailyRequest = new DailyRequest(note,a08,a09,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,m08,m09,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20,m21,p08,p09,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Insert_DailyActivity.this);
        queue.add(dailyRequest);
        // 顯示新增成功

    }
        // 顯示新增成功


    public void clickCancel(View view) {
        // 結束
        finish();
    }


}
