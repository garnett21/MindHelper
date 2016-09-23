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
        header = (TextView) findViewById(R.id.header);
    }

    public void ppp()
    {
        header.setText("ooo");
    }
}

