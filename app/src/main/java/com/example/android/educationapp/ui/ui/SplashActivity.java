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
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    FrameLayout fram_lay;
    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

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

       /* TextView tx = (TextView)findViewById(R.id.fullscreen_content);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/font name.ttf");
        tx.setTypeface(custom_font);
        tx.setText("Education \nApp1");*/
      //  final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);
        fram_lay =(FrameLayout) findViewById(R.id.fram_lay);
        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                             //   mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                           /* controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);*/
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            //controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
      //  findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

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
                Toast.makeText(SplashActivity.this,
                        "you can do something just like ccb  ",
                        Toast.LENGTH_SHORT).show();

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

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/waltograph.ttf");
        tx.setTypeface(custom_font);
        tx.setText("Quick Quiz");
        tx.clearAnimation();
        tx.startAnimation(anim);




    }

    private void StartAnimations2() {


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate2);
        anim.reset();
        TextView tx1 = (TextView)findViewById(R.id.fullscreen_content);
        tx1.setVisibility(View.GONE);

        TextView tx = (TextView)findViewById(R.id.fullscreen_content2);
        tx.setVisibility(View.VISIBLE);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/waltograph.ttf");
        tx.setTypeface(custom_font);
        tx.setText("Quick Quiz");
        tx.clearAnimation();
        tx.startAnimation(anim);


        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer2(2000, 1000); // 1000 = 1s
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Intent i = new Intent(SplashActivity.this,MyDrawerActivity.class);
            startActivity(i);
            finish();
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
