package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerChooseActivity extends AppCompatActivity {

    private Button musteriCreateClick;
    private Button musteriFollowClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_choose_one);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.mybackbutton);
        actionBar.setDisplayHomeAsUpEnabled(true);


        musteriCreateClick =(Button) findViewById(R.id.buttonmchoose1);
        musteriCreateClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                openMusteriTicketCreatePage();
            }
        });

        musteriFollowClick = (Button) findViewById(R.id.buttonmchoose2);
        musteriFollowClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openMusteriTicketFollowPage();   }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMusteriTicketCreatePage(){
        Intent intent=new Intent(this, musteriTicketCreation.class);
        startActivity(intent);
    }
    public void openMusteriTicketFollowPage(){
        Intent intent=new Intent(this, musteriTicketFollow.class);
        startActivity(intent);
    }
}
