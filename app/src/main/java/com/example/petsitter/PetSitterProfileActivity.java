package com.example.petsitter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PetSitterProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_sitter_profile);
        setActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.candidateProfileFragment,new ProfileFragment()).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.edit_profile, menu);

        // first parameter is the file for icon and second one is menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.editButton:
                Toast.makeText(getApplicationContext(),"Edit Profile",Toast.LENGTH_LONG).show();
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
        titleView.setText("PetSitter Profile");
    }
}