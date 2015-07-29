package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

/**
 * Created by Burak on 28.07.2015.
 */
public class MealCalculator extends BaseViaDiabetActivity
{
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createData();
        setContentView(R.layout.activity_meal_calculator);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter(this,
                groups);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 5; j++) {
            Group group = new Group("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }
    }
}


