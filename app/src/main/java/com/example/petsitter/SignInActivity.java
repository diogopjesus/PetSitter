package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        spinner = findViewById(R.id.spinner1);
        arrayAdapter = ArrayAdapter.createFromResource(this,R.array.roles, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public void onClickEvent(View v){
        Intent intent = new Intent(v.getContext(),LoginActivity.class);
        switch(v.getId()){
            case R.id.createAccount_btn:
                startActivity(intent);
                break;

            case R.id.signUp_btn:
                startActivity(intent);
                break;
        }
    }

}