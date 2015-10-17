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
        mBarChart.setContentDescription("Description");


        ArrayList<BarValue> barValues = new ArrayList<>();

        barValues.add(new BarValue(1310,"02.10.2015", 0xFF123456));
        barValues.add(new BarValue(1800,"03.10.2015", 0xFF123456));
        barValues.add(new BarValue(922,"04.10.2015", 0xFF123456));
        barValues.add(new BarValue(1296, "05.10.2015", 0xFF123456));
        barValues.add(new BarValue(1105, "06.10.2015", 0xFF123456));


        for(int i=0; i<barValues.size(); i++)
            addNewBar(barValues.get(i), mBarChart);

        BarChart mBarChart2 = (BarChart) findViewById(R.id.barchart2);
        mBarChart.setContentDescription("Description");

        ArrayList<BarValue> barValues2 = new ArrayList<>();

        barValues2.add(new BarValue(6,"02.10.2015", 0xFF123456));
        barValues2.add(new BarValue(2,"03.10.2015", 0xFF123456));
        barValues2.add(new BarValue(3,"04.10.2015", 0xFF123456));
        barValues2.add(new BarValue(0,"05.10.2015", 0xFF123456));
        barValues2.add(new BarValue(0, "06.10.2015", 0xFF123456));
        barValues2.add(new BarValue(4, "07.10.2015", 0xFF123456));


        for(int i=0; i<barValues2.size(); i++)
            addNewBar(barValues2.get(i), mBarChart2);


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
