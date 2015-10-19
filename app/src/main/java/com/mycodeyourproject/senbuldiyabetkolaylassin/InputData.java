package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class InputData extends BaseViaDiabetActivity {
    private String array_spinner[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

/*        TextView textView=new TextView(this);
        textView.setText("VIADIABET");
        android.support.v7.app.ActionBar.LayoutParams params=new android.support.v7.app.ActionBar.LayoutParams(120,30);
        super.mActionBar.setCustomView(textView, params);
        super.mActionBar.setDisplayShowCustomEnabled(true);*/

        Resources res = getResources();
        DateTimePicker dateTimePicker = (DateTimePicker) findViewById(R.id.input_date_time);

       /* String[] array_spinner = res.getStringArray(R.array.array_categories);
        Spinner s = (Spinner) findViewById(R.id.spinner_category);
        ArrayAdapter adapter = new ArrayAdapter(this,, array_spinner);
        s.setAdapter(adapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_data, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                EditText txtInputDate = (EditText) findViewById(R.id.input_date_time).findViewById(R.id.datetimepicker_editText);
                EditText txtGlicose = (EditText) findViewById(R.id.imagetextbox_glicose).findViewById(R.id.imagetextbox_text);
                EditText txtCarbonhydrade = (EditText) findViewById(R.id.imagetextbox_carbonhydrade).findViewById(R.id.imagetextbox_text);
                EditText txtInsuline = (EditText) findViewById(R.id.imagetextbox_insuline).findViewById(R.id.imagetextbox_text);
                EditText txtWeight = (EditText) findViewById(R.id.imagetextbox_weight).findViewById(R.id.imagetextbox_text);
                EditText txtHb1ac = (EditText) findViewById(R.id.imagetextbox_hb1ac).findViewById(R.id.imagetextbox_text);
                EditText txtColesterol = (EditText) findViewById(R.id.imagetextbox_colestrol).findViewById(R.id.imagetextbox_text);
                Spinner category = (Spinner) findViewById(R.id.spinner_category).findViewById(R.id.spinner);
                Object selectedCategory = category.getSelectedItem();

                ArrayList<View> views = new ArrayList<View>();
                views.add(txtInputDate);
                views.add(txtGlicose);          //Bu kontroller silinirse obje oluşturulurken null kontrolü gerekir
                views.add(txtCarbonhydrade);    //Bu kontroller silinirse obje oluşturulurken null kontrolü gerekir
                views.add(txtInsuline);         //Bu kontroller silinirse obje oluşturulurken null kontrolü gerekir
                views.add(txtWeight);           //Bu kontroller silinirse obje oluşturulurken null kontrolü gerekir
                views.add(txtHb1ac);            //Bu kontroller silinirse obje oluşturulurken null kontrolü gerekir
                views.add(txtColesterol);       //Bu kontroller silinirse obje oluşturulurken null kontrolü gerekir
                views.add(category);            //Bu kontroller silinirse obje oluşturulurken null kontrolü gerekir

                if (Controls.ControlViewValues(views, getResources())) {
                    Toast.makeText(this, "Girişlerinizi kontrol ediniz!", Toast.LENGTH_LONG).show();
                    return false;
                }

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userValue = sharedPref.getString(getString(R.string.signeduser), "");

                DataTransferObjects.UserDatalog userDatalog = new DataTransferObjects.UserDatalog(userValue,
                        Float.valueOf(txtGlicose.getText().toString()), Float.valueOf(txtCarbonhydrade.getText().toString()),
                        Float.valueOf(txtInsuline.getText().toString()), Float.valueOf(txtWeight.getText().toString()),
                        Float.valueOf(txtHb1ac.getText().toString()), Float.valueOf(txtColesterol.getText().toString()),
                        0, 0f);

                Map<String, String> userDatalogObject = userDatalog.getUserDatalogObject();

                boolean result = DatabaseQuery.Insert("USERDATALOG", userDatalogObject);

                if (result)
                    Toast.makeText(this, "Başarılı", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Başarısız", Toast.LENGTH_LONG).show();

                finish();
                Intent intent = new Intent(InputData.this, ViaDiabet.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}