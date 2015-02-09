package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.QuestionDetails;
import com.filippudak.ProgressPieView.ProgressPieView;

import java.util.ArrayList;


public class QuestionActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ListView listview;
    ProgressDialog dialog;
    net.qiujuer.genius.widget.GeniusButton btnNext;
    com.filippudak.ProgressPieView.ProgressPieView pieView;
    private ArrayList<QuestionDetails> Ques_det;
    int counter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        pieView = (com.filippudak.ProgressPieView.ProgressPieView) findViewById(R.id.progressPieViewXml);
        listview = (ListView) findViewById(R.id.listview);
        btnNext = (net.qiujuer.genius.widget.GeniusButton)findViewById(R.id.btnNext);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);


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

        viewdata(StartTestActivity.Ques_det);

        checkQuestionNo(counter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 counter+=1;
                if(counter>=StartTestActivity.Ques_det.size()){
                    Toast.makeText(QuestionActivity.this,"Questions are finished :)",Toast.LENGTH_LONG).show();
                }else {
                    checkQuestionNo(counter);
                }
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
                counter+=1;
                if(counter>=StartTestActivity.Ques_det.size()){
                    pieView.setTextSize(18);
                    pieView.setText("Time up");
                    Toast.makeText(QuestionActivity.this,"Questions are finished :)",Toast.LENGTH_LONG).show();
                }else {
                    checkQuestionNo(counter);
                }

            }
        });


    }


    public void checkQuestionNo(final int qno ){
        ArrayList<QuestionDetails> newObj = new ArrayList<QuestionDetails>(1);
        newObj.add(StartTestActivity.Ques_det.get(counter));
        viewdata(newObj);
    }

    @Override
    protected void onResume() {
        super.onResume();
        counter=0;
    }

    public void viewdata(ArrayList<QuestionDetails> Ques_detlobjects) {

pieView.setProgress(0);
pieView.setMax(350);
pieView.animateProgressFill();
        Myadapter adapter = new Myadapter(QuestionActivity.this,Ques_detlobjects,counter);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

   public class Myadapter extends BaseAdapter{

         Context context;
         int layoutResourceId;
         ArrayList<QuestionDetails> values;

         public Myadapter(Context context,ArrayList<QuestionDetails> objects,final int counter) {
             this.context = context;
             this.values=objects;

             Log.e("inside", "adapter");
         }


       @Override
       public int getCount() {
           return values.size();
       }

       @Override
       public Object getItem(int position) {
           return null;
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
         public View getView(final int position, View convertView, ViewGroup parent) {
             if(convertView == null){
                 convertView = QuestionActivity.this.getLayoutInflater().inflate(R.layout.items_question_activity,null);
             }

           TextView txtno = (TextView) convertView.findViewById(R.id.txtQno);

             TextView txtQuestion = (TextView) convertView.findViewById(R.id.txtQuestion);
             TextView txtOptA = (TextView) convertView.findViewById(R.id.txtOptA);
             TextView txtOptB = (TextView) convertView.findViewById(R.id.txtOptB);
             TextView txtOptC = (TextView) convertView.findViewById(R.id.txtOptC);
             TextView txtOptD = (TextView) convertView.findViewById(R.id.txtOptD);

           txtno.setText(String.valueOf(counter+1));
           txtQuestion.setText(values.get(position).Question);
           txtOptA.setText("   "+values.get(position).optA);
           txtOptB.setText("   "+values.get(position).optB);
           txtOptC.setText("   "+values.get(position).optC);
           txtOptD.setText("   "+values.get(position).optD);
             //Log.e("value", String.valueOf(values.get(position).getString("playerName")));

             return  convertView;
         }


     }
/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.getInBackground("yKD4lpJRzG", new GetCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> scoreList, com.parse.ParseException e) {

                if (e == null) {
                    // object will be your game score
                  //  String playerName = parseObject.getString("playerName");
                    Log.e("size of list",String.valueOf(scoreList.size()));
                } else {
                    Log.e("player name","no data err");
                    Log.e("exc",e.toString());
                    // something went wrong
                }


            }
        });*/





    //end of min class
}