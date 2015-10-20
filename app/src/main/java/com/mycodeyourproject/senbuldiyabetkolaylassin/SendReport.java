package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SendReport extends AppCompatActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonAction(View view) {

        EditText txtEmail = (EditText) findViewById(R.id.report_email).findViewById(R.id.textbox_editText);
        EditText txtPhone = (EditText) findViewById(R.id.report_phonenumber).findViewById(R.id.textbox_editText);
        email = txtEmail.getText().toString();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userValue = sharedPref.getString(getString(R.string.signeduser), "");
        Map<Object, Object> user = DataTransferObjects.User.GetUser(userValue);
        String name="", surname="";

        String raporText="";
        if(user != null)
        {
            name = user.get("NAME").toString();
            surname = user.get("SURNAME").toString();

            List<Map<Object, Object>> userDataList = DataTransferObjects.UserDatalog.getUserDatalogList(userValue);

            Comparator<Object> cmp = new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    return Integer.valueOf(((Map<Object, Object>) o1).get("ID").toString()).compareTo(Integer.valueOf(((Map<Object, Object>) o2).get("ID").toString()));
                }
            };

            int glucoseCount = 0;
            for (int i = 0; i < userDataList.size(); i++) {
                if (Float.parseFloat(userDataList.get(i).get("GLUCOSE").toString()) > 0)
                    glucoseCount++;
            }

            Map<Object, Object> lastData = Collections.max(userDataList, cmp);


            raporText += (name+" "+surname+"\n\n");
            raporText += ("Kan Şekeri : " + lastData.get("GLUCOSE").toString() +"\n\n");
            raporText += ("İstatistikler : " + "\n");
            raporText += ("Toplam kayıt : "+userDataList.size()+"\n\n");
            raporText += ("Günlük şeker ölçüm sayısı: "+glucoseCount+"\n");

        }else
        {
            Log.e("NULL", "NULL");
        }
        if (view.getId() == R.id.mailButton) {
            String TO = "agahburakdemirkan@yahoo.com";
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO});
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ViaDiabet Rapor");
            Log.e("raporttext", raporText);
            emailIntent.putExtra(Intent.EXTRA_TEXT, raporText);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                Log.e("Finished sending email.", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(SendReport.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.smsButton)

        {
            SmsManager smsManager = SmsManager.getDefault();
            String message = "#ViaDiabet :\n";
            message += raporText;
            smsManager.sendTextMessage(txtPhone.getText().toString(), null, message, null, null);
            finish();
        }
    }
}