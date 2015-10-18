package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Burak on 18.10.2015.
 */
public class TimePicker extends LinearLayout{
    private EditText editTextTime;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat timeFormatter;

    private Calendar cal;
    private int hour;
    private int minute;

    public TimePicker(final Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TextBox, 0, 0);
        String label = typedArray.getString(R.styleable.TextBox_label);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.time_picker, this, true);

        timeFormatter = new SimpleDateFormat(getResources().getString(R.string.time_format), Locale.US);
        TextView textView = (TextView)findViewById(R.id.timepicker_textView);
        textView.setText(label);

        editTextTime = (EditText)findViewById(R.id.timepicker_editText);
        editTextTime.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);

        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        editTextTime.setText(Extensions.Format("{0}:{1}", String.valueOf(hour), String.valueOf(minute)));
        editTextTime.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar newCalendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        Calendar newTime = Calendar.getInstance();
                        newTime.set(1950, 12, 31, hourOfDay, minute);
                        editTextTime.setText(timeFormatter.format(newTime.getTime()));
                    }
                },newCalendar.get(Calendar.HOUR_OF_DAY),newCalendar.get(Calendar.MINUTE),true);

                timePickerDialog.show();
            }
        });
    }

    public TimePicker(Context context)
    {
        this(context, null);
    }
}