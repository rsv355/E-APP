package com.example.android.educationapp.ui.ui;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.CircleMenuLayout;
import com.example.android.educationapp.ui.base.MyDrawerActivity;
import com.example.android.educationapp.ui.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {

     FrameLayout fram_lay;

    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts = new String[] { "Item 1 ", "Item 2", "Item 3",
            "Item 4", "Item 5", "Item 6" };
    private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
            R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
            R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
            R.drawable.home_mbank_6_normal };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

         final View contentView = findViewById(R.id.fullscreen_content);
        fram_lay =(FrameLayout) findViewById(R.id.fram_lay);

        StartAnimations();


        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer(3000, 1000); // 1000 = 1s
        countDownTimer.start();

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
        {

            @Override
            public void itemClick(View view, int pos)
            {
                Toast.makeText(SplashActivity.this, mItemTexts[pos],
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void itemCenterClick(View view)
            {
               /* Toast.makeText(SplashActivity.this,
                        "you can do something just like ccb  ",
                        Toast.LENGTH_SHORT).show();*/
                Intent i = new Intent(SplashActivity.this,ToDoListActivity.class);
                startActivity(i);

            }
        });

    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l=(RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
         anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView tx = (TextView)findViewById(R.id.fullscreen_content);
        TextView download = (TextView)findViewById(R.id.downloading);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/waltograph.ttf");
        tx.setTypeface(custom_font);
        download.setTypeface(custom_font);
        tx.setText("Quick Quiz");
        tx.clearAnimation();
        tx.startAnimation(anim);



    }

    private void StartAnimations2() {


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate2);
        anim.reset();
        TextView tx1 = (TextView)findViewById(R.id.fullscreen_content);
        tx1.setVisibility(View.GONE);

        TextView download = (TextView)findViewById(R.id.downloading);
        download.setVisibility(View.GONE);


        TextView tx = (TextView)findViewById(R.id.fullscreen_content2);
        tx.setVisibility(View.VISIBLE);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/waltograph.ttf");
        tx.setTypeface(custom_font);
        tx.setText("Quick Quiz");
        tx.clearAnimation();
        tx.startAnimation(anim);


        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer2(2100, 1000); // 1000 = 1s
        countDownTimer.start();


    }

    public class MyCountDownTimer2 extends CountDownTimer {

        public MyCountDownTimer2(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            Log.e("counter", "Time's up!");
            fram_lay.setVisibility(View.VISIBLE);
            //  getActivity().finish();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction ft = manager.beginTransaction();
//            ft.setCustomAnimations(R.anim.entry, R.anim.exit,R.anim.entry, R.anim.exit);
//            ft.replace(R.id.payment_fragment, new FragmentHome(), "payment_home");
//            ft.commit();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }
    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            Log.e("counter", "Time's up!");
            StartAnimations2();

        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }




}
