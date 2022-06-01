package com.example.petsitter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class PetOwner_HomePage extends AppCompatActivity {

    ArrayList<RequestsPetOwnerModel> requestsPetOwnerModel = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petowner_homepage);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_sky_blue)));

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        setUpRequestsPetOwnerModels();
        RecyclerViewPetOwnerRequestsAdapter adapter = new RecyclerViewPetOwnerRequestsAdapter(this, requestsPetOwnerModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("NonConstantResourceId")
    public void chooseRequestType(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.appCompatButton1:
                intent = new Intent(getApplicationContext(), DogWalkingRequest.class);

                break;
            case R.id.appCompatButton2:
                intent = new Intent(getApplicationContext(), PetOwnerRequests.class);

                break;
            case R.id.appCompatButton3:
                intent = new Intent(getApplicationContext(), PetSittingRequest.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        startActivity(intent);

    }

    public void setUpRequestsPetOwnerModels(){
        ArrayList<JSONObject> requestsDatabase = DB.getRequests();

    }
}