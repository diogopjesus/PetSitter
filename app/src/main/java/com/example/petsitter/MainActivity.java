package com.example.petsitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int i = 0;

        while (i < 10){
            i++;
        }

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }
}