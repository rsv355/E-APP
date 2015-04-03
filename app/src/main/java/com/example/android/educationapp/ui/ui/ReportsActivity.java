package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.DBAdapter;
import com.example.android.educationapp.ui.base.QuestionDetails;
import com.example.android.educationapp.ui.model.CurrentTest;

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
    lecho.lib.hellocharts.view.LineChartView chart;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    public List<PointValue> values;
    public static  ArrayList<CurrentTest> resultList=new ArrayList<CurrentTest>();
    DBAdapter db;


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
        chart = (lecho.lib.hellocharts.view.LineChartView)findViewById(R.id.chart);

    }

    void loaddata(){
        resultList = new ArrayList<CurrentTest>();
        db.open();
        Cursor c = db.getAllResult();
        if (c.moveToFirst()) {
            Display(c);
        }
        else {
            resultList = new ArrayList<CurrentTest>();
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

            resultList.add(newObj);
            c.moveToNext();
        }


         values = new ArrayList<PointValue>();

        // (3,6)  ==> 6 is the marks on x axis,,,,,,3 is the marks on y axis

        for(int i=0;i<resultList.size();i++){

            values.add(new PointValue(i, resultList.get(i).Correct_ques));

        }

    }

    @Override
    protected void onResume() {
        super.onResume();


        loaddata();

     /*   List<PointValue> values = new ArrayList<PointValue>();

        // (3,6)  ==> 6 is the marks on x axis,,,,,,3 is the marks on y axis

        for(int i=0;i<resultList.size();i++){

            values.add(new PointValue(i, resultList.get(i).Correct_ques));

        }
*/


/*
        values.add(new PointValue(0, 10));
        values.add(new PointValue(1, 15));
        values.add(new PointValue(2, 9));*/


        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setShape(ValueShape.CIRCLE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);



        LineChartData data = new LineChartData();
        data.setLines(lines);

    //    chart.setLineChartData(data);


        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Test ID");
                axisY.setName("Marks");


            }

            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);


    }

    //end of min class
}