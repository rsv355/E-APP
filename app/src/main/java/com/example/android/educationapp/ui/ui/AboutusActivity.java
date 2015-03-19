package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.QuestionDetails;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;


public class AboutusActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnStart;
    ProgressDialog dialog;
    public static int i;
    private EditText etPassword;
    private EditText etTestid;
    private ListView listview;
    private TextView txtCounter;
    private QuestionDetails ques_d;
    public static ArrayList<QuestionDetails> Ques_det;
    int tempParseSize=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("About Us");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        TextView txt1 = (TextView)findViewById(R.id.txt1);



        txt1.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        txt1.setSelected(true);


        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        txt1.clearAnimation();
        txt1.startAnimation(anim);
    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}