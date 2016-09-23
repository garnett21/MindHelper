package  com.example.mindhelper.mindhelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    // 資料庫名稱
    public static final String DATABASE_NAME = "mydata.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 5;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;
    private static MyDBHelper instance = null;

    public static MyDBHelper getInstance(Context ctx){
        if (instance==null){
            instance = new MyDBHelper(ctx, "expense.db", null, 1);
        }
        return instance;
    }
    // 建構子，在一般的應用都不需要修改
    public MyDBHelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new MyDBHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }

   @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立應用程式需要的表格
       db.execSQL(ItemDAO.CREATE_TABLE);
       db.execSQL(DailyDAO.CREATE_TABLE);
       db.execSQL(TcrDAO.CREATE_TABLE);
       db.execSQL("CREATE  TABLE main.exp " +
               "(_id INTEGER PRIMARY KEY  NOT NULL , " +
               "cdate VARCHAR(20) NOT NULL , " +
               "info VARCHAR)");
    }



    @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格

        db.execSQL("DROP TABLE IF EXISTS " + ItemDAO.TABLE_NAME);
        // 呼叫onCreate建立新版的表格
     onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + DailyDAO.TABLE_NAME);
        // 呼叫onCreate建立新版的表格
        db.execSQL("DROP TABLE IF EXISTS " + TcrDAO.TABLE_NAME);
        onCreate(db);

   }



}