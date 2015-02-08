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

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.QuestionDetails;

import java.util.ArrayList;


public class QuestionActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ListView listview;
    ProgressDialog dialog;
    private ArrayList<QuestionDetails> Ques_det;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        listview = (ListView) findViewById(R.id.listview);

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

        viewdata();





    }




    public void viewdata() {
        Myadapter adapter = new Myadapter(QuestionActivity.this,StartTestActivity.Ques_det);
        listview.setAdapter(adapter);
    }

   public class Myadapter extends BaseAdapter{

         Context context;
         int layoutResourceId;
         ArrayList<QuestionDetails> values;

         public Myadapter(Context context,ArrayList<QuestionDetails> objects) {
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
                 convertView = QuestionActivity.this.getLayoutInflater().inflate(R.layout.question_activity,null);
             }

             TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
             TextView txtTitle2 = (TextView) convertView.findViewById(R.id.txtTitle2);

             txtTitle.setText(values.get(position).Question);
             txtTitle2.setText("   "+values.get(position).Correct_opt);
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