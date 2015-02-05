package com.example.android.educationapp.ui.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.educationapp.R;
import com.parse.ParseObject;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseObject gameScore = new ParseObject("GameScore");
                gameScore.put("score", 1);
                gameScore.put("playerName", "Krishna");
                gameScore.put("cheatMode", false);
                gameScore.saveInBackground();


              /*  ParseUser.getCurrentUser().saveInBackground();
                ParseACL defaultACL = new ParseACL();
                setDefaultACL(defaultACL, true);

                ParseObject gameScore = new ParseObject("GameScore");
                gameScore.put("score", 1337);
                gameScore.put("playerName", "Sean Plott");
                gameScore.put("cheatMode", false);
                gameScore.saveInBackground();*/


                Toast.makeText(MainActivity.this, "Save object done", Toast.LENGTH_LONG).show();


              /*  ParseQuery<ParseObject> queryDate = ParseQuery.getQuery("currentdate");
                queryDate.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Random random = new Random();
                            int randomIntValue = random.nextInt(50);
                            scoreList.get(0).put("date","nirav"+randomIntValue);
                            scoreList.get(0).saveInBackground();
                            Date serverDate=scoreList.get(0).getUpdatedAt();
                            String serverDateString =inputFormat.format(serverDate);
                            Date currentDate=new Date();
                            String currentDateString =inputFormat.format(currentDate);
                            Log.e("serverDate",serverDateString+"");
                            Log.e("currentDate",currentDateString+"");
                            if(serverDateString.equals(currentDateString)){

                                ParseQuery<ParseObject> query = ParseQuery.getQuery("Quests");
                                query.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> scoreList, ParseException e) {
                                        if (e == null) {
                                            getRandomNumber(scoreList);
                                        } else {

                                        }
                                    }
                                });
                            } else {
                                txtQuest.setText("wrong date");
                            }

                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });*/

            }
        });



    }


}
