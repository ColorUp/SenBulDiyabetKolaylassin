package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Burak on 12.07.2015.
 */
public class Settings extends BaseViaDiabetActivity
{
    private static final String PREF_UPDATE="PREF_UPDATE";
    private static final String PREF_SEND_MAIL_DOCTOR="PREF_SEND_MAIL_DOCTOR";
    private static final String PREF_DOCTOR_MAIL="PREF_DOCTOR_MAIL";
    private static final String PREF_SHOW_LUNCH_TIME_ALERT="PREF_SHOW_LUNCH_TIME_ALERT";
    private static final String PREF_IMMEDIATE_CALL="PREF_IMMEDIATE_CALL";
    private static final String PREF_IMMEDIATE_CALL_NUMBER="PREF_IMMEDIATE_CALL_NUMBER";
    private static final String PREF_SHOW_YOUR_LOGS_TO_FRIEND="PREF_SHOW_YOUR_LOGS_TO_FRIEND";
    private static final String PREF_FRIEND_NAME="PREF_FRIEND_NAME";

    private CheckBox autoUpdate;
    private CheckBox sendMailDoctor;
    private CheckBox showLunchTimeAlert;
    private CheckBox useImmediateCall;
    private CheckBox sendYourLogsToFriend;
    private EditText editTextDoctor;
    private EditText editTextImmediateCall;
    private EditText editTextFindName;
    private TextView textViewDoctor;
    private TextView textViewImmediateCall;
    private Button buttonFindFriend;

    private SharedPreferences sharedPreferences;

    private void checkBoxKontrolEt(boolean state, View view1, View view2)
    {
        if(state)
        {
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
        }
        else
        {
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.GONE);
        }
    }

    private void tercihleriKaydet()
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(PREF_UPDATE,autoUpdate.isChecked());
        editor.putBoolean(PREF_SEND_MAIL_DOCTOR, sendMailDoctor.isChecked());
        editor.putString(PREF_DOCTOR_MAIL, editTextDoctor.getText().toString());
        editor.putBoolean(PREF_SHOW_LUNCH_TIME_ALERT, showLunchTimeAlert.isChecked());
        editor.putBoolean(PREF_IMMEDIATE_CALL, useImmediateCall.isChecked());
        editor.putString(PREF_IMMEDIATE_CALL_NUMBER, editTextImmediateCall.getText().toString());
        editor.putBoolean(PREF_SHOW_YOUR_LOGS_TO_FRIEND, sendYourLogsToFriend.isChecked());
        editor.putString(PREF_FRIEND_NAME, editTextFindName.getText().toString());
        editor.apply();

        Toast.makeText(this, getResources().getString(R.string.preferences_saved), Toast.LENGTH_LONG).show();
        Thread thread=new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3500);
                    Settings.this.finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void tercihleriOku()
    {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        autoUpdate.setChecked(sharedPreferences.getBoolean(PREF_UPDATE,true));
        sendMailDoctor.setChecked(sharedPreferences.getBoolean(PREF_SEND_MAIL_DOCTOR, false));
        editTextDoctor.setText(sharedPreferences.getString(PREF_DOCTOR_MAIL, getResources().getString(R.string.edittext_doctor_mail)));
        showLunchTimeAlert.setChecked(sharedPreferences.getBoolean(PREF_SHOW_LUNCH_TIME_ALERT, false));
        useImmediateCall.setChecked(sharedPreferences.getBoolean(PREF_IMMEDIATE_CALL, false));
        editTextImmediateCall.setText(sharedPreferences.getString(PREF_IMMEDIATE_CALL_NUMBER, getResources().getString(R.string.edittext_immediate_call)));
        sendYourLogsToFriend.setChecked(sharedPreferences.getBoolean(PREF_SHOW_YOUR_LOGS_TO_FRIEND, false));
        editTextFindName.setText(sharedPreferences.getString(PREF_FRIEND_NAME,getResources().getString(R.string.edittext_find_your_friend)));

        checkBoxKontrolEt(sendMailDoctor.isChecked(), textViewDoctor, editTextDoctor);
        checkBoxKontrolEt(useImmediateCall.isChecked(), textViewImmediateCall, editTextImmediateCall);
        checkBoxKontrolEt(sendYourLogsToFriend.isChecked(),buttonFindFriend,editTextFindName);
    }

    private void initializeScreen()
    {
        Button buttonKaydet=(Button)findViewById(R.id.button_save_settings);
        buttonKaydet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tercihleriKaydet();
            }
        });

        autoUpdate=(CheckBox)findViewById(R.id.checkbox_update);

        textViewDoctor=(TextView)findViewById(R.id.textview_doctor_mail);
        editTextDoctor=(EditText)findViewById(R.id.edittext_doctor_mail);
        sendMailDoctor=(CheckBox)findViewById(R.id.checkbox_send_mail_doctor);
        sendMailDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxKontrolEt(sendMailDoctor.isChecked(), textViewDoctor, editTextDoctor);
            }
        });

        showLunchTimeAlert=(CheckBox)findViewById(R.id.checkbox_show_lunch_time_alert);

        textViewImmediateCall=(TextView)findViewById(R.id.textview_immediate_call);
        editTextImmediateCall=(EditText)findViewById(R.id.edittext_immediate_call);
        useImmediateCall=(CheckBox)findViewById(R.id.checkbox_use_immediate_call);
        useImmediateCall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkBoxKontrolEt(useImmediateCall.isChecked(),textViewImmediateCall,editTextImmediateCall);
            }
        });

        buttonFindFriend=(Button)findViewById(R.id.button_find_your_friend);
        editTextFindName=(EditText)findViewById(R.id.edittext_friend_name);
        sendYourLogsToFriend=(CheckBox)findViewById(R.id.checkbox_show_your_logs_to_friend);
        sendYourLogsToFriend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkBoxKontrolEt(sendYourLogsToFriend.isChecked(),buttonFindFriend,editTextFindName);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.initializeScreen();

        tercihleriOku();
    }
}
