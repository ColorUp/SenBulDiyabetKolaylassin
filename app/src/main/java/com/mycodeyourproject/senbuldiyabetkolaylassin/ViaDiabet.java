package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ViaDiabet extends BaseViaDiabetActivity {
    public final static String MESAJ = "com.mycodeyourproject.senbuldiyabetkolaylassin.MESAJ";

    private void setInitialValues() {
        SimpleDateFormat sdfDate = new SimpleDateFormat((getString(R.string.date_format)));
        SimpleDateFormat sdfDateTime = new SimpleDateFormat(getString(R.string.date_format) +" " + getString(R.string.time_format));

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userValue = sharedPref.getString(getString(R.string.signeduser), "");

        //Veritabanından verilerin hepsi okunur
        List<Map<Object, Object>> userDataList = DataTransferObjects.UserDatalog.getUserDatalogList(userValue);

        Comparator<Object> cmp = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.valueOf(((Map<Object, Object>) o1).get("ID").toString()).compareTo(Integer.valueOf(((Map<Object, Object>) o2).get("ID").toString()));
            }
        };

        Map<Object, Object> lastData = Collections.max(userDataList, cmp);

        TextView autoCommentTime = (TextView) findViewById(R.id.auto_comment_time);
        autoCommentTime.setText(sdfDateTime.format(Calendar.getInstance().getTime()));

        TextView autoComment = (TextView) findViewById(R.id.auto_comment);

        String firstComment = getString(R.string.auto_comment_hey);
        String secondComment = getString(R.string.auto_comment_need_more_values);

        SpannableStringBuilder commentBuilder = new SpannableStringBuilder(firstComment + secondComment);
        commentBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, firstComment.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        commentBuilder.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), firstComment.length(),
                firstComment.length() + secondComment.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        autoComment.setText(commentBuilder);

        String mgDecilitre = getResources().getString(R.string.unity_mg_decilitre);
        String calori = "kCal";
        String gram = getResources().getString(R.string.unity_gram);
        String insuline = getResources().getString(R.string.unity_insuline);
        String kilogram = "kg";

        String lastDataDateTime = lastData.get("DATETIME").toString();
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(lastDataDateTime);
        } catch (ParseException ex) {
            date = new Date();
        }

        TextView lastCheckBlood = (TextView) findViewById(R.id.text_last_check_blood);
        lastCheckBlood.setText(String.valueOf(Math.round(Float.parseFloat(lastData.get("GLUCOSE").toString()))));
        TextView lastCheckBloodDate = (TextView) findViewById(R.id.text_last_check_date);
        lastCheckBloodDate.setText(sdfDate.format(date));
        TextView lastCheckInsulinDate = (TextView) findViewById(R.id.text_last_check_insulin_date);
        lastCheckInsulinDate.setText(sdfDate.format(date));
        TextView lastInsuline = (TextView) findViewById(R.id.text_insulin);
        lastInsuline.setText(Extensions.Format("{0} {1}", lastData.get("INSULINE").toString(), insuline));

        TextView lastDataCarbonhydrade = (TextView) findViewById(R.id.statistic_last_data_carbonhydrade);
        lastDataCarbonhydrade.setText(Extensions.Format("{0} {1}", lastData.get("CARBONHYDRADE").toString(), gram));
        TextView lastDataCarbonhydradeDateTime = (TextView) findViewById(R.id.statistic_last_data_carbonhydrade_date_time);
        lastDataCarbonhydradeDateTime.setText(lastData.get("DATETIME").toString());
        TextView lastDataCalori = (TextView) findViewById(R.id.statistic_last_data_calori);
        lastDataCalori.setText(Extensions.Format("{0} {1}", lastData.get("CALORI").toString(), calori));
        TextView lastDataCaloriDateTime = (TextView) findViewById(R.id.statistic_last_data_calori_date_time);
        lastDataCaloriDateTime.setText(lastData.get("DATETIME").toString());
        TextView lastDataGlucose = (TextView) findViewById(R.id.statistic_last_data_glucose);
        lastDataGlucose.setText(Extensions.Format("{0} {1}", lastData.get("GLUCOSE").toString(), mgDecilitre));
        TextView lastDataGlucoseDateTime = (TextView) findViewById(R.id.statistic_last_data_glucose_date_time);
        lastDataGlucoseDateTime.setText(lastData.get("DATETIME").toString());
        TextView lastDataWeight = (TextView) findViewById(R.id.statistic_last_data_weight);
        lastDataWeight.setText(Extensions.Format("{0} {1}", lastData.get("WEIGHT").toString(), kilogram));
        TextView totalRecord = (TextView) findViewById(R.id.statistic_total_record);
        totalRecord.setText(String.valueOf(userDataList.size()));
        TextView totalDailyGlicoseRecord = (TextView) findViewById(R.id.statistic_daily_total_glicose_record);

        int glucoseCount = 0;
        for (int i = 0; i < userDataList.size(); i++) {
            if (Float.parseFloat(userDataList.get(i).get("GLUCOSE").toString()) > 0)
                glucoseCount++;
        }

        totalDailyGlicoseRecord.setText(String.valueOf(glucoseCount));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_via_diabet);
