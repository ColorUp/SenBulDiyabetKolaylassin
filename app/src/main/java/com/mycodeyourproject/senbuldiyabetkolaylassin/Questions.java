package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Burak on 27.07.2015.
 */
public class Questions extends BaseViaDiabetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        this.SetProgressBarTextValueAndShow("Yaşam Tarzı (2/2)", 100);

        Button buttonSignUp = (Button) findViewById(R.id.button_mainpage);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Questions.this, ViaDiabet.class);
                startActivity(intent);
            }
        });
    }
}