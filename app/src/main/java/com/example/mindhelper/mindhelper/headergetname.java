package com.example.mindhelper.mindhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by SW on 2016/9/23.
 */

public class headergetname extends AppCompatActivity {

    public TextView header;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_drawer);
        Intent recIntent = getIntent();
       // String text = recIntent.getStringExtra("p_name");
        TextView textView1 = (TextView) findViewById(R.id.name); //get a reference to the textview on the game.xml file.
        textView1.setText("123");

    }

}

