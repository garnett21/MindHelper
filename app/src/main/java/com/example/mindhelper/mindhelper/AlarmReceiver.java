package  com.example.mindhelper.mindhelper;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    @Override
    public void onReceive(Context context, Intent intent) {
        // 讀取記事標題
        //String title = intent.getStringExtra("title");
        // 顯示訊息框
        //Toast.makeText(context, title, Toast.LENGTH_LONG).show();

        // 讀取記事編號
        long id = intent.getLongExtra("id", 0);

        if (id != 0) {
            sendNotify(context, id);
        }
    }

    private void sendNotify(Context context, long id) {
        // 建立資料庫物件
        DailyDAO dailyDAO = new DailyDAO(context.getApplicationContext());
        // 讀取指定編號的記事物件
       Daily daily = dailyDAO.get(id);

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        // Set the alarm to start at 8:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 60, alarmIntent);

        // 取得NotificationManager物件
        NotificationManager nm = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);


        // 建立NotificationCompat.Builder物件
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context);
            // 設定圖示、時間、內容標題和內容訊息
            builder.setSmallIcon(android.R.drawable.star_big_on)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText(daily.getNote());
            // 發出通知
            nm.notify((int)daily.getId(), builder.build());
        }
    }


