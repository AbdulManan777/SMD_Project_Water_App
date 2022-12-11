package com.ass2.water_reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ass2.water_reminderapp.databinding.Activity3Binding;
import com.ass2.water_reminderapp.databinding.ActivityMainBinding;

public class Activity3 extends AppCompatActivity {

    Activity3Binding binding;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        username=getIntent().getStringExtra("Username");





        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home)
                replaceFragment(new HomeFragment());

            else if (item.getItemId() == R.id.services) {



                replaceFragment(new ServicesFragment());
            }

            else if (item.getItemId() == R.id.settings)
                replaceFragment(new SettingsFragment());


            return true;

        });
    }

    private void replaceFragment(Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}