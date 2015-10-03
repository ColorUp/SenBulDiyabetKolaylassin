package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Burak on 29.09.2015.
 */
public class Controls {
    public static boolean ControlViewValues(ArrayList<View> group, Resources resources) {
        for (int i = 0; i < group.size(); i++) {
            View view = group.get(i);
            if (view instanceof EditText) {
                EditText editText = ((EditText) view);
                String text = editText.getText().toString();

                if (text.matches("")) {
                    editText.setBackgroundColor(resources.getColor(R.color.red));
                    return true;
                }
                else {
                    editText.setBackground(resources.getDrawable(R.drawable.edittextstyle));
                }
            } else if (view instanceof RadioGroup) {
                RadioGroup radioGroup = ((RadioGroup) view);
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    radioGroup.setBackgroundColor(resources.getColor(R.color.red));
                    return true;
                }
                else {
                    radioGroup.setBackgroundColor(resources.getColor(R.color.lightBlue));
                }
            }
        }

        return false;
    }
}