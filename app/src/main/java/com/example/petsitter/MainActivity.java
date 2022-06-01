package com.example.petsitter;

import android.content.Intent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_sky_blue)));

        File dir = new File (this.getExternalFilesDir(null) + "");
        if(!dir.exists()){
            dir.mkdirs();
        }
        DB.database = new File(this.getExternalFilesDir(null) + "/database.json");
        try {
            FileWriter fileWriter = new FileWriter(DB.database);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("{\"animals\": {}, \"requests\": {}, \"candidates\": {}}");
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    Intent intent = new Intent(getApplicationContext(),PetOwner_HomePage.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },2000);


    }
}