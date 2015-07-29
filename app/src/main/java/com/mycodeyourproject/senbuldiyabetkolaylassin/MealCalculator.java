package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MealCalculator extends BaseViaDiabetActivity
{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String[]>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_calculator);
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.listView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String[]>>();

        // Adding child data
        listDataHeader.add("Hamur İşleri");
        listDataHeader.add("Meyveler");
        listDataHeader.add("Salatalar");

        // Adding child data
        List<String[]> hamurIsleri = new ArrayList<String[]>();
        String[] ekmek={"Ekmek","Protein: 55 Kalori:33 Yağ:5"};
        hamurIsleri.add(ekmek);
        String[] pogaca={"Poğaça","Protein: 15 Kalori:33 Yağ:65"};
        hamurIsleri.add(pogaca);
        String[] simit={"Simit","Protein: 5 Kalori:133 Yağ:15"};
        hamurIsleri.add(simit);

        List<String[]> meyveler = new ArrayList<String[]>();
        String[] elma={"Elma","Protein: 52 Kalori:55 Yağ:12"};
        meyveler.add(elma);
        String[] muz={"Muz","Protein: 18 Kalori:23 Yağ: 7"};
        meyveler.add(muz);
        String[] cilek={"Çilek","Protein: 13 Kalori:19 Yağ:45"};
        meyveler.add(cilek);

        List<String[]> salatalar = new ArrayList<String[]>();
        String[] sezar={"Sezar Salata","Protein: 13 Kalori:19 Yağ:45"};
        salatalar.add(sezar);
        String[] mevsim={"Mevsim Salata","Protein: 13 Kalori:19 Yağ:45"};
        salatalar.add(mevsim);
        String[] coban={"Çoban Salata","Protein: 13 Kalori:19 Yağ:45"};
        salatalar.add(coban);

        listDataChild.put(listDataHeader.get(0), hamurIsleri);
        listDataChild.put(listDataHeader.get(1), meyveler);
        listDataChild.put(listDataHeader.get(2), salatalar);
    }
}

