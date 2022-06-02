package com.example.petsitter;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
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

        DB.addAnimal("dog","male","pastor","tobi",20.0,"oi",new ArrayList<>());

        animals = findViewById(R.id.animalsSpinner);
        requestsDatabase = DB.getAnimals();

        assert requestsDatabase != null;
        for(JSONObject request: requestsDatabase){
            animalsName.add((String)request.get("name"));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,animalsName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animals.setAdapter(adapter);
        regularity = findViewById(R.id.regularitySpinner);
        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.regularity, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regularity.setAdapter(adapter1);
    }

    public void onClickEvent(View v){

        animals = findViewById(R.id.animalsSpinner);
        regularity = findViewById(R.id.regularitySpinner);
        date = findViewById(R.id.requestDate);
        time = findViewById(R.id.requestTime);
        details = findViewById(R.id.requestDetails);

        if (date.getText().toString().equals("") || time.getText().toString().equals("")
                || details.getText().toString().equals("")){
            Toast.makeText(v.getContext(),"All options are required", Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                animalName = animals.getSelectedItem().toString();
                int pet_id = 0;
                requestsDatabase = DB.getAnimals();
                assert requestsDatabase != null;
                for (JSONObject request : requestsDatabase) {
                    if (animalName.equals((String) request.get("name")))
                        pet_id = ((Long) request.get("animal_id")).intValue();
                }
                regularity1 = regularity.getSelectedItem().toString();
                DB.addDogWalkingEntry("1", pet_id, details.getText().toString(),
                        LocalDate.parse(date.getText().toString()), LocalTime.parse(time.getText().toString()),
                        regularity1);
                startActivity(new Intent(v.getContext(), PetOwnerRequests.class));
            } catch (DateTimeParseException e){
                Toast.makeText(v.getContext(),"Date field must have this format \" yyyy-mm-dd\" and " +
                        "Time field must have this format \" hh:mm\"", Toast.LENGTH_SHORT).show();
            }

        }
    }
}