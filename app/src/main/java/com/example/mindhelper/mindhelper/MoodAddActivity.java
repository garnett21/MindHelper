package com.example.mindhelper.mindhelper;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MoodAddActivity extends AppCompatActivity {

    private EditText edDate;
    private EditText edInfo;
//    private EditText edAmount;
    private MyDBHelper helper;
    private RatingBar ratingbar = null;
    private String edDate2;
    private String edInfo2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood_);

        ratingbar = (RatingBar) findViewById(R.id.mind_ratingBar);
        ratingbar.setOnRatingBarChangeListener(new RatingBarListener());
        findViews();
        helper = MyDBHelper.getInstance(this);
        Button view= (Button) findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoodAddActivity.this, MoodFinanceActivity.class));
            }
        });
    }

    private void findViews() {
        edInfo2 = String.valueOf((RatingBar) findViewById(R.id.mind_ratingBar));

    }

    public void add(View v){
        String cdate = edDate2.toString();
        String info = edInfo2.toString();


        ContentValues values = new ContentValues();
        values.put("cdate", cdate);
        values.put("info", info);

        long id = helper.getWritableDatabase().insert("exp", null, values);
        Log.d("ADD", id+"");
        Intent intent = new Intent();
        intent.setClass(MoodAddActivity.this  , MoodFinanceActivity.class);
        startActivity(intent);
    }
    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener {

        public void onRatingChanged(RatingBar ratingBar, float rating,
                                    boolean fromUser) {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/HH:mm");

            String mind_time = df.format(c.getTime());

            RatingBar rb = (RatingBar) findViewById(R.id.mind_ratingBar);

            String rate = String.valueOf(rb.getRating());

            TextView myTextView = (TextView) findViewById(R.id.mind_View);
            myTextView.setText(mind_time + " ，心情指數: " + rate);

            System.out.println("心情：" + rating);

            edDate2= mind_time;
            edInfo2=rate;
        }
    }
}
