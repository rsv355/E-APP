package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.QuestionDetails;

import net.qiujuer.genius.widget.GeniusButton;
import net.qiujuer.genius.widget.GeniusEditText;

import java.util.ArrayList;


public class FeedbackActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnSend;
    ProgressDialog dialog;
    public static int i;
    private EditText etPassword;
    private EditText etTestid;
    private ListView listview;
    private TextView txtCounter;
    private QuestionDetails ques_d;
    public static ArrayList<QuestionDetails> Ques_det;
    int tempParseSize=0;
    GeniusEditText etName,etEmail,etFeedback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("Feedback");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSendMail();
            }
        });
    }

 void processSendMail(){

 }


void initView(){
    btnSend = (GeniusButton)findViewById(R.id.btnSend);
    etName = (GeniusEditText)findViewById(R.id.etName);
    etEmail = (GeniusEditText)findViewById(R.id.etEmail);
    etFeedback = (GeniusEditText)findViewById(R.id.etFeedback);
}

    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}