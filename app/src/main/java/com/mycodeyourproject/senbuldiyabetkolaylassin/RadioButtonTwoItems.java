package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Burak on 23.07.2015.
 */
public class RadioButtonTwoItems extends LinearLayout
{
    public RadioButtonTwoItems(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.TextBox,0,0);
        String radioButtonTexts = typedArray.getString(R.styleable.TextBox_text);
        String label=typedArray.getString(R.styleable.TextBox_label);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.radio_button_two_items,this,true);

        TextView textView = (TextView)findViewById(R.id.radiobutton_textview);
        final RadioButton radioButton1=(RadioButton)findViewById(R.id.radiobutton_1);
        final RadioButton radioButton2=(RadioButton)findViewById(R.id.radiobutton_2);
        textView.setText(label);
        String delims = "[,]+";
        final String[] tokens = radioButtonTexts.split(delims);

        radioButton1.setText(tokens[0]);
        radioButton2.setText(tokens[1]);
    }

    public RadioButtonTwoItems(Context context)
    {
        this(context, null);
    }
}