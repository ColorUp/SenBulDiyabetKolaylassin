package com.mycodeyourproject.senbuldiyabetkolaylassin;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Burak on 23.07.2015.
 */

public class TextBox extends LinearLayout
{
    public TextBox(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.TextBox,0,0);
        String label = typedArray.getString(R.styleable.TextBox_label);
        String text=typedArray.getString(R.styleable.TextBox_text);
        String hint=typedArray.getString(R.styleable.TextBox_hint);
        int inputType=typedArray.getInt(R.styleable.TextBox_android_inputType,0);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.textbox,this,true);

        TextView textView = (TextView)findViewById(R.id.textbox_textView);
        EditText editText=(EditText)findViewById(R.id.textbox_editText);
        textView.setText(label);
        editText.setText(text);
        editText.setHint(hint);
        editText.setInputType(inputType);
    }

    public TextBox(Context context)
    {
        this(context, null);
    }
}