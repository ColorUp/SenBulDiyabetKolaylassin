package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        email = ((EditText) findViewById(R.id.mailEdittext)).getText().toString();
        if (view.getId() == R.id.mailButton) {
            String TO = "agahburakdemirkan@yahoo.com";
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ TO});
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ViaDiabet Rapor");
            emailIntent.putExtra(Intent.EXTRA_TEXT, ((EditText) findViewById(R.id.raporText)).getText().toString()+"\n"+email);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                Log.e("Finished sending email...", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(SendReport.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.smsButton)

        {
            SmsManager smsManager = SmsManager.getDefault();
            String message = "#ViaDiabet :\n";
            EditText raporET = (EditText) findViewById(R.id.raporText);
            message += raporET.getText().toString() + "\n" + email;
            smsManager.sendTextMessage("+905075042570", null, message, null, null);
            finish();
        }
    }
}