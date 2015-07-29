package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimePicker extends LinearLayout
{
    private EditText editTextDate;
    private EditText editTextTime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;
    private Calendar newCalendar;

    public DateTimePicker(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TextBox, 0, 0);
        String label = typedArray.getString(R.styleable.TextBox_label);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.date_time_picker, this, true);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        timeFormatter = new SimpleDateFormat("HH:mm",Locale.US);
        TextView textView = (TextView)findViewById(R.id.datetimepicker_label);
        editTextDate = (EditText)findViewById(R.id.edittext_datepicker);
        editTextTime = (EditText)findViewById(R.id.edittext_datetimepicker);

        newCalendar = Calendar.getInstance();

        editTextDate.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        editTextTime.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);

        textView.setText(label);
    }

    public DateTimePicker(Context context)
    {
        this(context, null);
    }

    public String getTime()
    {
        return editTextTime.getText().toString();
    }

    public String getDate()
    {
        return editTextDate.getText().toString();
    }

    public void setTime(Context context)
    {
        editTextTime.setText(timeFormatter.format(Calendar.getInstance().getTime()));
        timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                Date newTime = Calendar.getInstance().getTime();
                newTime.setHours(h);
                newTime.setMinutes(m);
                editTextTime.setText(timeFormatter.format(newTime.getTime()));
            }
        },newCalendar.get(Calendar.HOUR),newCalendar.get(Calendar.MINUTE), true);

        editTextTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
    }

    public void setDate(Context context)
    {
        editTextDate.setText(dateFormatter.format(Calendar.getInstance().getTime()));
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(context, new OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        editTextDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                datePickerDialog.show();
            }
        });
    }
}