package com.eraybarisbahadir.benim_musteri_test;

import android.os.Bundle;

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

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();


    }
}