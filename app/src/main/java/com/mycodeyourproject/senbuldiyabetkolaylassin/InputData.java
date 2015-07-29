package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class InputData extends Activity
{
    private String array_spinner[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        Resources res = getResources();
        DateTimePicker dateTimePicker = (DateTimePicker)findViewById(R.id.input_date_time);
        dateTimePicker.setTime(this);
        dateTimePicker.setDate(this);

        String[] array_spinner = res.getStringArray(R.array.array_categories);
        Spinner s = (Spinner) findViewById(R.id.spinner_category);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        s.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_data, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_save:
/*                File file = new File("Sample.txt");
                try {
                    System.out.println("exists: "+file.exists());
                    file.createNewFile();
                    System.out.println("exists: "+file.exists());
                    System.out.println("canRead: "+file.canRead());
                    System.out.println("canWrite: "+file.canWrite());
                    System.out.println("getPath: "+file.getPath());
                    System.out.println("getAbsolutePath: "+file.getAbsolutePath());
                    System.out.println("getCanonicalPath: "+file.getCanonicalPath());
                    System.out.println("getParent: "+file.getParent());
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


