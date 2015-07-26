package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Burak on 13.07.2015.
 */
public class Preferences extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
