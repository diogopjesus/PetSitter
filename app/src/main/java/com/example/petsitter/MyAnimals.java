package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MyAnimals extends AppCompatActivity {
    ArrayList<MyAnimalsModel> myAnimalsModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_animals);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_sky_blue)));
        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        setUpMyAnimalsModel();
        RecyclerViewMyAnimalsAdapter adapter = new RecyclerViewMyAnimalsAdapter(this, myAnimalsModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AddAnimal.class));
            }
        });
    }

    public void setUpMyAnimalsModel(){
        ArrayList<JSONObject> requestsDatabase = DB.getAnimals();
        String animalName, animalType;
        for(JSONObject request: requestsDatabase){
            animalName = (String)request.get("name");
            animalType = (String) request.get("type");
            myAnimalsModel.add(new MyAnimalsModel(animalName,animalType));
        }
    }
}