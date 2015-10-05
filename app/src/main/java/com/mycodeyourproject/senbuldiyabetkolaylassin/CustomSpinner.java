package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Burak on 10.09.2015.
 */
public class CustomSpinner extends LinearLayout {
    /**************
     * Intialize Variables
     *************/
    public ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
    CustomAdapter adapter;
    CustomSpinner spinner = null;
    Spinner SpinnerSample=null;
    String text="";

    public CustomSpinner(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TextBox, 0, 0);
        String label = typedArray.getString(R.styleable.TextBox_label);
        text=typedArray.getString(R.styleable.TextBox_text);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_custom_spinner, this, true);

        TextView textView = (TextView)findViewById(R.id.custom_spinner_textView);
        SpinnerSample = (Spinner) findViewById(R.id.spinner);

        textView.setText(label);
        SpinnerSample.setPrompt(text);

        // Set data in arraylist
        setListData();

        // Create custom adapter object ( see below CustomAdapter.java )
        adapter = new CustomAdapter(getContext(), R.layout.spinner_rows, CustomListViewValuesArr, inflater);

        // Set adapter to spinner
        SpinnerSample.setAdapter(adapter);

        // Listener called when spinner item selected
        SpinnerSample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                SpinnerSample.setPrompt(text); //Default text atar
            }

        });
    }

    public CustomSpinner(Context context)
    {
        this(context, null);
    }

    /******
     * Function to set data in ArrayList
     *************/
    public void setListData() {

        // Now i have taken static values by loop.
        // For further inhancement we can take data by webservice / json / xml;

        for (int i = 0; i < 11; i++) {

            final SpinnerModel sched = new SpinnerModel();

            /******* Firstly take data in model object ******/
            sched.setCompanyName("Company " + i);
            sched.setImage("image1");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }
    }
}