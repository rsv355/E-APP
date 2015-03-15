package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.example.android.educationapp.ui.base.QuestionDetails;
import com.filippudak.ProgressPieView.ProgressPieView;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class QuestionActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ListView listview;
    ProgressDialog dialog;
    ImageView img;
    net.qiujuer.genius.widget.GeniusButton btnNext;
    com.filippudak.ProgressPieView.ProgressPieView pieView;
    private ArrayList<QuestionDetails> Ques_det;
    int counter=0;
    net.qiujuer.genius.widget.GeniusCheckBox optA,optB,optC,optD;
    int time_text,time_image,time_audio;
    int finaltime=10;

    String selectedOption="NA";

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
                    Intent i = new Intent(QuestionActivity.this,FinishActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    checkQuestionNo(counter);
                }

            }
        });


        time_text  =  Integer.valueOf(Prefs.getString("Time_text",""));
        time_image =  Integer.valueOf(Prefs.getString("Time_image", ""));
        time_audio =  Integer.valueOf(Prefs.getString("Time_audio", ""));


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

   /* pieView.setProgress(0);
    pieView.setMax(350);
    pieView.animateProgressFill();
*/
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

             img = (ImageView)convertView.findViewById(R.id.img);

             TextView txtQuestion = (TextView) convertView.findViewById(R.id.txtQuestion);
             TextView txtOptA = (TextView) convertView.findViewById(R.id.txtOptA);
             TextView txtOptB = (TextView) convertView.findViewById(R.id.txtOptB);
             TextView txtOptC = (TextView) convertView.findViewById(R.id.txtOptC);
             TextView txtOptD = (TextView) convertView.findViewById(R.id.txtOptD);


           optA = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.a);
           optB = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.b);
           optC = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.c);
           optD = (net.qiujuer.genius.widget.GeniusCheckBox)convertView. findViewById(R.id.d);



           txtno.setText(String.valueOf(counter+1));
           txtQuestion.setText(values.get(position).Question);
           txtOptA.setText("   "+values.get(position).optA);
           txtOptB.setText("   "+values.get(position).optB);
           txtOptC.setText("   "+values.get(position).optC);
           txtOptD.setText("   "+values.get(position).optD);
             //Log.e("value", String.valueOf(values.get(position).getString("playerName")));



           optA.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="A";
                   optB.setChecked(false);
                   optC.setChecked(false);
                   optD.setChecked(false);

               }
           });

           optB.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="B";
                   optA.setChecked(false);
                   optC.setChecked(false);
                   optD.setChecked(false);

               }
           });

           optC.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="C";
                   optB.setChecked(false);
                   optA.setChecked(false);
                   optD.setChecked(false);

               }
           });

           optD.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedOption="D";
                   optB.setChecked(false);
                   optC.setChecked(false);
                   optA.setChecked(false);

               }
           });

            Prefs.putString("option" + position, selectedOption);


           if(values.get(position).Q_type.equalsIgnoreCase("text")){
               finaltime=time_text;
           }
           else if(values.get(position).Q_type.equalsIgnoreCase("image")){
                //setting the image for image questions
               if(values.get(position).Q_image_url == null) {
                   img.setVisibility(View.GONE);
               }
               else{
                   img.setVisibility(View.VISIBLE);
                   try {

                       Log.e("full path", String.valueOf(values.get(position).Q_image_url));
                       Picasso.with(QuestionActivity.this.getBaseContext()).load(values.get(position).Q_image_url).priority(Picasso.Priority.HIGH).into(img,new Callback(){

                           @Override
                           public void onSuccess() {
                               finaltime=time_image;
                               Log.e("Piccaso","image loded sucesfullY");
                           }

                           @Override
                           public void onError() {

                           }
                       });


                   } catch (Exception e) {
                       Log.e("Execpeti loading profile", e.toString());
                   }
               }

           }
           else if(values.get(position).Q_type.equalsIgnoreCase("audio")){
               finaltime=time_audio;
           }


           pieView.setProgress(0);
           pieView.setMax(finaltime*35);
           pieView.animateProgressFill();




             return  convertView;
         }


     }


    //end of min class
}