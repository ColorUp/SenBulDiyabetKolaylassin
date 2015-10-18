package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Burak on 27.07.2015.
 */
public class Questions extends BaseViaDiabetActivity {
    EditText txtHeight;
    EditText txtWeight;
    Spinner meslek;
    Spinner medeniDurum;
    EditText txtUyanmaSaati;
    EditText txtUyumaSaati;
    RadioGroup sportActivity;
    RadioButton selectedSportActivity;
    Spinner sporSikligi;
    EditText txtSporSuresi;
    RadioGroup seyahat;
    RadioButton selectedSeyahat;
    EditText txtKahvaltiZamani;
    RadioGroup snackAfterBreakfast;
    RadioButton selectedSnackAfterBreakfast;
    EditText txtOgleYemegiZamani;
    RadioGroup snackAfterLunch;
    RadioButton selectedSnackAfterlunch;
    EditText txtAksamYemegiZamani;
    RadioGroup snackAfterDinner;
    RadioButton selectedSnackAfterDinner;
    EditText txtWater;
    RadioGroup smoke;
    RadioButton selectedSmoke;
    RadioGroup alcohol;
    RadioButton selectedAlcohol;
    RadioGroup allergia;
    RadioButton selectedAllergia;
    RadioGroup medicine;
    RadioButton selectedMedicine;
    EditText txtComment;
    String userValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        this.SetProgressBarTextValueAndShow("Yaşam Tarzı (2/2)", 100);

        setIniliazeItems();

