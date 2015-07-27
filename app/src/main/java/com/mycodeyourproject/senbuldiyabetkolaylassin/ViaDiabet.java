package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ViaDiabet extends BaseViaDiabetActivity
{
    public final static String MESAJ = "com.mycodeyourproject.senbuldiyabetkolaylassin.MESAJ";

    private void setInitialValues()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat((getString(R.string.date_format)));
        SimpleDateFormat sdfTime = new SimpleDateFormat((getString(R.string.time_format)));

        TextView lastCheckBlood=(TextView)findViewById(R.id.text_last_check_blood);
        lastCheckBlood.setText(getString(R.string.number_zero));

        TextView lastCheckDate=(TextView)findViewById(R.id.text_last_check_date);
        lastCheckDate.setText(sdfDate.format(Calendar.getInstance().getTime()));

        TextView lastCheckInsulinDate=(TextView)findViewById(R.id.text_last_check_insulin_date);
        lastCheckInsulinDate.setText(sdfDate.format(Calendar.getInstance().getTime()));
        TextView autoCommentTime=(TextView)findViewById(R.id.text_auto_comment_time);
        autoCommentTime.setText(sdfTime.format(Calendar.getInstance().getTime()));

        TextView lastCheckInsuline=(TextView)findViewById(R.id.text_last_check_insulin);

        String firstString = getString(R.string.number_zero);
        String secondString = getString(R.string.unity_insuline);

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(firstString + secondString);
        stringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, firstString.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        stringBuilder.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), firstString.length(),
                firstString.length() + secondString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        lastCheckInsuline.setText(stringBuilder);

        TextView autoComment=(TextView)findViewById(R.id.text_auto_comment);

        String firstComment = getString(R.string.auto_comment_hey);
        String secondComment = getString(R.string.auto_comment_need_more_values);

        SpannableStringBuilder commentBuilder = new SpannableStringBuilder(firstComment + secondComment);
        commentBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, firstComment.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        commentBuilder.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), firstComment.length(),
                firstComment.length() + secondComment.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        autoComment.setText(commentBuilder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_via_diabet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_via_diabet, menu);
        return true;
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId())
        {
            case R.id.action_my_info:
                Toast.makeText(this,R.string.action_my_info,Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_logs:
                Toast.makeText(this,R.string.action_logs,Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_tools:
                Toast.makeText(this,R.string.action_tools,Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_settings:
                Intent intent=new Intent(ViaDiabet.this,Settings.class);
                /*PREFERENCE ACTIVITY de bu sekilde yapilmali
                Intent intent=new Intent(ViaDiabet.this,Preferences.class);*/
                startActivity(intent);
                return true;

            case R.id.action_about:
                Toast.makeText(this,R.string.action_about,Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_help:
                Toast.makeText(this,R.string.action_help,Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_twitter:
                try
                {
                    ApplicationInfo info = getPackageManager().getApplicationInfo("com.twitter.android", 0 );
                    PackageManager pm=getPackageManager();
                    Intent twitter=pm.getLaunchIntentForPackage("com.twitter.android");
                    startActivity(twitter);
                }
                catch( PackageManager.NameNotFoundException e )
                {
                    Toast.makeText(this,R.string.action_app_not_found,Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;

            case R.id.action_facebook:
                try
                {
                    ApplicationInfo info = getPackageManager().getApplicationInfo("com.facebook.android", 0 );
                    PackageManager pm=getPackageManager();
                    Intent facebook=pm.getLaunchIntentForPackage("com.facebook.android");
                    startActivity(facebook);
                }
                catch( PackageManager.NameNotFoundException e )
                {
                    return false;
                }
                return true;
            case R.id.action_updates:
                Toast.makeText(this,R.string.action_check_updates,Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_logout:
                Toast.makeText(this,R.string.action_logout,Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createNotification(View v) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Intent i = new Intent(this, ViaDiabet.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);

        builder.setTicker("Şekerini ölç");

        builder.setSmallIcon(R.drawable.cast_ic_notification_0);

        builder.setAutoCancel(true);

        Notification notification = builder.build();

        if (Build.VERSION.SDK_INT >= 16) {
            RemoteViews expandedView =
                    new RemoteViews(getPackageName(), R.layout.notification_expanded);
            notification.bigContentView = expandedView;
        }
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
    }

    //BURASI PREFERENCEACTIVITY KULLANILACAGI ZAMAN BU SEKILDE YAPILMALI

    /*public class ViaDiabet extends BaseViaDiabetActivity implements SharedPreferences.OnSharedPreferenceChangeListener
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Toast.makeText(this,getResources().getString(R.string.preferences_saved),Toast.LENGTH_LONG).show();
    }*/

//    public void GonderMesaj(View view)
//    {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.veri_girisi);
//        String message = editText.getText().toString();
//        intent.putExtra(MESAJ, message);
//        startActivity(intent);
//    }
}
