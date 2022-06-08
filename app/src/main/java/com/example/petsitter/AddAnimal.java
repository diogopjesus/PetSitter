package com.example.petsitter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class AddAnimal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] types = {"Animal...", "Dog", "Cat"};
    String[] dogBreeds = {"Breed...", "Labrador", "Cocker", "Pincher"};
    String[] catBreeds = {"Breed...", "Sphynx", "Persian", "Siamese"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        setActionBar();
        addAnimalTypesSpinner();
        addAnimalBreedSpinner();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setActionBar() {
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM|ActionBar.DISPLAY_SHOW_TITLE);

        actionBar.setCustomView(R.layout.abs_layout);

        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView titleView = findViewById(R.id.absLayout);
        titleView.setText("New Animal");
    }

    private void addAnimalTypesSpinner() {
        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        Spinner spin = findViewById(R.id.animalTypeSpinner);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                R.layout.custom_textview_to_spinner,
                types);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.setAdapter(ad);
    }

    private void addAnimalBreedSpinner() {
        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        Spinner spin = findViewById(R.id.animalBreedSpinner);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(this, R.layout.custom_textview_to_spinner, types);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.setAdapter(ad);
    }

    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = findViewById(R.id.animalBreedSpinner);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter ad;
        if(parent.getSelectedItem().toString().equals("Dog")) {
            ad = new ArrayAdapter(this, R.layout.custom_textview_to_spinner, dogBreeds);
            // set simple layout resource file
            // for each item of spinner
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Set the ArrayAdapter (ad) data on the
            // Spinner which binds data to spinner
            spin.setAdapter(ad);
        } else if (parent.getSelectedItem().toString().equals("Cat")) {
            ad = new ArrayAdapter(this, R.layout.custom_textview_to_spinner, catBreeds);
            // set simple layout resource file
            // for each item of spinner
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Set the ArrayAdapter (ad) data on the
            // Spinner which binds data to spinner
            spin.setAdapter(ad);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}