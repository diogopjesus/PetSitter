package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class AddAnimal extends AppCompatActivity {
    EditText animalName, animalSize, animalDescription;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner spinnerAnimalType, spinnerAnimalBreed;
    String animalType, animalBreed, animalSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_animal);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_sky_blue)));
        spinnerAnimalType = findViewById(R.id.animalType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animals, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnimalType.setAdapter(adapter);
        spinnerAnimalType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinnerAnimalBreed = findViewById(R.id.animalBreed);
                ArrayAdapter<CharSequence> adapter1;
                switch(i){
                    case 0:
                        adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                                R.array.cats_array, android.R.layout.simple_spinner_item);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerAnimalBreed.setAdapter(adapter1);
                        break;
                    case 1:
                        adapter1 = ArrayAdapter.createFromResource(view.getContext(),
                                R.array.dogs_array, android.R.layout.simple_spinner_item);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerAnimalBreed.setAdapter(adapter1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onClickEvent(View v){
        radioGroup= findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);
        spinnerAnimalType = findViewById(R.id.animalType);
        spinnerAnimalBreed = findViewById(R.id.animalBreed);
        animalName = findViewById(R.id.animalName);
        animalSize = findViewById(R.id.animalSize);
        animalDescription = findViewById(R.id.animalDescription);

        if (animalName.getText().toString().equals("") || animalSize.getText().toString().equals("")
                || radioButton == null){
            Toast.makeText(v.getContext(),"All options are required", Toast.LENGTH_SHORT).show();
        }
        else{
            animalType = spinnerAnimalType.getSelectedItem().toString();
            animalBreed = spinnerAnimalType.getSelectedItem().toString();
            animalSex = radioButton.getText().toString();
            try {
                DB.addAnimal(animalType, animalSex, animalBreed, animalName.getText().toString(),
                        Double.parseDouble(animalSize.getText().toString()), animalDescription.getText().toString(),
                        new ArrayList<>());
                startActivity(new Intent(v.getContext(), MyAnimals.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}