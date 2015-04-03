package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.DBAdapter;
import com.example.android.educationapp.ui.base.QuestionDetails;
import com.example.android.educationapp.ui.model.CurrentTest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ValueFormatter;

import net.qiujuer.genius.widget.GeniusButton;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;


public class ReportsActivity extends ActionBarActivity {


    public List<PointValue> values;
      DBAdapter db;

    protected BarChart mChart;
    private ArrayList<CurrentTest> tests ;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        db= new DBAdapter(ReportsActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("Reports");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mChart = (BarChart) findViewById(R.id.chart1);

        mChart.setDrawValuesForWholeStack(true);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(false);
        mChart.setDescription("");
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        //  mChart.setDrawBarShadow(true);
        //   mChart.setDrawXLabels(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setTextSize(18f);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);


      //  ValueFormatter custom = new MyValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();

        leftAxis.setLabelCount(8);
    //    leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        rightAxis.setLabelCount(8);
  //      rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);




        new fetchLevels().execute();

    }


    private void updatePreferences() {

       // String color = Prefs.with(TestListActivity.this).getString("back", "#494949");
//        globalImageview.setBackgroundColor(Color.parseColor("#494949"));
    }


    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            //xVals.add(mMonths[i % 12]);
            float percentage = 100;
            float val = tests.get(i).Correct_ques * percentage;
            float val2 = val/tests.get(i).Total_ques;


             xVals.add(String.format(tests.get(i).Test_id));



        }
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
          // Test test = tests.get(i);
            float percentage = 100;

            float val = tests.get(i).Correct_ques * percentage;
            float val2 = val/tests.get(i).Total_ques;

                if(val2==0){

                    yVals1.add(new BarEntry(-10, i));
                }else {
                    yVals1.add(new BarEntry(val2, i));
                }


        }

        BarDataSet set1 = new BarDataSet(yVals1, "Tests");
        set1.setBarSpacePercent(40f);


        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
//        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(10f);
        mChart.setData(data);

    }


    void loaddata(){
        tests = new ArrayList<CurrentTest>();
        db.open();
        Cursor c = db.getAllResult();
        if (c.moveToFirst()) {
            Display(c);
        }
        else {
            tests = new ArrayList<CurrentTest>();
        }
        c.close();
        db.close();



    }


    private void Display(Cursor c) {
        c.moveToFirst();
        while (!c.isAfterLast()) {
            CurrentTest newObj = new CurrentTest();
            //     Toast.makeText(ResultActivity.this,""+ c.getString(c.getColumnIndex("Test_date")),Toast.LENGTH_SHORT).show();
            newObj.Test_id = c.getString(c.getColumnIndex("Test_id"));
            newObj.Test_date = c.getString(c.getColumnIndex("Test_date"));
            newObj.Result = c.getString(c.getColumnIndex("Result"));
            newObj.Total_ques = c.getInt(c.getColumnIndex("Total_ques"));
            newObj.Answered = c.getInt(c.getColumnIndex("Answered"));
            newObj.UnAnswered = c.getInt(c.getColumnIndex("UnAnswered"));
            newObj.Correct_ques = c.getInt(c.getColumnIndex("Correct_ques"));
            newObj.Wrong_ques = c.getInt(c.getColumnIndex("Wrong_ques"));

            tests.add(newObj);
            c.moveToNext();
        }




        // (3,6)  ==> 6 is the marks on x axis,,,,,,3 is the marks on y axis



    }

    private class fetchLevels extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loaddata();
            fillTests();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();



        updatePreferences();


    }

    private void fillTests() {

        if(tests == null || tests.isEmpty()){
           /* setData(3, 100);
            mChart.animateXY(1500, 1500);*/
        }else{
            setData(tests.size(), 100);
            mChart.animateXY(1500, 1500);
        }

        //  TestAdapter adapter = new TestAdapter(TestListActivity.this,tests);
        //  lvTests.setAdapter(adapter);

    }


    //end of min class
}