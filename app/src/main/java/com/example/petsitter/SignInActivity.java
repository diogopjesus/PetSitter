package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
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