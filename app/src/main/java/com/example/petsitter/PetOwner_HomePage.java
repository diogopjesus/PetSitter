package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PetOwner_HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petowner_homepage);


    }

    @SuppressLint("NonConstantResourceId")
    public void chooseRequestType(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.appCompatButton1:
                intent = new Intent(getApplicationContext(), DogWalkingRequest.class);

                break;
            case R.id.appCompatButton2:
                intent = new Intent(getApplicationContext(), PetHostingRequest.class);

                break;
            case R.id.appCompatButton3:
                intent = new Intent(getApplicationContext(), PetSittingRequest.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        startActivity(intent);

    }
}