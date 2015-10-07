package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by onculy on 30.07.2015.
 */
public class MealView extends LinearLayout
{
    String yemek1;
    String yemek2;
    String yemek3;
    String yemek4;
    int yemeksayisi;
    TextView yemekler[] = new TextView[4];
    public MealView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.TextBox,0,0);
        yemeksayisi = typedArray.getInt(R.styleable.TextBox_yemeksayisi, 4);
        String label = typedArray.getString(R.styleable.TextBox_label);
        yemek1 = typedArray.getString(R.styleable.TextBox_yemek1);
        yemek2 = typedArray.getString(R.styleable.TextBox_yemek2);
        yemek3 = typedArray.getString(R.styleable.TextBox_yemek3);
        yemek4 = typedArray.getString(R.styleable.TextBox_yemek4);
        String title = typedArray.getString(R.styleable.TextBox_meal_title);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mealview,this,true);

        TextView textView = (TextView)findViewById(R.id.textView_label);

        yemekler[0] = (TextView)findViewById(R.id.textView_meal1);
        yemekler[1] = (TextView)findViewById(R.id.textView_meal2);
        yemekler[2] = (TextView)findViewById(R.id.textView_meal3);
        yemekler[3] = (TextView)findViewById(R.id.textView_meal4);
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

    public void setYemek1(String txt)
    {
        yemek1 = txt;
        yemekler[0].setText(yemek1);
        invalidate();
    }

    public void setYemek2(String txt)
    {
        yemek2 = txt;
        yemekler[1].setText(yemek2);
        invalidate();
    }

    public void setYemek3(String txt)
    {
        yemek3 = txt;
        yemekler[2].setText(yemek3);
        invalidate();
    }

    public void setYemek4(String txt)
    {
        yemek4 = txt;
        yemekler[3].setText(yemek4);
        invalidate();
    }

    public void setYemeksayisi(int ys)
    {
        yemeksayisi = ys;
        for(int i=0; i<yemeksayisi; i++) {
            yemekler[i].setVisibility(VISIBLE);
        }
        for(int i=4; i>yemeksayisi; i--)
        {
            yemekler[i-1].setVisibility(GONE);
        }

        invalidate();
    }

    public void clickMealSelector(View view)
    {
        Intent intent=new Intent(getContext(),MealCalculator.class);
        getContext().startActivity(intent);
    }

    public MealView(Context context)
    {
        this(context, null);
    }
}
