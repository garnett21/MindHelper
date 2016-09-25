package com.example.mindhelper.mindhelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class User_main extends AppCompatActivity {

    final Context context = this;
    private Button button;
    private EditText result;
    private Button button_p;
    private Button button_cbt;
    private Button button_player;
    private TextView textView_help;
private TextView textView_logout;
    private Button card;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_drawer);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("p_name");
        final String username = intent.getStringExtra("p_account");
        final String password = intent.getStringExtra("p_password");
        final int id = intent.getIntExtra("p_index", -1);
        final String sex = intent.getStringExtra("p_gender");
        final String email = intent.getStringExtra("p_email");
        final String address = intent.getStringExtra("p_address");



       /* button_p.setOnClickListener(new View.OnClickListener() { //前往個人資料
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(User_main.this, PersonActivity.class);
                intent.putExtra("p_name", name);
                intent.putExtra("p_password", password);
                intent.putExtra("p_account", username);
                intent.putExtra("p_index", id);
                intent.putExtra("p_gender", sex);
                intent.putExtra("p_email", email);
                intent.putExtra("p_address", address);
                startActivity(intent);
                User_main.this.startActivity(intent);
            }
        });

      /*  button_cbt.setOnClickListener(new View.OnClickListener() { //前往TCR
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();//實例化一個Intent物件
                intent.setClass(User_main.this, CBT.class);//設定要start的Avtivity，第一個參數是現在的Activity，第二個參數是要開啟的Activity
                intent.putExtra("p_name", name);
                intent.putExtra("p_password", password);
                intent.putExtra("p_account", username);
                intent.putExtra("p_index", id);
                intent.putExtra("p_gender", sex);
                intent.putExtra("p_email", email);
                intent.putExtra("p_address", address);
                startActivity(intent);  //開啟另一個Activity
                User_main.this.finish();
            }
        });
        button_player.setOnClickListener(new View.OnClickListener() { //前往TCR
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();//實例化一個Intent物件
                intent.setClass(User_main.this, PlayAudioExample.class);//設定要start的Avtivity，第一個參數是現在的Activity，第二個參數是要開啟的Activity
                startActivity(intent);  //開啟另一個Activity
                User_main.this.finish();
            }
        });

        textView_help.setOnClickListener(new View.OnClickListener() {  //彈出"幫助"視窗&內容
            @Override
            public void onClick(View v) {

               new AlertDialog.Builder(User_main.this)
                       .setTitle("Mind Helper 幫助")
                       .setMessage("如何使用Mind Helper?\n1.\n2.\n3.\n4.")
                       .setPositiveButton("OK",null)
                       .show();            }
        });

        textView_logout.setOnClickListener(new View.OnClickListener() { //前往TCR
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();//實例化一個Intent物件

                intent.setClass(User_main.this, Login.class);//設定要start的Avtivity，第一個參數是現在的Activity，第二個參數是要開啟的Activity
                startActivity(intent);  //開啟另一個Activity
                User_main.this.finish();
            }
        });

        card.setOnClickListener(new View.OnClickListener() { //前往TCR
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();//實例化一個Intent物件
                intent.putExtra("id", id);
                intent.setClass(User_main.this, Card.class);//設定要start的Avtivity，第一個參數是現在的Activity，第二個參數是要開啟的Activity
                startActivity(intent);  //開啟另一個Activity
                User_main.this.finish();
            }
        });


        button.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent();//實例化一個Intent物件
                intent.setClass(User_main.this, MoodAddActivity.class);//設定要start的Avtivity，第一個參數是現在的Activity，第二個參數是要開啟的Activity
                startActivity(intent);  //開啟另一個Activity
                User_main.this.finish();
            }
         /*   public void onClick(View arg0) { //彈出視窗

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.mood_content_finance, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                  public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        result.setText(userInput.getText());
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });*/
    }






}



