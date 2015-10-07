package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

/***** Adapter class extends with ArrayAdapter ******/
public class CustomAdapter extends ArrayAdapter<String> {

    private ArrayList data;
    public Resources res;
    SpinnerModel tempValues = null;
    LayoutInflater inflater;
    String defaultValue;
    /*************
     * CustomAdapter Constructor
     *****************/
    public CustomAdapter(
            Context context,
            int textViewResourceId,
            ArrayList objects,
            LayoutInflater inflate
    ) {
        super(context, textViewResourceId, objects);

        /********** Take passed values **********/
        data = objects;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = inflate;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spinner_rows, parent, false);

        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (SpinnerModel) data.get(position);

        TextView label = (TextView) row.findViewById(R.id.spinner_row_name);
        ImageView companyLogo = (ImageView) row.findViewById(R.id.spinner_row_image);

        if (position == 0) {

            // Default selected Spinner item
            label.setText(defaultValue);
        } else {
            // Set values for spinner each row
            label.setText(tempValues.getCompanyName());
            companyLogo.setImageResource(R.drawable.image1);
        }

        return row;
    }
}