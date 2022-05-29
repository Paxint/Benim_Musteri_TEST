package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class UserProfile extends AppCompatActivity {

    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        btn_logout = (Button) findViewById(R.id.btn_logout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.mybackbutton);
        actionBar.setDisplayHomeAsUpEnabled(true);


        // TextView open_ticket= (TextView) findViewById(R.id.open_ticket);
        // TextView ongoing_ticket= (TextView) findViewById(R.id.ongoing_ticket);
        // TextView solved_ticket= (TextView) findViewById(R.id.solved_ticket);


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,ChooseOneActivity.class);
                startActivity(intent);

            }
        });
 /*
        open_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,OpenFragment.class);
                startActivity(intent);
                finish();

            }
        });

        solved_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,SolvedFragment.class);
                startActivity(intent);
                finish();

            }
        });

        ongoing_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,OngoingFragment.class);
                startActivity(intent);
                finish();

            }
        });

  */


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
}