        Button buttonSignUp = (Button) findViewById(R.id.button_mainpage);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInitialValues();
            }
        });

        Button buttonBreakfast = (Button) findViewById(R.id.button_choose_breakfast);
        buttonBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Questions.this,MealCalculator.class);
                startActivity(intent);
            }
        });
        Button buttonLunch = (Button) findViewById(R.id.button_choose_lunch);
        buttonLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Questions.this,MealCalculator.class);
                startActivity(intent);
            }
        });
        Button buttonDinner = (Button) findViewById(R.id.button_choose_dinner);
        buttonDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Questions.this,MealCalculator.class);
                startActivity(intent);
            }
        });
        Button buttonLovelyMeals = (Button) findViewById(R.id.button_choose_lovely_meals);
        buttonLovelyMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Questions.this,MealCalculator.class);
                startActivity(intent);
            }
        });
        Button buttonDisliked = (Button) findViewById(R.id.button_choose_disliked_meals);
        buttonDisliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Questions.this, MealCalculator.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        userValue = sharedPref.getString(getString(R.string.signeduser), "");

        if (userValue.matches("") == false)
            getInitialValues(userValue);
    }

    private void setIniliazeItems() {
        txtHeight = (EditText) findViewById(R.id.imagetextbox_height).findViewById(R.id.imagetextbox_text);
        txtWeight = (EditText) findViewById(R.id.imagetextbox_weight).findViewById(R.id.imagetextbox_text);
        meslek = (Spinner) findViewById(R.id.spinner_meslek).findViewById(R.id.spinner);
        medeniDurum = (Spinner) findViewById(R.id.spinner_medeni_durum).findViewById(R.id.spinner);
        txtUyanmaSaati = (EditText) findViewById(R.id.timepicker_uyanma_saati).findViewById(R.id.timepicker_editText);
        txtUyumaSaati = (EditText) findViewById(R.id.timepicker_uyuma_saati).findViewById(R.id.timepicker_editText);
        sportActivity = (RadioGroup) findViewById(R.id.radiobutton_sportactivity).findViewById(R.id.radiobutton_radiogroup);
        sporSikligi = (Spinner) findViewById(R.id.spinner_spor_sikligi).findViewById(R.id.spinner);
        txtSporSuresi = (EditText) findViewById(R.id.timepicker_spor_zamani).findViewById(R.id.timepicker_editText);
        seyahat = (RadioGroup) findViewById(R.id.radiobutton_seyahat).findViewById(R.id.radiobutton_radiogroup);
        txtKahvaltiZamani = (EditText) findViewById(R.id.timepicker_kahvalti_zamani).findViewById(R.id.timepicker_editText);
        snackAfterBreakfast = (RadioGroup) findViewById(R.id.radiobutton_snackafterbreakfast).findViewById(R.id.radiobutton_radiogroup);
        txtOgleYemegiZamani = (EditText) findViewById(R.id.timepicker_ogle_yemegi_zamani).findViewById(R.id.timepicker_editText);
        snackAfterLunch = (RadioGroup) findViewById(R.id.radiobutton_snackafterlunch).findViewById(R.id.radiobutton_radiogroup);
        txtAksamYemegiZamani = (EditText) findViewById(R.id.timepicker_aksam_yemegi_zamani).findViewById(R.id.timepicker_editText);
        snackAfterDinner = (RadioGroup) findViewById(R.id.radiobutton_snackafterdinner).findViewById(R.id.radiobutton_radiogroup);
        txtWater = (EditText) findViewById(R.id.imagetextbox_water).findViewById(R.id.imagetextbox_text);
        smoke = (RadioGroup) findViewById(R.id.radiobutton_smoke).findViewById(R.id.radiobutton_radiogroup);
        alcohol = (RadioGroup) findViewById(R.id.radiobutton_alcohol).findViewById(R.id.radiobutton_radiogroup);
        allergia = (RadioGroup) findViewById(R.id.radiobutton_allergia).findViewById(R.id.radiobutton_radiogroup);
        medicine = (RadioGroup) findViewById(R.id.radiobutton_medicine).findViewById(R.id.radiobutton_radiogroup);
        txtComment = (EditText) findViewById(R.id.editTextComments);
    }

    private void setInitialValues() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat(getResources().getString(R.string.time_format), Locale.US);

        ArrayList<View> views = new ArrayList<View>();
        views.add(txtHeight);
        views.add(txtWeight);
        views.add(meslek);
        views.add(medeniDurum);
        views.add(txtUyanmaSaati);
        views.add(txtUyumaSaati);
        views.add(sportActivity);
        views.add(sporSikligi);
        views.add(txtSporSuresi);
        views.add(snackAfterBreakfast);
        views.add(txtKahvaltiZamani);
        views.add(snackAfterLunch);
        views.add(txtOgleYemegiZamani);
        views.add(snackAfterDinner);
        views.add(txtAksamYemegiZamani);
        views.add(seyahat);
        views.add(smoke);
        views.add(medicine);

        if (Controls.ControlViewValues(views, getResources())) {
            Toast.makeText(this, "Girişlerinizi kontrol ediniz!", Toast.LENGTH_LONG).show();
            return;
        }

        DataTransferObjects.UserLifeStyle userLifeStyle=null;

        try {
            selectedSportActivity = (RadioButton) findViewById(sportActivity.getCheckedRadioButtonId());
            selectedSeyahat = (RadioButton) findViewById(seyahat.getCheckedRadioButtonId());
            selectedSnackAfterBreakfast = (RadioButton) findViewById(snackAfterBreakfast.getCheckedRadioButtonId());
            selectedSnackAfterlunch = (RadioButton) findViewById(snackAfterLunch.getCheckedRadioButtonId());
            selectedSnackAfterDinner = (RadioButton) findViewById(snackAfterDinner.getCheckedRadioButtonId());
            selectedSmoke = (RadioButton) findViewById(smoke.getCheckedRadioButtonId());
            selectedAlcohol = (RadioButton) findViewById(alcohol.getCheckedRadioButtonId());
            selectedAllergia = (RadioButton) findViewById(allergia.getCheckedRadioButtonId());
            selectedMedicine = (RadioButton) findViewById(medicine.getCheckedRadioButtonId());

            userLifeStyle = new DataTransferObjects.UserLifeStyle(userValue, Float.parseFloat(txtHeight.getText().toString()),
                    Float.parseFloat(txtWeight.getText().toString()), Byte.parseByte(meslek.getPrompt().toString()),
                    Byte.parseByte(medeniDurum.getPrompt().toString()), timeFormatter.parse(txtUyanmaSaati.getText().toString()),
                    timeFormatter.parse(txtUyumaSaati.getText().toString()),
                    selectedSportActivity.getText().toString().matches("Var"), Byte.parseByte(sporSikligi.getPrompt().toString()),
                    timeFormatter.parse(txtSporSuresi.getText().toString()),
                    selectedSnackAfterBreakfast.getText().toString().matches("Evet"),
                    timeFormatter.parse(txtKahvaltiZamani.getText().toString()),
                    selectedSnackAfterlunch.getText().toString().matches("Evet"),
                    timeFormatter.parse(txtOgleYemegiZamani.getText().toString()),
                    selectedSnackAfterDinner.getText().toString().matches("Evet"),
                    timeFormatter.parse(txtAksamYemegiZamani.getText().toString()),
                    selectedSeyahat.getText().toString().matches("Evet"), Float.parseFloat(txtWater.getText().toString()),
                    selectedSmoke.getText().toString().matches("Evet"), selectedAllergia.getText().toString().matches("Evet"),
                    selectedMedicine.getText().toString().matches("Evet"), txtComment.getText().toString(),
                    selectedAlcohol.getText().toString().matches("Evet"));
        } catch (ParseException ex) {
            Toast.makeText(this, "Başarısız", Toast.LENGTH_LONG).show();
            return;
        }

        if (userValue.matches("") == false)
        {
            Map<String, String> conditions = new LinkedHashMap<>();
            conditions.put("USERNAME",userValue);
                boolean resultLifeStyle = DatabaseQuery.UpdateOrDelete(Enums.PhpSqlOperation.UPDATE,"USERLIFESTYLE",
                        userLifeStyle.getUserLifeStyleObject(),conditions);

                if (resultLifeStyle)
                    Toast.makeText(this, "Başarılı", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Başarısız", Toast.LENGTH_LONG).show();
        }
        else {
            Intent userIntent = getIntent();

            DataTransferObjects.User newUser = new DataTransferObjects.User(userIntent.getStringExtra("User"),
                    userIntent.getStringExtra("Password"), Converter.StringToDate(userIntent.getStringExtra("BirthDate")),
                    Enums.GetGenderNumber(userIntent.getStringExtra("Gender")), userIntent.getStringExtra("TurkishId"),
                    userIntent.getStringExtra("Name"), userIntent.getStringExtra("Surname"));

            Map<String, String> userObject = newUser.getUserObject();
            boolean result = DatabaseQuery.Insert("USER", userObject);

            if (result) {
                boolean resultLifeStyle = DatabaseQuery.Insert("USERLIFESTYLE", userLifeStyle.getUserLifeStyleObject());

                if (resultLifeStyle)
                    Toast.makeText(this, "Başarılı", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Başarısız", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Başarısız Kullanıcı Tanımı", Toast.LENGTH_LONG).show();

            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.signeduser), userIntent.getStringExtra("User"));
            editor.commit();
        }

        Intent intent = new Intent(Questions.this, ViaDiabet.class);
        startActivity(intent);
    }

    private void getInitialValues(String username) {
        List<Map<Object, Object>> userLifeStyleList = DataTransferObjects.UserLifeStyle.getUserLifeStyleList(username);
        Map<Object, Object> userLifeStyle = userLifeStyleList.get(0);

        txtHeight.setText(userLifeStyle.get("HEIGHT").toString());
        txtWeight.setText(userLifeStyle.get("WEIGHT").toString());
        meslek.setSelection(Integer.valueOf(userLifeStyle.get("PROFESSION").toString()));
        medeniDurum.setSelection(Integer.valueOf(userLifeStyle.get("MARITALSTATUS").toString()));
        txtUyanmaSaati.setText(userLifeStyle.get("WAKEUPTIME").toString());
        txtUyumaSaati.setText(userLifeStyle.get("SLEEPTIME").toString());
        sporSikligi.setSelection(Integer.valueOf(userLifeStyle.get("SPORTSMARTNESS").toString()));
        txtSporSuresi.setText(userLifeStyle.get("SPORTTIME").toString());
        txtKahvaltiZamani.setText(userLifeStyle.get("BREAKFASTTIME").toString());
        txtOgleYemegiZamani.setText(userLifeStyle.get("LUNCHTIME").toString());
        txtAksamYemegiZamani.setText(userLifeStyle.get("DINNERTIME").toString());
        txtWater.setText(userLifeStyle.get("DAILYWATERDRINKS").toString());
        txtComment.setText(userLifeStyle.get("INFORMATION").toString());

        setRadioButtonValue(userLifeStyle.get("HASSPORTACTIVITY").toString(), sportActivity);
        setRadioButtonValue(userLifeStyle.get("HASJOURNEY").toString(), seyahat);
        setRadioButtonValue(userLifeStyle.get("SNACKAFTERBREAKFAST").toString(), snackAfterBreakfast);
        setRadioButtonValue(userLifeStyle.get("SNACKAFTERLUNCH").toString(), snackAfterLunch);
        setRadioButtonValue(userLifeStyle.get("SNACKAFTERDINNER").toString(), snackAfterDinner);
        setRadioButtonValue(userLifeStyle.get("ISSMOKING").toString(), smoke);
        setRadioButtonValue(userLifeStyle.get("HASALLERGIC").toString(), allergia);
        setRadioButtonValue(userLifeStyle.get("USEMEDICINE").toString(), medicine);
        setRadioButtonValue(userLifeStyle.get("HASALCOHOL").toString(), alcohol);
    }

    private void setRadioButtonValue(String userValue, RadioGroup group) {
        if (userValue.matches("1"))
            group.check(R.id.radiobutton_1);
        else
            group.check(R.id.radiobutton_2);
    }
}