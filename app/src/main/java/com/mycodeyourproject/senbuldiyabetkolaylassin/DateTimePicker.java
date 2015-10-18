package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DateTimePicker extends LinearLayout
{
    private EditText editTextDate;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    public DateTimePicker(final Context context, AttributeSet attrs)
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
        TextView textView = (TextView)findViewById(R.id.datetimepicker_textView);
        textView.setText(label);

        editTextDate = (EditText)findViewById(R.id.datetimepicker_editText);
        editTextDate.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        editTextDate.setText(Extensions.Format("{0}-{1}-{2}",String.valueOf(day),String.valueOf(month),String.valueOf(year)));
        editTextDate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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

                datePickerDialog.show();
            }
        });
    }

    public DateTimePicker(Context context)
    {
        this(context, null);
    }
}