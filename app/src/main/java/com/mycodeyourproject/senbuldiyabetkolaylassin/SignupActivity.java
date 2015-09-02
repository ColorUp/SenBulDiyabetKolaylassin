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
import java.text.ParseException;
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

    public void setLifeStyle(View v)
    {
        Button btnSave=(Button)findViewById(R.id.buttonSave);
        EditText txtUserName=(EditText)findViewById(R.id.signup_username).findViewById(R.id.textbox_editText);
        EditText txtPassword=(EditText)findViewById(R.id.signup_password).findViewById(R.id.textbox_editText);
        EditText txtPasswordAgain=(EditText)findViewById(R.id.signup_password_again).findViewById(R.id.textbox_editText);
        EditText txtBirthOfDate=(EditText)findViewById(R.id.signup_birth_date).findViewById(R.id.datetimepicker_editText);
        EditText txtTurkishId=(EditText)findViewById(R.id.signup_turkish_id).findViewById(R.id.textbox_editText);
        EditText txtName=(EditText)findViewById(R.id.signup_name).findViewById(R.id.textbox_editText);
        EditText txtSurname=(EditText)findViewById(R.id.signup_surname).findViewById(R.id.textbox_editText);
        EditText txtEmail=(EditText)findViewById(R.id.signup_email).findViewById(R.id.textbox_editText);
        EditText txtPhoneNumber=(EditText)findViewById(R.id.signup_phone_number).findViewById(R.id.textbox_editText);

        if(txtPassword.getText()!=txtPasswordAgain.getText())
        {
            Toast.makeText(this,"Şifre bilgilerinizi kontrol ediniz!",Toast.LENGTH_LONG).show();
            return;
        }

        Date birthDate=null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try
        {
            birthDate = format.parse(txtBirthOfDate.getText().toString());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        DataTransferObjects.User newUser=new DataTransferObjects.User(txtUserName.getText().toString(),txtPassword.getText().toString(),
                birthDate,1,txtTurkishId.getText().toString(),txtName.getText().toString(),txtSurname.getText().toString());

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
