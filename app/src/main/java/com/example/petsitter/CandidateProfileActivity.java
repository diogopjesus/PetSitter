package com.example.petsitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CandidateProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_profile);
        setActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.candidateProfileFragment, new ProfileFragment()).commit();

        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");

        if (!activity.equals("CandidatesActivity")) {
            findViewById(R.id.chooseCandidate).setVisibility(View.INVISIBLE);
            findViewById(R.id.removeCandidate).setVisibility(View.INVISIBLE);
        }
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
        titleView.setText("Candidate Profile");
    }
}