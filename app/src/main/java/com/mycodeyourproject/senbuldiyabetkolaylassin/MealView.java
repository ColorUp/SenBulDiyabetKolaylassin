package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by onculy on 30.07.2015.
 */
public class MealView extends RelativeLayout{

    public MealView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mealview, this, true);
    }
}
