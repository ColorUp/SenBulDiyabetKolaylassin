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
        int yemeksayisi = typedArray.getInt(R.styleable.TextBox_yemeksayisi, 4);
        String label = typedArray.getString(R.styleable.TextBox_label);
        String yemek1 = typedArray.getString(R.styleable.TextBox_yemek1);
        String yemek2 = typedArray.getString(R.styleable.TextBox_yemek2);
        String yemek3 = typedArray.getString(R.styleable.TextBox_yemek3);
        String yemek4 = typedArray.getString(R.styleable.TextBox_yemek4);
        String title = typedArray.getString(R.styleable.TextBox_meal_title);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mealview,this,true);

        TextView textView = (TextView)findViewById(R.id.textView18);
        TextView yemekler[] = new TextView[4];
        yemekler[0] = (TextView)findViewById(R.id.textView19);
        yemekler[1] = (TextView)findViewById(R.id.textView20);
        yemekler[2] = (TextView)findViewById(R.id.textView21);
        yemekler[3] = (TextView)findViewById(R.id.textView22);
        TextView meal_title = (TextView)findViewById(R.id.meal_title);
        textView.setText(label);
        yemekler[0].setText(yemek1);
        yemekler[1].setText(yemek2);
        yemekler[2].setText(yemek3);
        yemekler[3].setText(yemek4);
        meal_title.setText(title);

        for(int i=4; i>yemeksayisi; i--)
        {
            yemekler[i-1].setVisibility(GONE);
        }

    }

    public MealView(Context context)
    {
        this(context, null);
    }
}
