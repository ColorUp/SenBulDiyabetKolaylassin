package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.xml.transform.Source;

public class ImageTextBox extends LinearLayout
{
    public ImageTextBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.TextBox,0,0);
        Drawable src=typedArray.getDrawable(R.styleable.TextBox_android_src);
        String label = typedArray.getString(R.styleable.TextBox_label);
        String text=typedArray.getString(R.styleable.TextBox_text);
        String unit=typedArray.getString(R.styleable.TextBox_unit);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_textbox,this,true);

        ImageView imageView = (ImageView)findViewById(R.id.imagetextbox_image);
        TextView textView = (TextView)findViewById(R.id.imagetextbox_label);
        EditText editText=(EditText)findViewById(R.id.imagetextbox_text);
        TextView textViewUnit = (TextView)findViewById(R.id.imagetextbox_unit);
        imageView.setImageDrawable(src);
        textView.setText(label);
        editText.setText(text);
        textViewUnit.setText(unit);
    }

    public ImageTextBox(Context context)
    {
        this(context, null);
    }
}