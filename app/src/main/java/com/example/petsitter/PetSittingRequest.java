package com.example.petsitter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

public class PetSittingRequest extends AppCompatActivity {
    Spinner animals;
    EditText from,until, details, nVisitsDay, location;
    ArrayList<String> animalsName = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String animalName;
    ArrayList<JSONObject> requestsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petsitting_request);
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
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickEvent(View v){

        animals = findViewById(R.id.animalsSpinner);
        from = findViewById(R.id.from);
        until = findViewById(R.id.until);
        details = findViewById(R.id.details);
        nVisitsDay = findViewById(R.id.nVisitsDay);
        location = findViewById(R.id.location);
        if (from.getText().toString().equals("") || until.getText().toString().equals("")
                || details.getText().toString().equals("") || nVisitsDay.getText().toString().equals("")
                    || location.getText().toString().equals("")){
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
                DB.addPetSittingEntry("1",pet_id,details.getText().toString(),
                        LocalDate.parse(from.getText().toString()), LocalDate.parse(until.getText().toString()),
                            Integer.parseInt(nVisitsDay.getText().toString()),location.getText().toString());
                startActivity(new Intent(v.getContext(), PetOwnerRequests.class));
            } catch (DateTimeParseException e){
                Toast.makeText(v.getContext(),"Dates fields must have this format \" yyyy-mm-dd\"", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e){
                Toast.makeText(v.getContext(),"Number of visits must have only numbers", Toast.LENGTH_SHORT).show();
            }

        }
    }
}