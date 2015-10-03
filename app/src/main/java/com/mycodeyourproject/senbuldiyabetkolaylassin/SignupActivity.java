package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        this.SetProgressBarTextValueAndShow("Kullanıcı Bilgileri (1/2)", 50);
    }

    public void setLifeStyle(View v) {
        EditText txtUserName = (EditText) findViewById(R.id.signup_username).findViewById(R.id.textbox_editText);
        EditText txtPassword = (EditText) findViewById(R.id.signup_password).findViewById(R.id.textbox_editText);
        EditText txtPasswordAgain = (EditText) findViewById(R.id.signup_password_again).findViewById(R.id.textbox_editText);
        EditText txtBirthOfDate = (EditText) findViewById(R.id.signup_birth_date).findViewById(R.id.datetimepicker_editText);
        EditText txtTurkishId = (EditText) findViewById(R.id.signup_turkish_id).findViewById(R.id.textbox_editText);
        EditText txtName = (EditText) findViewById(R.id.signup_name).findViewById(R.id.textbox_editText);
        EditText txtSurname = (EditText) findViewById(R.id.signup_surname).findViewById(R.id.textbox_editText);
        RadioGroup gender = (RadioGroup) findViewById(R.id.signup_gender).findViewById(R.id.radiobutton_radiogroup);
        RadioButton selectedGender = (RadioButton) findViewById(gender.getCheckedRadioButtonId());

        EditText txtEmail = (EditText) findViewById(R.id.signup_email).findViewById(R.id.textbox_editText);
        EditText txtPhoneNumber = (EditText) findViewById(R.id.signup_phone_number).findViewById(R.id.textbox_editText);

        ArrayList<View> views = new ArrayList<View>();
        views.add(txtUserName);
        views.add(txtPassword);
        views.add(txtPasswordAgain);
        views.add(txtBirthOfDate);
        views.add(txtTurkishId);
        views.add(txtName);
        views.add(txtSurname);
        views.add(gender);
        views.add(selectedGender);
        views.add(txtEmail);
        views.add(txtPhoneNumber);
        views.add(txtBirthOfDate);

        if (Controls.ControlViewValues(views, getResources())) {
            Toast.makeText(this, "Girişlerinizi kontrol ediniz!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!txtPassword.getText().toString().trim().matches(txtPasswordAgain.getText().toString().trim())) {
            Toast.makeText(this, "Şifre bilgilerinizi kontrol ediniz!", Toast.LENGTH_LONG).show();
            return;
        }

        int choosenGender = Enums.GetGenderNumber(selectedGender.getText().toString());
        Date birthDate = Converter.StringToDate(txtBirthOfDate.getText().toString());

/*        DataTransferObjects.User newUser = new DataTransferObjects.User(txtUserName.getText().toString(), txtPassword.getText().toString(),
                birthDate, choosenGender, txtTurkishId.getText().toString(), txtName.getText().toString(), txtSurname.getText().toString());

        Map<String, String> userObject = newUser.getUserObject();

        boolean result = DatabaseQuery.Insert("USER", userObject);

        if (result)
            Toast.makeText(this, "Başarılı", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Başarısız", Toast.LENGTH_LONG).show();*/

        finish();
        Intent intent = new Intent(SignupActivity.this, Questions.class);
        intent.putExtra("UserName", txtUserName.getText().toString());
        intent.putExtra("Password", txtPassword.getText().toString());
        intent.putExtra("BirthDate", birthDate);
        intent.putExtra("Gender", choosenGender);
        intent.putExtra("TurkishId", txtTurkishId.getText().toString());
        intent.putExtra("Name", txtName.getText().toString());
        intent.putExtra("Surname", txtSurname.getText().toString());
        startActivity(intent);
    }
}