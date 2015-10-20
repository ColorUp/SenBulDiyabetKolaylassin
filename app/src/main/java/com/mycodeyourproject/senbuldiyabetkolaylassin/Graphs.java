package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userValue = sharedPref.getString(getString(R.string.signeduser), "");

        //Veritabanından verilerin hepsi okunur
        List<Map<Object, Object>> userDataList = DataTransferObjects.UserDatalog.getUserDatalogList(userValue);

        List<Map<Object,Object>> yemekler= DataTransferObjects.UserMeal.getUserMealList(userValue);
        List<Map<Object,Object>> anaYemekListesi= DataTransferObjects.Meal.getMealList();

        float cal = 0f, prot= 0f, fat = 0f;
        if(yemekler!=null)
            if(yemekler.size()>0)
            {
                for (int i=0;i<yemekler.size();i++)
                {
                    String type = yemekler.get(i).get("TYPE").toString();
                    if(type.matches("1") || type.matches("2") || type.matches("3")){
                        for(int j= 0; j<anaYemekListesi.size(); j++)
                        {
                            if(anaYemekListesi.get(j).get("ID").toString().matches(yemekler.get(i).get("MEALID").toString()))
                            {
                                cal += Float.parseFloat(anaYemekListesi.get(j).get("CALORI").toString());
                                prot += Float.parseFloat(anaYemekListesi.get(j).get("PROTEIN").toString());
                                fat += Float.parseFloat(anaYemekListesi.get(j).get("FAT").toString());
                            }
                        }

                    }
                }
            }

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Günlük Kalori Tüketimi (kCal)", cal, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Günlük Protein Tüketimi (g)", prot, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Günlük Yağ Tüketimi (g)", fat, Color.parseColor("#CDA67F")));

        mPieChart.startAnimation();

        ValueLineChart mCubicValueLineChart = (ValueLineChart) findViewById(R.id.cubiclinechart);

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        for(int i=0; i<userDataList.size(); i++)
        {
            Map<Object, Object> row = userDataList.get(i);
            series.addPoint(new ValueLinePoint(Converter.StringDateTimeToStringDate(row.get("DATETIME").toString()),
                    Float.parseFloat(row.get("GLUCOSE").toString())));
        }



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
