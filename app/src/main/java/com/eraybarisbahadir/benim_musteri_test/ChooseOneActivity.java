package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ChooseOneActivity extends AppCompatActivity {
    private Button kurumLoginClick;
    private Button musteriLoginClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);
        onBackPressed();


        kurumLoginClick = (Button) findViewById(R.id.buttonk1);
        kurumLoginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCompanyLogin();
            }
        });

        musteriLoginClick = (Button) findViewById(R.id.buttonm1);
        musteriLoginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomerLogin();
            }
        });

    }

    public void openCompanyLogin() {
        Intent intent = new Intent(this, kurumLogin.class);
        startActivity(intent);
    }

    public void openCustomerLogin() {
        Intent intent = new Intent(this, CustomerChooseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }

}