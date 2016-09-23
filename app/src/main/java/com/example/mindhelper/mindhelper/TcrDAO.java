package com.example.mindhelper.mindhelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by X550V-NB on 2016/8/18.
 */

    // 資料功能類別
    public class TcrDAO {
        // 表格名稱
        public static final String TABLE_NAME = "tcr";

        // 編號表格欄位名稱，固定不變
        public static final String KEY_ID = "_id";

        // 其它表格欄位名稱
        public static final String DATETIME_COLUMN = "datetime";
        public static final String NOTE_COLUMN = "note";
    public static final String ANS1_WHO_COLUMN = "ans1_who";
    public static final String ANS1_WHEN_COLUMN = "ans1_when";
    public static final String ANS1_WHERE_COLUMN = "ans1_where";
    public static final String ANS1_WHAT_COLUMN = "ans1_what";

    public static final String ANS2_EMO_COLUMN = "ans2_emo";
    public static final String ANS2_PER_COLUMN = "ans2_per";
        public static final String ANS3_COLUMN = "ans3";
        public static final String ANS4_COLUMN = "ans4";
        public static final String ANS5_COLUMN = "ans5";
        public static final String ANS6_COLUMN = "ans6";
        public static final String ANS7_COLUMN = "ans7";

    // 所有欄位名稱陣列，把所有表格欄位名稱變數湊起來建立一個字串陣列
    public static final String[] COLUMNS = //之後要和每日活動作合併，不確定要不要改名
            { KEY_ID,DATETIME_COLUMN, NOTE_COLUMN,
                    ANS1_WHO_COLUMN, ANS1_WHEN_COLUMN, ANS1_WHERE_COLUMN, ANS1_WHAT_COLUMN,
                    ANS2_EMO_COLUMN, ANS2_PER_COLUMN, ANS3_COLUMN, ANS4_COLUMN,
                    ANS5_COLUMN, ANS6_COLUMN, ANS7_COLUMN };

        // 顯示用欄位名稱陣列，
        // 在資料查詢畫面上希望顯示位置表格的編號、日期時間和說明，
        // 所以額外使用三個表格欄位名稱變數建立這個欄位名稱陣列
        public static final String[] SHOW_COLUMNS =
                { KEY_ID, ANS1_WHEN_COLUMN,NOTE_COLUMN };

        // 使用上面宣告的變數建立表格的SQL指令
        public static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DATETIME_COLUMN + " TEXT , " +
                        NOTE_COLUMN + " TEXT , " +

                        ANS1_WHO_COLUMN + " TEXT , " +
                        ANS1_WHEN_COLUMN + " TEXT , " +
                        ANS1_WHERE_COLUMN + " TEXT , " +
                        ANS1_WHAT_COLUMN + " TEXT , " +

                        ANS2_EMO_COLUMN + " TEXT , " +
                        ANS2_PER_COLUMN + " TEXT , " +
                        ANS3_COLUMN + " TEXT , " +
                        ANS4_COLUMN + " TEXT  , " +
                        ANS5_COLUMN + " TEXT  , " +
                        ANS6_COLUMN + " TEXT  , " +
                        ANS7_COLUMN + " TEXT  )";

        // 資料庫物件
        private SQLiteDatabase db;

        // 建構子，一般的應用都不需要修改
        public TcrDAO(Context context) {
            db = MyDBHelper.getDatabase(context);
        }

        // 關閉資料庫，一般的應用都不需要修改
        public void close() {
            db.close();
        }

        // 新增參數指定的物件
        public Tcr insert(Tcr tcr) {
            // 建立準備新增資料的ContentValues物件
            ContentValues cv = new ContentValues();

            // 加入ContentValues物件包裝的新增資料
            // 第一個參數是欄位名稱， 第二個參數是欄位的資料
            cv.put(DATETIME_COLUMN, tcr.getDatetime());
            cv.put(NOTE_COLUMN, tcr.getNote());

            cv.put(ANS1_WHO_COLUMN, tcr.getAns1_who());
            cv.put(ANS1_WHEN_COLUMN, tcr.getAns1_when());
            cv.put(ANS1_WHERE_COLUMN, tcr.getAns1_where());
            cv.put(ANS1_WHAT_COLUMN, tcr.getAns1_what());

            cv.put(ANS2_EMO_COLUMN, tcr.getAns2_emo());
            cv.put(ANS2_PER_COLUMN, tcr.getAns2_per());
            cv.put(ANS3_COLUMN, tcr.getAns3());
            cv.put(ANS4_COLUMN, tcr.getAns4());
            cv.put(ANS5_COLUMN, tcr.getAns5());
            cv.put(ANS6_COLUMN, tcr.getAns6());
            cv.put(ANS7_COLUMN, tcr.getAns7());


            // 新增一筆資料並取得編號
            // 第一個參數是表格名稱
            // 第二個參數是沒有指定欄位值的預設值
            // 第三個參數是包裝新增資料的ContentValues物件
            long id = db.insert(TABLE_NAME, null, cv);

            // 設定編號
            tcr.setId(id);
            // 回傳結果
            return tcr;
        }

        // 修改參數指定的物件
        public boolean update(Tcr tcr) {
            // 建立準備修改資料的ContentValues物件
            ContentValues cv = new ContentValues();

            // 加入ContentValues物件包裝的修改資料
            // 第一個參數是欄位名稱， 第二個參數是欄位的資料
            cv.put(DATETIME_COLUMN, tcr.getDatetime());
            cv.put(NOTE_COLUMN, tcr.getNote());
            cv.put(ANS1_WHO_COLUMN, tcr.getAns1_who());
            cv.put(ANS1_WHEN_COLUMN, tcr.getAns1_when());
            cv.put(ANS1_WHERE_COLUMN, tcr.getAns1_where());
            cv.put(ANS1_WHAT_COLUMN, tcr.getAns1_what());

            cv.put(ANS2_EMO_COLUMN, tcr.getAns2_emo());
            cv.put(ANS2_PER_COLUMN, tcr.getAns2_per());
            cv.put(ANS3_COLUMN, tcr.getAns3());
            cv.put(ANS4_COLUMN, tcr.getAns4());
            cv.put(ANS5_COLUMN, tcr.getAns5());
            cv.put(ANS6_COLUMN, tcr.getAns6());
            cv.put(ANS7_COLUMN, tcr.getAns7());

            // 設定修改資料的條件為編號
            // 格式為「欄位名稱＝資料」
            String where = KEY_ID + "=" + tcr.getId();

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
        public Tcr get(long id) {
            // 準備回傳結果用的物件
            Tcr tcr = null;
            // 使用編號為查詢條件
            String where = KEY_ID + "=" + id;
            // 執行查詢
            Cursor result = db.query(
                    TABLE_NAME,COLUMNS, where, null, null, null, null, null);

            // 如果有查詢結果
            if (result.moveToFirst()) {
                // 讀取包裝一筆資料的物件
                tcr = getRecord(result);
            }

            // 關閉Cursor物件
            result.close();
            // 回傳結果
            return tcr;
        }

        // 把Cursor目前的資料包裝為物件
        public Tcr getRecord(Cursor cursor) {
            // 準備回傳結果用的物件
            Tcr result = new Tcr();

            result.setId(cursor.getLong(0));

            result.setDatetime(cursor.getString(1));
            result.setNote(cursor.getString(2));

            result.setAns1_who(cursor.getString(3));
            result.setAns1_where(cursor.getString(4));
            result.setAns1_when(cursor.getString(5));
            result.setAns1_what(cursor.getString(6));

            result.setAns2_emo(cursor.getString(7));
            result.setAns2_per(cursor.getString(8));
            result.setAns3(cursor.getString(9));
            result.setAns4(cursor.getString(10));
            result.setAns5(cursor.getString(11));
            result.setAns6(cursor.getString(12));
            result.setAns7(cursor.getString(13));

            // 回傳結果
            return result;
        }


    // 第一次執行應用程式新增一些範例資料
    public void sampleData(Context context) {
        SharedPreferences sp =
                PreferenceManager.getDefaultSharedPreferences(context);
        boolean firstTime = sp.getBoolean("FIRST_TIME", true);

        if (firstTime) {

            Tcr t03 = new Tcr(0, "2016-02-12 16:50", "Awesome!", "Awesome!", "Awesome!", "Awesome!", "Awesome!", "Awesome!", "Awesome!", "Awesome!","","","","","","","");


            insert(t03);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); //原"yyyy-MM-dd HH:mm"

             for (int i = 1; i < 6; i++) {
                Date date = new Date(System.currentTimeMillis() + (i * 1000 * 60 * 60 * 24) );
                Tcr tcr = new Tcr(0, sdf.format(date), "Place: " + i , "", "", "", "", "", "", "","","","","","","","");
                insert(tcr);
            }

            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("FIRST_TIME", false);
            editor.commit();
        }
    }


    }
