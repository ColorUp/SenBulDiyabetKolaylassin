package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.SetProgressBarTextValueAndShow("Kullanıcı Bilgileri (1/2)", 50);
    }

    public void signUp(View v)
    {
        Button btnSave=(Button)findViewById(R.id.buttonSave);
        EditText txtUserName=(EditText)findViewById(R.id.signup_username).findViewById(R.id.textbox_editText);
        EditText txtPassword=(EditText)findViewById(R.id.signup_password).findViewById(R.id.textbox_editText);
        EditText txtPasswordAgain=(EditText)findViewById(R.id.signup_password_again).findViewById(R.id.textbox_editText);
        EditText txtEmail=(EditText)findViewById(R.id.signup_email).findViewById(R.id.textbox_editText);
        EditText txtPhoneNumber=(EditText)findViewById(R.id.signup_phone_number).findViewById(R.id.textbox_editText);

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
