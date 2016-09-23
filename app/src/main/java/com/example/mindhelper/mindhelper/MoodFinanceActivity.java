package com.example.mindhelper.mindhelper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MoodFinanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_content_finance);

//        nextPageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(FinanceActivity.this  , chart_Activity.class);
//                startActivity(intent);
//            }
//        });




        ListView list = (ListView) findViewById(R.id.list);
        MyDBHelper helper = MyDBHelper.getInstance(this);
//        "exp", null, null, null, null, null,null  正序
//        倒序
        Cursor c = helper.getReadableDatabase().query(
                "exp", null, null, null, null, null,"_id DESC");

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.mood_finance_row,
                c,
                new String[] {"_id", "cdate", "info"},
                new int[] {R.id.item_id, R.id.item_cdate,
                        R.id.item_info}, 0);


        list.setAdapter(adapter);



       Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoodFinanceActivity.this, MoodAddActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
          }
      });
        Button back= (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoodFinanceActivity.this, User_main.class));
            }
        });
    }

}