/*        TextView textView=new TextView(this);
        textView.setText("VIADIABET");
        android.support.v7.app.ActionBar.LayoutParams params=new android.support.v7.app.ActionBar.LayoutParams(120,30);
        super.mActionBar.setCustomView(textView, params);
        super.mActionBar.setDisplayShowCustomEnabled(true);*/

        setInitialValues();
        setButtonListener();
    }

    // Call Back method  to get the Message form other Activity    override the method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 0) {
            // fetch the message String
            String message = data.getStringExtra("Kalori") + "kCal, " +
                    data.getStringExtra("Protein") + " kJ, " +
                    data.getStringExtra("Yağ") + " kFat, " +
                    data.getStringExtra("Karbonhidrat") + "kCal";
            // Set the message string in textView
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    private void setButtonListener()
    {
        Button buttonInputData=(Button)findViewById(R.id.button_save_data);
        buttonInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViaDiabet.this, InputData.class);
                startActivity(intent);
            }
        });

        Button buttonCalculator = (Button) findViewById(R.id.button_calculator);
        buttonCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViaDiabet.this,MealCalculator.class);
                startActivityForResult(intent, 0);
            }
        });

        Button buttonStats = (Button) findViewById(R.id.button_graphs);
        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViaDiabet.this, Graphs.class);
                startActivity(intent);
            }
        });

        Button buttonLines = (Button) findViewById(R.id.button_lines);
        buttonLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViaDiabet.this, Stats.class);
                startActivity(intent);
            }
        });

        Button buttonDatabase=(Button)findViewById(R.id.button_database);
        buttonDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViaDiabet.this, DataBank.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_via_diabet, menu);
        return true;
    }

    /**
     * On selecting action bar icons
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_my_info:
                Intent intent =new Intent(ViaDiabet.this,Questions.class);
                startActivity(intent);
                return true;

            case R.id.action_logs:
                Toast.makeText(this, R.string.action_logs, Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_tools:
                Toast.makeText(this, R.string.action_tools, Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_settings:
                Intent intentS = new Intent(ViaDiabet.this, Settings.class);
                /*PREFERENCE ACTIVITY de bu sekilde yapilmali
                Intent intent=new Intent(ViaDiabet.this,Preferences.class);*/
                startActivity(intentS);
                return true;

            case R.id.action_about:
                Toast.makeText(this, R.string.action_about, Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_help:
                Toast.makeText(this, R.string.action_help, Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_logout:
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear().apply();
                finish();
                System.exit(0);
                return true;
            case R.id.action_diet:
                Intent intentDiet = new Intent(ViaDiabet.this, DietList.class);
                startActivity(intentDiet);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showNotif(View v)
    {
        Intent newIntent = new Intent(ViaDiabet.this, SendReport.class);
        startActivity(newIntent);
    }

    private void createNotification() {
        // BEGIN_INCLUDE(notificationCompat)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // END_INCLUDE(notificationCompat)

        // BEGIN_INCLUDE(intent)
        //Create Intent to launch this Activity again if the notification is clicked.
        Intent i = new Intent(this, ViaDiabet.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);
        // END_INCLUDE(intent)

        // BEGIN_INCLUDE(ticker)
        // Sets the ticker text
        builder.setTicker(getResources().getString(R.string.custom_notification));

        // Sets the small icon for the ticker
        builder.setSmallIcon(R.drawable.cast_ic_notification_0);
        // END_INCLUDE(ticker)

        // BEGIN_INCLUDE(buildNotification)
        // Cancel the notification when clicked
        builder.setAutoCancel(true);

        // Build the notification
        Notification notification = builder.build();
        // END_INCLUDE(buildNotification)

        // BEGIN_INCLUDE(customLayout)
        // Inflate the notification layout as RemoteViews

        // Set text on a TextView in the RemoteViews programmatically.

        /* Workaround: Need to set the content view here directly on the notification.
         * NotificationCompatBuilder contains a bug that prevents this from working on platform
         * versions HoneyComb.
         * See https://code.google.com/p/android/issues/detail?id=30495
         */
        //notification.contentView = contentView;

        // Add a big content view to the notification if supported.
        // Support for expanded notifications was added in API level 16.
        // (The normal contentView is shown when the notification is collapsed, when expanded the
        // big content view set here is displayed.)
        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView =
                    new RemoteViews(getPackageName(), R.layout.notification_expanded);
            notification.bigContentView = expandedView;
        }
        // END_INCLUDE(customLayout)

        // START_INCLUDE(notify)
        // Use the NotificationManager to show the notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
        // END_INCLUDE(notify)
    }

    //BURASI PREFERENCEACTIVITY KULLANILACAGI ZAMAN BU SEKILDE YAPILMALI

    /*public class ViaDiabet extends BaseViaDiabetActivity implements SharedPreferences.OnSharedPreferenceChangeListener
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Toast.makeText(this,getResources().getString(R.string.preferences_saved),Toast.LENGTH_LONG).show();
    }*/
}
