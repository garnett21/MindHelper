package com.example.mindhelper.mindhelper;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;


public class Mainmanu extends Activity implements ViewFactory, OnTouchListener {
    /**
     * ImagaSwitcher 的引用
     */
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
        setContentView(R.layout.activity_mainmenu);

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setOnClickListener(new TextView.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
// TODO Auto-generated method stub


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
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
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

    /**
     * 设置选中的tip的背景
     * @param selectItems
     */
    /**
     * private void setImageBackground(int selectItems){
     * for(int i=0; i<tips.length; i++){
     * if(i == selectItems){
     * tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
     * }else{
     * tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
     * }
     * }
     * }
     */

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
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
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





}
