package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.eraybarisbahadir.benim_musteri_test.databinding.ActivityTicketBinding;

public class Tickets extends AppCompatActivity {

     ActivityTicketBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new OpenFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->  {

            switch (item.getItemId()) {
                case R.id.navigation_open:
                    replaceFragment(new OpenFragment());
                    break;
                case R.id.navigation_ongoing:
                    replaceFragment(new OngoingFragment());
                    break;
                case R.id.navigation_solved:
                    replaceFragment(new SolvedFragment());
                    break;

            }

            return true;

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.action_bar_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.user_profile) {
            Intent intent=new Intent(Tickets.this,UserProfile.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();


    }
}