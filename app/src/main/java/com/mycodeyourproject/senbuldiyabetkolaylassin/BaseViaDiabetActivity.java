package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Burak on 12.07.2015.
 */
public class BaseViaDiabetActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.show();
    }
}
