package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.QuestionMaster;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class QuestionActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ListView listview;
    ProgressDialog dialog;

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

        dialog = ProgressDialog.show(QuestionActivity.this,"Please Wait","Fetching data from server..",true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

                dialog.dismiss();

                if (e == null) {
                    Log.e("size of list", String.valueOf(parseObjects.size()));


                    Myadapter adapter = new Myadapter(QuestionActivity.this, parseObjects);
                    listview.setAdapter(adapter);
                } else {
                    Log.e("size of exception", e.getMessage());
                }
            }

        });
    }

   public class Myadapter extends BaseAdapter{

         Context context;
         int layoutResourceId;
         List<ParseObject> values;

         public Myadapter(Context context,List<ParseObject> objects) {
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

             Log.e("inside", "getview");

             if(convertView == null){
                 convertView = QuestionActivity.this.getLayoutInflater().inflate(R.layout.item_pickup_points,null);
             }

             TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
             txtTitle.setText(values.get(position).getString("playerName"));
             Log.e("value", String.valueOf(values.get(position).getString("playerName")));

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