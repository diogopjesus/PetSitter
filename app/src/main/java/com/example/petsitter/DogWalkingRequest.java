package com.example.petsitter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class DogWalkingRequest extends AppCompatActivity {
    Spinner animals, regularity;
    EditText date,time, details;
    ArrayList<String> animalsName = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<CharSequence> adapter1;
    String animalName, regularity1;
    ArrayList<JSONObject> requestsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dogwalking_request);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_sky_blue)));
        try {
            DB.addAnimal("dog","male","pastor","tobi",20.0,"oi",new ArrayList<>());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        animals = findViewById(R.id.animalsSpinner);
        ArrayList<JSONObject> requestsDatabase = DB.getAnimals();
        for(JSONObject request: requestsDatabase){
            animalsName.add((String)request.get("name"));
        }
         adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, animalsName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animals.setAdapter(adapter);
        regularity = findViewById(R.id.regularitySpinner);
        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.regularity, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regularity.setAdapter(adapter1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickEvent(View v){

        animals = findViewById(R.id.animals);
        regularity = findViewById(R.id.regularitySpinner);
        date = findViewById(R.id.requestDate);
        time = findViewById(R.id.requestTime);
        details = findViewById(R.id.requestDetails);

        if (date.getText().toString().equals("") || time.getText().toString().equals("")
                || details.getText().toString().equals("")){
            Toast.makeText(v.getContext(),"All options are required", Toast.LENGTH_SHORT).show();
        }
        else{
            animalName = animals.getSelectedItem().toString();
            int pet_id = 0;
            for (JSONObject request: requestsDatabase) {
                if(animalName.equals((String) request.get("name")))
                    pet_id = (Integer)request.get("request_id");
            }
            regularity1 = regularity.getSelectedItem().toString();
            DB.addDogWalkingEntry("1",pet_id,details.getText().toString(),
                    LocalDate.parse(date.getText().toString()), LocalTime.parse(time.getText().toString()),
                            regularity1);
        }
    }
}