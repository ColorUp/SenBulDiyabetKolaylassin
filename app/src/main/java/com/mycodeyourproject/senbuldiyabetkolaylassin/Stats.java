package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.Random;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);
        //mBarChart.setFixedBarWidth(true);

        ArrayList<BarValue> barValues = new ArrayList<>();
        float prev = 5f;
        Random random = new Random();
        for(int i=0; i<10; i++)
        {
            float newVal = prev + random.nextFloat();
            prev = newVal;
            barValues.add(new BarValue(newVal,""+i+".10", 0xFF123456));
            addNewBar(barValues.get(i), mBarChart);
        }

        mBarChart.startAnimation();

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Freetime", 15, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Sleep", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Work", 35, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));

        mPieChart.startAnimation();

        ValueLineChart mCubicValueLineChart = (ValueLineChart) findViewById(R.id.cubiclinechart);

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        series.addPoint(new ValueLinePoint("Jan", 2.4f));
        series.addPoint(new ValueLinePoint("Feb", 10f));
        series.addPoint(new ValueLinePoint("Mar", .4f));
        series.addPoint(new ValueLinePoint("Apr", 1.2f));
        series.addPoint(new ValueLinePoint("Mai", 2.6f));
        series.addPoint(new ValueLinePoint("Jun", 4.0f));
        series.addPoint(new ValueLinePoint("Jul", 3.5f));
        series.addPoint(new ValueLinePoint("Aug", 2.4f));
        series.addPoint(new ValueLinePoint("Sep", 2.4f));
        series.addPoint(new ValueLinePoint("Oct", 3.4f));
        series.addPoint(new ValueLinePoint("Nov", .4f));
        series.addPoint(new ValueLinePoint("Dec", 1.3f));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();


    }

    private void addNewBar(BarValue bv, BarChart bc)
    {
        BarModel bm = new BarModel(bv.value, bv.color);
        bm.setLegendLabel(bv.label);
        bc.addBar(bm);
    }

    private class BarValue{
        public float value;
        public String label;
        public int color;

        public BarValue(float value, String label, int color) {
            this.value = value;
            this.label = label;
            this.color = color;
        }
    }

    private void addNewSlice(PieSlice pieSlice, PieChart pieChart)
    {
        PieModel pieModel = new PieModel(pieSlice.label, pieSlice.value, pieSlice.color);
        pieChart.addPieSlice(pieModel);
    }

    private class PieSlice{
        public String label;
        public float value;
        public int color;

        public PieSlice(String label, float value, int color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }
    }

}
