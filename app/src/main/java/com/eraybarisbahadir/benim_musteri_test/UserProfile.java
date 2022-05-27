package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class UserProfile extends AppCompatActivity {

    Button btn_logout;
    Button open_ticket;
    Button solved_ticket;
    Button ongoing_ticket;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        btn_logout = (Button) findViewById(R.id.btn_logout);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.mybackbutton);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);




       /*

        open_ticket= (Button) findViewById(R.id.open_ticket);
        ongoing_ticket= (Button) findViewById(R.id.ongoing_ticket);
        solved_ticket= (Button) findViewById(R.id.solved_ticket);

        */
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

    // this event will enable the back
    // function to the button on press
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
