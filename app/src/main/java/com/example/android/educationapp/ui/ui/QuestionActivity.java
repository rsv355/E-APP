package com.example.android.educationapp.ui.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.DBAdapter;
import com.example.android.educationapp.ui.base.QuestionDetails;
import com.example.android.educationapp.ui.model.CurrentTest;
import com.filippudak.ProgressPieView.ProgressPieView;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class QuestionActivity extends ActionBarActivity {
    private Toolbar toolbar;
    ProgressDialog dialog;
    ImageView img;
    net.qiujuer.genius.widget.GeniusButton btnNext;
    com.filippudak.ProgressPieView.ProgressPieView pieView;
    private ArrayList<QuestionDetails> Ques_det;
    int counter=0;
    net.qiujuer.genius.widget.GeniusCheckBox optA,optB,optC,optD;
    int time_text,time_image,time_audio;
    int finaltime=10;
    public static HashMap<Integer,String> optionlist = new HashMap<Integer,String>();
    public static ArrayList<String> selectoptionlist=new ArrayList<String>();
    TextView txtQuestion,txtOptA,txtOptB,txtOptC,txtOptD,txtno;
    String selectedOption="NA";
    DBAdapter db;
    boolean isTestComplete =false;
    CurrentTest obj;


    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionActivity.this);
        alertDialog.setTitle("Warning");

        // Setting Dialog Message
        alertDialog.setMessage("Would you like to cancel this test ?\nCurrent test data will be lost!!!");

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                Intent i = new Intent(QuestionActivity.this,StartTestActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);


            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

            }
        });

        // Showing Alert Message
        alertDialog.show();




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        db= new DBAdapter(QuestionActivity.this);
        obj = new CurrentTest();


        pieView = (com.filippudak.ProgressPieView.ProgressPieView) findViewById(R.id.progressPieViewXml);
        btnNext = (net.qiujuer.genius.widget.GeniusButton)findViewById(R.id.btnNext);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

        txtno = (TextView) findViewById(R.id.txtQno);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtOptA = (TextView) findViewById(R.id.txtOptA);
        txtOptB = (TextView) findViewById(R.id.txtOptB);
        txtOptC = (TextView) findViewById(R.id.txtOptC);
        txtOptD = (TextView) findViewById(R.id.txtOptD);
        optA = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.a);
        optB = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.b);
        optC = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.c);
        optD = (net.qiujuer.genius.widget.GeniusCheckBox) findViewById(R.id.d);


        if (toolbar != null) {
            toolbar.setTitle("Quick Quiz");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionActivity.this);
                alertDialog.setTitle("Warning");

                // Setting Dialog Message
                alertDialog.setMessage("Would you like to cancel this test ?\nCurrent test data will be lost!!!");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        Intent i = new Intent(QuestionActivity.this,StartTestActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);


                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event

                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });


        optionlist = new HashMap<Integer,String>();
        setupQuestion(counter);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(optionlist.get(counter)==null){
                    optionlist.put(counter, "NA");
                }


                counter+=1;
                if(counter>=StartTestActivity.Ques_det.size()){
                    Toast.makeText(QuestionActivity.this,"Please wait for Questions to finished",Toast.LENGTH_LONG).show();

                }else {
                        setupQuestion(counter);
                }

            }
        });

        optA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedOption="A";
                optB.setChecked(false);
                optC.setChecked(false);
                optD.setChecked(false);
                optionlist.put(counter, "A");
            }
        });

        optB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedOption="B";
                optA.setChecked(false);
                optC.setChecked(false);
                optD.setChecked(false);
                optionlist.put(counter, "B");
            }
        });
        optC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedOption="C";
                optB.setChecked(false);
                optA.setChecked(false);
                optD.setChecked(false);
                optionlist.put(counter, "C");
            }
        });
        optD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedOption="D";
                optB.setChecked(false);
                optC.setChecked(false);
                optA.setChecked(false);
                optionlist.put(counter, "D");
            }
        });

        pieView.setOnProgressListener(new ProgressPieView.OnProgressListener() {
            @Override
            public void onProgressChanged(int progress, int max) {
                int counter = progress;
                if(progress%35==0){
                    pieView.setTextSize(28);
                    pieView.setShowText(true);
                    pieView.setText(String.valueOf(progress/35));
                }

            }

            @Override
            public void onProgressCompleted() {

                if(optionlist.get(counter)==null){
                    optionlist.put(counter, "NA");
                }


                counter+=1;
                if(counter>=StartTestActivity.Ques_det.size()){
                     processFinish();
                }else {
                    setupQuestion(counter);
                }
            }
        });





    }




    @Override
    protected void onResume() {
        super.onResume();
        counter=0;
    }


    private void processInsertinDatabase(){
        int total_answerd=0;
        int total_Unanswerd=0;
        int total_correct_answer=0;
        int total_wrong_answer=0;

        for(int i=0;i<optionlist.size();i++){
            Log.e("Values:-",optionlist.get(i));
        }

        for(int i=0;i<StartTestActivity.Ques_det.size();i++){
  /*   Log.e("corect answer "+i,""+StartTestActivity.Ques_det.get(i).Correct_opt);
     Log.e("selection value "+i,""+selectoption.get(i));
*/
            if(optionlist.get(i).equalsIgnoreCase("NA")){

                total_Unanswerd++;
                total_wrong_answer++;
            }
            else{
                total_answerd++;
                if(optionlist.get(i).equalsIgnoreCase(StartTestActivity.Ques_det.get(i).Correct_opt)){
                    total_correct_answer++;
                }
                else{
                    total_wrong_answer++;
                }
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        Date date1 = new Date();
        String cuurentdate = ""+dateFormat.format(date1);

        if(total_correct_answer>=total_wrong_answer) {

            obj.setValues(StartTestActivity.Ques_det.get(0).Test_id,
                    cuurentdate,
                    "PASS",
                    StartTestActivity.Ques_det.size(),
                    total_correct_answer,
                    total_wrong_answer,
                    total_answerd,
                    total_Unanswerd);
        }
        else{
            obj.setValues(StartTestActivity.Ques_det.get(0).Test_id,
                    cuurentdate,
                    "FAIL",
                    StartTestActivity.Ques_det.size(),
                    total_correct_answer,
                    total_wrong_answer,
                    total_answerd,
                    total_Unanswerd);
        }



        db.open();
        db.insertRecord(String.valueOf(obj.Test_id),
                String.valueOf(obj.Test_date),
                String.valueOf(obj.Result),
                String.valueOf(obj.Total_ques),
                String.valueOf(obj.Correct_ques),
                String.valueOf(obj.Wrong_ques),
                String.valueOf(obj.Answered),
                String.valueOf(obj.UnAnswered));
        db.close();

    }


    private void processFinish(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuestionActivity.this);
        alertDialog.setTitle("Quic Quiz");
        alertDialog.setCancelable(false);

        // Setting Dialog Message
        alertDialog.setMessage("Test is Complete");

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {


                processInsertinDatabase();


                Intent i = new Intent(QuestionActivity.this,FinishActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();


            }
        });



        // Showing Alert Message
        alertDialog.show();
    }


    private void setupQuestion(int counter){


        pieView.setProgress(0);
        pieView.setMax(finaltime*35);
        pieView.animateProgressFill();

        txtQuestion.setText(StartTestActivity.Ques_det.get(counter).Question.trim());
        txtno.setText(""+(counter+1));
        txtOptA.setText(StartTestActivity.Ques_det.get(counter).optA.trim());
        txtOptB.setText(StartTestActivity.Ques_det.get(counter).optB.trim());
        txtOptC.setText(StartTestActivity.Ques_det.get(counter).optC.trim());
        txtOptD.setText(StartTestActivity.Ques_det.get(counter).optD.trim());

        optA.setChecked(false);
        optB.setChecked(false);
        optC.setChecked(false);
        optD.setChecked(false);


    }





    //end of min class
}