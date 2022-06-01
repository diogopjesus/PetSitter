package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class PetOwnerRequests extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrequests_petowner);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(adapter);

    }
}