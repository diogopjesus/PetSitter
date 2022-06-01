package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }

    public void onClickEvent(View v){
        Intent intent = null;
        switch(v.getId()) {
            case R.id.login_btn:
                intent = new Intent(v.getContext(),PetOwner_HomePage.class);
                startActivity(intent);
                break;
            case R.id.signIn_btn:
                intent = new Intent(v.getContext(),SignInActivity.class);
                startActivity(intent);
                break;
        }
    }
}