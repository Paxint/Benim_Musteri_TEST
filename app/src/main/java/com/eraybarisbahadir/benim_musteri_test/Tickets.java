package com.eraybarisbahadir.benim_musteri_test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class Tickets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_open);

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_open:
                        selectedFragment = new OpenFragment();
                        break;
                    case R.id.navigation_ongoing:
                        selectedFragment = new OngoingFragment();
                        break;
                    case R.id.navigation_solved:
                        selectedFragment = new SolvedFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                return true;
            }

        });
    }
}