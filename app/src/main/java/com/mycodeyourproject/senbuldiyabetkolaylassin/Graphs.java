package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Yemek", 2, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Uyku", 8, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Ara Öğün", 2, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Spor", 1, Color.parseColor("#FED70E")));
        mPieChart.addPieSlice(new PieModel("İş Güç", 10, Color.parseColor("#9fa8da")));
        mPieChart.addPieSlice(new PieModel("Boş Vakit", 3, Color.parseColor("#ffff00")));

        mPieChart.startAnimation();

        ValueLineChart mCubicValueLineChart = (ValueLineChart) findViewById(R.id.cubiclinechart);

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        series.addPoint(new ValueLinePoint("07:00", 152f));
        series.addPoint(new ValueLinePoint("10:00", 201f));
        series.addPoint(new ValueLinePoint("12:00", 188f));
        series.addPoint(new ValueLinePoint("15:00", 145f));
        series.addPoint(new ValueLinePoint("18:00", 115f));
        series.addPoint(new ValueLinePoint("20:00", 265f));
        series.addPoint(new ValueLinePoint("23:00", 105f));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graphs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
