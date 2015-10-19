package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.xml.transform.Source;

public class ImageTextBox extends LinearLayout
{
    public ImageTextBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TextBox, 0, 0);
        Drawable src=typedArray.getDrawable(R.styleable.TextBox_android_src);
        Integer srcButtonInteger=typedArray.getInteger(R.styleable.TextBox_button_image, 0);
        String hint=typedArray.getString(R.styleable.TextBox_hint);
        int inputType=typedArray.getInt(R.styleable.TextBox_android_inputType, 0);

        Drawable srcButton=getResources().getDrawable(R.drawable.buttonstyle);

        if(srcButtonInteger==0)
        srcButton=getResources().getDrawable(R.drawable.buttonstyle);
        else if(srcButtonInteger==1)
            srcButton=getResources().getDrawable(R.drawable.bar_bg);
        else if(srcButtonInteger==2)
            srcButton=getResources().getDrawable(R.drawable.decorativeline);
        else if(srcButtonInteger==3)
            srcButton=getResources().getDrawable(R.drawable.abc_ab_share_pack_mtrl_alpha);
        else if(srcButtonInteger==4)
            srcButton=getResources().getDrawable(R.drawable.abc_btn_borderless_material);
        else if(srcButtonInteger==5)
            srcButton=getResources().getDrawable(R.drawable.abc_btn_check_to_on_mtrl_000);
        else if(srcButtonInteger==6)
            srcButton=getResources().getDrawable(R.drawable.abc_btn_default_mtrl_shape);

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
        Button button=(Button)findViewById(R.id.imagetextbox_button);
        imageView.setImageDrawable(src);
        textView.setText(label);
        editText.setText(text);
        editText.setHint(hint);
        editText.setInputType(inputType);
        textViewUnit.setText(unit);
        button.setBackgroundDrawable(srcButton);
        if(srcButtonInteger==0)
            button.setVisibility(INVISIBLE);
    }

    public ImageTextBox(Context context)
    {
        this(context, null);
    }
}