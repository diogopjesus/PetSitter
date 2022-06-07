package com.example.petsitter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        EditText userName = findViewById(R.id.userName);
        findViewById(R.id.signUp_btn).setOnClickListener(view -> startActivity(new Intent(view.getContext(),SignUpActivity.class)));

        findViewById(R.id.login_btn).setOnClickListener(view -> {
            if (userName.getText().toString().equals("alberto12345"))
                startActivity(new Intent(view.getContext(),PetOwnerMainActivity.class));
            else if (userName.getText().toString().equals("carlos12345"))
                startActivity(new Intent(view.getContext(),PetSitterMainActivity.class));
            else
                Toast.makeText(view.getContext(),"Invalid credentials", Toast.LENGTH_LONG).show();
        });
    }
}