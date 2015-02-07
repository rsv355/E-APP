package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.educationapp.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pixplicity.easyprefs.library.Prefs;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.List;


public class StartTestActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnStart;
    ProgressDialog dialog;
    private EditText etPassword;
    private EditText etTestid;
    private ListView listview;
    private TextView txtCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starttest);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

        etTestid = (EditText)findViewById(R.id.etTestid);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btnStart= (GeniusButton)findViewById(R.id.btnStart);

        if (toolbar != null) {
            toolbar.setTitle("Quick Quiz");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processTest();
            }
        });

    }


    private void processTest()
    {
        String testid = Prefs.getString("TestID", "");
        String password = Prefs.getString("Password", "");

        if (etTestid.getText().toString().trim().length() == 0)
        {
            etTestid.setError("Please Enter TestID !!!");
            return;
        }
        if (etPassword.getText().toString().trim().length() == 0)
        {
            etPassword.setError("Please Enter Password !!!");
            return;
        }
           if (etTestid.getText().toString().trim().equalsIgnoreCase(testid) && etPassword.getText().toString().trim().equals(password))
        {
            processStartCounter();

        } else {
            Toast.makeText(this, "Wrong Test id", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtCounter.setVisibility(View.GONE);
    }


    private void processStartCounter(){
        CountDownTimer countDownTimer;
        countDownTimer = new MyCountDownTimer(10000, 1000); // 1000 = 1s
        countDownTimer.start();
    }

 public class MyCountDownTimer extends CountDownTimer {

        protected AlphaAnimation fadeIn;
        protected AlphaAnimation fadeOut;

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            fetchQuestiondetails();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            txtCounter.setText(""+millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(StartTestActivity.this,QuestionActivity.class);
            startActivity(intent);
        }
    }


    private void fetchQuestiondetails()
    {
        String temp = Prefs.getString("TestID", "");
        ParseQuery parsequery = ParseQuery.getQuery("Question_details");
        parsequery.whereEqualTo("Test_id", temp);
        parsequery.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

                if (e == null) {
                    Log.e("size of question list", String.valueOf(parseObjects.size()));
                    for (int i = 0; i < parseObjects.size(); i++)
                    {
                        Log.e("quesiotn", String.valueOf(((ParseObject)parseObjects.get(i)).get("Question")));
                    }
                } else {
                    Log.e("size of exception", e.getMessage());
                }
            }

        });
    }

    //end of min class
}