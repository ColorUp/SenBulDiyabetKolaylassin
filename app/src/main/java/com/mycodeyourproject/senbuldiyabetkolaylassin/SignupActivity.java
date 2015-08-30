package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class SignupActivity extends BaseViaDiabetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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

    public void signUp(View v)
    {
        Button btnSave=(Button)findViewById(R.id.buttonSave);
        EditText txtUserName=(EditText)findViewById(R.id.editTextUserName);
        EditText txtPassword=(EditText)findViewById(R.id.editTextPassword);
        EditText txtPasswordAgain=(EditText)findViewById(R.id.editTextPasswordAgain);
        EditText txtEmail=(EditText)findViewById(R.id.editTextEmail);
        EditText txtPhoneNumber=(EditText)findViewById(R.id.editTextPhoneNumber);

        Date birthDate=new Date(2000,12,23);

        DataTransferObjects.User newUser=new DataTransferObjects.User(txtUserName.getText().toString(),txtPassword.getText().toString(),
                birthDate,1,"11971280728","Agah Burak","DEMİRKAN");

        Map<String,String> userObject=newUser.getUserObject();
        boolean result = DatabaseQuery.Insert("USER", userObject);

       if(result)
            Toast.makeText(this,"Başarılı",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Başarısız", Toast.LENGTH_LONG).show();

/*        finish();
        Intent intent = new Intent(SignupActivity.this, Questions.class);
        startActivity(intent);*/
    }
}
