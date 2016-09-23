package com.example.mindhelper.mindhelper;

/**
 * Created by X550V-NB on 2016/8/22.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// 資料功能類別
public class DailyDAO {

    // 表格名稱
    public static final String TABLE_NAME = "daily";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它需要的表格欄位名稱
    /* public static final String LATITUDE_COLUMN = "latitude";
    public static final String LONGITUDE_COLUMN = "longitude";
    public static final String ACCURACY_COLUMN = "accuracy"; */

    public static final String PID_COLUMN = "patientid";
    public static final String ACTIVITY_COLUMN = "dailyactivity";
    public static final String MOOD_COLUMN = "dailymood";
    public static final String HOLD_COLUMN = "dailyhold";
    public static final String DATE_COLUMN = "dailydate";
    public static final String TIME_COLUMN = "dailytime";



    // 所有欄位名稱陣列，把所有表格欄位名稱變數湊起來建立一個字串陣列
    public static final String[] COLUMNS =
            { KEY_ID, PID_COLUMN,
                    ACTIVITY_COLUMN,MOOD_COLUMN,HOLD_COLUMN,DATE_COLUMN,TIME_COLUMN
            };

    // 顯示用欄位名稱陣列，
    // 在資料查詢畫面上希望顯示位置表格的編號、日期時間和說明，
    // 所以額外使用三個表格欄位名稱變數建立這個欄位名稱陣列
    public static final String[] SHOW_COLUMNS =
            { KEY_ID, TIME_COLUMN,DATE_COLUMN };

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PID_COLUMN + " TEXT NOT NULL," + ACTIVITY_COLUMN + " TEXT, "+MOOD_COLUMN + " TEXT, "+HOLD_COLUMN + " TEXT, "+DATE_COLUMN + " TEXT, "+TIME_COLUMN + " TEXT) ";
    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DailyDAO(Context context) {
        db = MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public Daily insert(Daily daily) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        /* cv.put(LATITUDE_COLUMN, place.getLatitude());
        cv.put(LONGITUDE_COLUMN, place.getLongitude());
        cv.put(ACCURACY_COLUMN, place.getAccuracy()); */

        cv.put(PID_COLUMN, daily.getP_id());
        cv.put(ACTIVITY_COLUMN, daily.getActivity());
        cv.put(MOOD_COLUMN, daily.getMood());
        cv.put(HOLD_COLUMN, daily.getHold());
        cv.put(DATE_COLUMN, daily.getDate());
        cv.put(TIME_COLUMN, daily.getTime());


        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        daily.setId(id);
        // 回傳結果
        return daily;
    }

    // 修改參數指定的物件
    public boolean update(Daily daily) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        /* cv.put(LATITUDE_COLUMN, place.getLatitude());
        cv.put(LONGITUDE_COLUMN, place.getLongitude());
        cv.put(ACCURACY_COLUMN, place.getAccuracy()); */

        cv.put(PID_COLUMN, daily.getP_id());
        cv.put(ACTIVITY_COLUMN, daily.getActivity());
        cv.put(MOOD_COLUMN, daily.getMood());
        cv.put(HOLD_COLUMN, daily.getHold());
        cv.put(DATE_COLUMN, daily.getDate());
        cv.put(TIME_COLUMN, daily.getTime());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + daily.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 取得所有資料的Cursor物件
    public Cursor getAllCursor() {
        Cursor result = db.query(TABLE_NAME, SHOW_COLUMNS, null, null, null, null, null);
        return result;
    }

    // 取得參數指定日期資料的Cursor物件
    public Cursor getSearchCursor(String date) {
        // 設定條件為查詢日期時間欄位的前十個字元，就是日期的部份，
        // 格式為「substr(欄位名稱,開始,個數)='資料'」
        // 字串資料必須在前後加上「'」，數字不用
        String where = "substr(datetime, 1, 10)='" + date + "'";
        // 查詢指定日期條件的資料
        Cursor result = db.query(TABLE_NAME, SHOW_COLUMNS, where, null, null, null, null);
        return result;
    }

    // 取得指定編號的資料物件
    public Daily get(long id) {
        // 準備回傳結果用的物件
        Daily daily = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, COLUMNS, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            daily = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return daily;
    }

    // 把Cursor目前的資料包裝為物件
    public Daily getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Daily result = new Daily();

        result.setId(cursor.getLong(0));
        /* result.setLatitude(cursor.getDouble(1));
        result.setLongitude(cursor.getDouble(2));
        result.setAccuracy(cursor.getDouble(3)); */

        result.setP_id(cursor.getString(1));
         result.setActivity(cursor.getString(2));
        result.setMood(cursor.getString(3));result.setHold(cursor.getString(4));result.setDate(cursor.getString(5));result.setTime(cursor.getString(6));

        // 回傳結果
        return result;
    }



}


