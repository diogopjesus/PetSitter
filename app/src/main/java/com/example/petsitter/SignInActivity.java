package com.example.petsitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SignInActivity extends AppCompatActivity {

    EditText fName, lName, email_phoneNumber, password, reenter_password;
    RadioButton radioButton;
    RadioGroup role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        email_phoneNumber = findViewById(R.id.eMail_phoneNumber);
        password = findViewById(R.id.password);
        reenter_password = findViewById(R.id.reEnterPass);
        role = findViewById(R.id.radioGroup);
    }

    public void checkRole(View v){
        int radioId = role.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

    }
    public void onClickEvent(View v){
        Intent intent = new Intent(v.getContext(),LoginActivity.class);
        switch(v.getId()){
            case R.id.createAccount_btn:
                if (fName.getText().toString().equals("") || lName.getText().toString().equals("")
                        || email_phoneNumber.getText().toString().equals("") ||password.getText().toString().equals("")
                            || reenter_password.getText().toString().equals("") || radioButton == null){
                    Toast.makeText(v.getContext(),"All options are required", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(reenter_password)){
                    Toast.makeText(v.getContext(),"Oi", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(intent);
                }
                break;

            case R.id.signUp_btn:
                startActivity(intent);
                break;
        }
    }


}