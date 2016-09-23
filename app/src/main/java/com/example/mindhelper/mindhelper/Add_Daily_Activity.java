package com.example.mindhelper.mindhelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class Add_Daily_Activity extends Activity {

    private EditText add_time, add_activity, add_hold, add_mood;
    private Button d_add;

    private EditText title_text, content_text,mood,hold;

    // 啟動功能用的請求代碼
    private static final int START_CAMERA = 0;
    private static final int START_RECORD = 1;
    private static final int START_LOCATION = 2;
    private static final int START_ALARM = 3;
    private static final int START_COLOR = 4;

    // 記事物件
    private Itemm item;

    // 檔案名稱
    private String fileName;

    // 錄音檔案名稱
    private String recFileName;

    // 照片
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 需要在狀態列顯示處理中圖示，
        // 一定要在指定Activity元件畫面配置資源之前，
        // 使用這行敘述執行設定
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_add__daily_);



        // 取得Intent物件
        Intent intent = getIntent();
        // 讀取Action名稱
        String action = intent.getAction();

        // 如果是修改記事
        if (action.equals("com.example.mindhelper.mindhelper.EDIT_ITEMM")) {
            // 接收記事物件與設定標題、內容
            item = (Itemm) intent.getExtras().getSerializable(
                    "com.example.mindhelper.mindhelper.Itemm");
            title_text.setText(item.getTitle());
            content_text.setText(item.getContent());
            mood.setText(item.getMood());
            hold.setText(item.getHold());
        }
        // 新增記事
        else {
            item = new Itemm();
            // 建立SharedPreferences物件
            SharedPreferences sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(this);
            // 讀取設定的預設顏色
            int color = sharedPreferences.getInt("DEFAULT_COLOR", -1);
            item.setColor(getColors(color));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果有檔案名稱
        if (item.getFileName() != null && item.getFileName().length() > 0) {
            // 照片檔案物件
            File file = configFileName("P", ".jpg");

            // 如果照片檔案存在
            if (file.exists()) {
                // 顯示照片元件
                picture.setVisibility(View.VISIBLE);
                // 設定照片
                FileUtil.fileToImageView(file.getAbsolutePath(), picture);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                // 照像
                case START_CAMERA:
                    // 設定照片檔案名稱
                    item.setFileName(fileName);
                    break;
                case START_RECORD:
                    // 設定錄音檔案名稱
                    item.setRecFileName(fileName);
                    break;
                case START_LOCATION:
                    // 讀取與設定座標
                    double lat = data.getDoubleExtra("lat", 0.0);
                    double lng = data.getDoubleExtra("lng", 0.0);
                    item.setLatitude(lat);
                    item.setLongitude(lng);
                    break;
                case START_ALARM:
                    break;
                // 設定顏色
                case START_COLOR:
                    int colorId = data.getIntExtra(
                            "colorId", Colors.LIGHTGREY.parseColor());
                    item.setColor(getColors(colorId));
                    break;
            }
        }
    }

    public static Colors getColors(int color) {
        Colors result = Colors.LIGHTGREY;

        if (color == Colors.BLUE.parseColor()) {
            result = Colors.BLUE;
        }
        else if (color == Colors.PURPLE.parseColor()) {
            result = Colors.PURPLE;
        }
        else if (color == Colors.GREEN.parseColor()) {
            result = Colors.GREEN;
        }
        else if (color == Colors.ORANGE.parseColor()) {
            result = Colors.ORANGE;
        }
        else if (color == Colors.RED.parseColor()) {
            result = Colors.RED;
        }

        return result;
    }



    // 點擊確定與取消按鈕都會呼叫這個方法
    public void onSubmit(View view) {

        if (view.getId() == R.id.ok_teim) {
            String titleText = title_text.getText().toString();
            String contentText = content_text.getText().toString();
            String moodtext = mood.getText().toString();
            String holdtext = hold.getText().toString();
            item.setTitle(titleText);
            item.setContent(contentText);
            item.setMood(moodtext);
            item.setHold(holdtext);
            if (getIntent().getAction().equals(
                    "EDIT_ITEMM")) {
                item.setLastModify(new Date().getTime());
            }
            // 新增記事
            else {
                item.setDatetime(new Date().getTime());

            }

            Intent result = getIntent();
            result.putExtra("com.example.mindhelper.mindhelper.Itemm", item);
            setResult(Activity.RESULT_OK, result);
        }

        // 結束
        finish();
    }


    // 設定提醒日期時間
    private void processSetAlarm() {
        Calendar calendar = Calendar.getInstance();

        if (item.getAlarmDatetime() != 0) {
            // 設定為已經儲存的提醒日期時間
            calendar.setTimeInMillis(item.getAlarmDatetime());
        }

        // 讀取年、月、日、時、分
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // 儲存設定的提醒日期時間
        final Calendar alarm = Calendar.getInstance();

        // 設定提醒時間
        TimePickerDialog.OnTimeSetListener timeSetListener =
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view,
                                          int hourOfDay, int minute) {
                        alarm.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        alarm.set(Calendar.MINUTE, minute);

                        item.setAlarmDatetime(alarm.getTimeInMillis());
                    }
                };

        // 選擇時間對話框
        final TimePickerDialog tpd = new TimePickerDialog(
                this, timeSetListener, hour, minute, true);

        // 設定提醒日期
        DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,
                                          int year,
                                          int monthOfYear,
                                          int dayOfMonth) {
                        alarm.set(Calendar.YEAR, year);
                        alarm.set(Calendar.MONTH, monthOfYear);
                        alarm.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // 繼續選擇提醒時間
                        tpd.show();
                    }
                };

        // 建立與顯示選擇日期對話框
        final DatePickerDialog dpd = new DatePickerDialog(
                this, dateSetListener, year, month, day);
        dpd.show();
    }

    private File configFileName(String prefix, String extension) {
        // 如果記事資料已經有檔案名稱
        if (item.getFileName() != null && item.getFileName().length() > 0) {
            fileName = item.getFileName();
        }
        // 產生檔案名稱
        else {
            fileName = FileUtil.getUniqueFileName();
        }

        return new File(FileUtil.getExternalStorageDir(FileUtil.APP_DIR),
                prefix + fileName + extension);
    }

    private File configRecFileName(String prefix, String extension) {
        // 如果記事資料已經有檔案名稱
        if (item.getRecFileName() != null && item.getRecFileName().length() > 0) {
            recFileName = item.getRecFileName();
        }
        // 產生檔案名稱
        else {
            recFileName = FileUtil.getUniqueFileName();
        }

        return new File(FileUtil.getExternalStorageDir(FileUtil.APP_DIR),
                prefix + recFileName + extension);
    }

}


