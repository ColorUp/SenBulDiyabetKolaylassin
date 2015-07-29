package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by onculy on 30.07.2015.
 */
public class MealView extends LinearLayout
{
    public MealView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.TextBox,0,0);
        String label = typedArray.getString(R.styleable.TextBox_label);
        String yemek1 = typedArray.getString(R.styleable.TextBox_yemek1);
        String yemek2 = typedArray.getString(R.styleable.TextBox_yemek2);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mealview,this,true);

        TextView textView = (TextView)findViewById(R.id.textView18);
        TextView yemekyemek1 = (TextView)findViewById(R.id.textView19);
        TextView yemekyemek2 = (TextView)findViewById(R.id.textView20);
        textView.setText(label);
        yemekyemek1.setText(yemek1);
        yemekyemek2.setText(yemek2);
    }

    public MealView(Context context)
    {
        this(context, null);
    }
}
