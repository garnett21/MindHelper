package com.example.mindhelper.mindhelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import static com.example.mindhelper.mindhelper.R.id.drawer_layout;

public class drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ViewSwitcher.ViewFactory, View.OnTouchListener {
    private ImageSwitcher mImageSwitcher;
    /**
     * 图片id数组
     */
    private int[] imgIds;
    /**
     * 当前选中的图片id序号
     */
    private int currentPosition;
    /**
     * 按下点的X坐标
     */
    private float downX;
    /**
     * 装载点点的容器
     */
    private LinearLayout linearLayout;
    /**
     * 点点数组
     */



    private ImageView[] tips;
    /**
     * 实现图片自动切换的核心部分
     */
    Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    if(currentPosition < imgIds.length - 1){
                        currentPosition ++ ;
                    }else{
                        currentPosition=0;
                    }
                    mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_in));
                    mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_out));
                    mImageSwitcher.setImageResource(imgIds[currentPosition]);
                    //为了保证在滑动的时候，图片和点点切换时间一致，因为动画有500的延迟
                    handler.sendEmptyMessageDelayed(2, 500);
                    handler.sendEmptyMessageDelayed(1, 2000);
                    break;
                case 2:
                    //  setImageBackground(currentPosition);
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_drawer, null);
        navigationView.addHeaderView(header);
        TextView text = (TextView) header.findViewById(R.id.name);
        TextView text2 = (TextView) header.findViewById(R.id.mail);
        Intent recIntent = getIntent();
        Intent recIntent2 = getIntent();
        String name = recIntent.getStringExtra("p_name");
        String mail = recIntent2.getStringExtra("p_email");
        text.setText(name);
        text2.setText(mail);

                TextView textView = (TextView)findViewById(R.id.textView);
        textView.setOnClickListener(new TextView.OnClickListener() {
            @Override

            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent(drawer.this, cbtinfo.class);
                drawer.this.startActivity(intent);


            }
        });
        imgIds = new int[]{R.drawable.relax1, R.drawable.relax4, R.drawable.relax3};
        //实例化ImageSwitcher
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        //设置Factory
        mImageSwitcher.setFactory(this);
        //设置OnTouchListener，我们通过Touch事件来切换图片
        mImageSwitcher.setOnTouchListener(this);

        linearLayout = (LinearLayout) findViewById(R.id.viewGroup);

        tips = new ImageView[imgIds.length];
        for (int i = 0; i < imgIds.length; i++) {
            ImageView mImageView = new ImageView(this);
            tips[i] = mImageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.rightMargin = 3;
            layoutParams.leftMargin = 3;

            //mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            linearLayout.addView(mImageView, layoutParams);
        }

        //这个我是从上一个界面传过来的，上一个界面是一个GridView
        mImageSwitcher.setImageResource(imgIds[currentPosition]);
        //设置图片之间切换的时间间隔
        handler.sendEmptyMessageDelayed(1, 3000);
        //setImageBackground(currentPosition);
    }
    @Override
    public View makeView() {
        final ImageView i = new ImageView(this);
        /*CENTER /center  按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示
        CENTER_CROP / centerCrop  按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)
        CENTER_INSIDE / centerInside  将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽
        FIT_CENTER / fitCenter  把图片按比例扩大/缩小到View的宽度，居中显示
        FIT_END / fitEnd   把图片按比例扩大/缩小到View的宽度，显示在View的下部分位置
        FIT_START / fitStart  把图片按比例扩大/缩小到View的宽度，显示在View的上部分位置
        FIT_XY / fitXY  把图片不按比例扩大/缩小到View的大小显示
        MATRIX / matrix 用矩阵来绘制*/
        i.setBackgroundColor(0xff000000);
        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT));
        return i ;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {//判断图片左右滑动
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                //手指按下的X坐标
                downX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP:{
                float lastX = event.getX();
                //抬起的时候的X坐标大于按下的时候就显示上一张图片
                if(lastX > downX){
                    if(currentPosition > 0){
                        currentPosition -- ;
                    }else{
                        currentPosition=imgIds.length - 1;
                    }
                    mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_in));
                    mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_out));
                    mImageSwitcher.setImageResource(imgIds[currentPosition]);
                    // setImageBackground(currentPosition);
                }

                if(lastX < downX){
                    if(currentPosition < imgIds.length - 1){
                        currentPosition ++ ;
                    }else{
                        currentPosition=0;
                    }
                    mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_in));
                    mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_out));
                    mImageSwitcher.setImageResource(imgIds[currentPosition]);
                    // setImageBackground(currentPosition);
                }
            }

            break;
        }

        return true;
    }







    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = getIntent();
        final String name = intent.getStringExtra("p_name");
        final String username = intent.getStringExtra("p_account");
        final String password = intent.getStringExtra("p_password");
        final int personid = intent.getIntExtra("p_index", -1);
        final String sex = intent.getStringExtra("p_gender");
        final String email = intent.getStringExtra("p_email");
        final String address = intent.getStringExtra("p_address");
        if (id == R.id.nav_person) {
            // Handle the camera action

            Intent personintent = new Intent(drawer.this, PersonActivity.class);
            personintent.putExtra("p_name", name);
            personintent.putExtra("p_password", password);
            personintent.putExtra("p_account", username);
            personintent.putExtra("p_index", personid);
            personintent.putExtra("p_gender", sex);
            personintent.putExtra("p_email", email);
            personintent.putExtra("p_address", address);
            drawer.this.startActivity(personintent);
        } else if (id == R.id.nav_cbt) {
           // Intent cbtintent = new Intent(drawer.this, .class);
           // drawer.this.startActivity(cbtintent);

        } else if (id == R.id.nav_songlist) {
            Intent songintent = new Intent(drawer.this, PlayAudioExample.class);
            drawer.this.startActivity(songintent);

        } else if (id == R.id.nav_progress) {
           // Intent progressintent = new Intent(drawer.this, .class);
          //  drawer.this.startActivity(progressintent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
