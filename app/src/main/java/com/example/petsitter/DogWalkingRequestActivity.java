package com.example.petsitter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.AlertDialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;


public class DogWalkingRequestActivity extends AppCompatActivity {

    // animal list
    TextView animalListTextView;
    String[] animalArray;
    ArrayList<Integer> animalList = new ArrayList<>();
    boolean[] selectedAnimals;

    // hour picker
    TextView timeButton;
    int hour, minute;

    // date picker
    private DatePickerDialog datePickerDialog;
    private TextView dateButton;

    // regularity list
    TextView regularityListTextView;
    String[] regularityArray;
    ArrayList<Integer> regularityList = new ArrayList<>();
    boolean[] selectedRegularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_walking_request);
        setActionBar();


        // Add Animals to form
        addAnimalList();

        // Add Time Picker
        addTimePicker();

        // add Date Picker
        initDatePicker();
        addDatePicker();

        // add Regularity
        addRegularityList();
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
        titleView.setText("Dog Walking Request");
    }

    private void addAnimalList() {
        animalListTextView = findViewById(R.id.animalListTextView);
        animalArray = new String[]{"Oscar", "Pedro"};
        selectedAnimals = new boolean[animalArray.length];

        animalListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(DogWalkingRequestActivity.this);

                // set title
                builder.setTitle("Select Animal(s)");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(animalArray, selectedAnimals, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            animalList.add(i);
                            // Sort array list
                            Collections.sort(animalList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            animalList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < animalList.size(); j++) {
                            // concat array value
                            stringBuilder.append(animalArray[animalList.get(j)]);
                            // check condition
                            if (j != animalList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        animalListTextView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedAnimals.length; j++) {
                            // remove all selection
                            selectedAnimals[j] = false;
                            // clear language list
                            animalList.clear();
                            // clear text view value
                            animalListTextView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }

    private void addTimePicker() {
        timeButton = findViewById(R.id.timeButton);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                };

                // int style = AlertDialog.THEME_HOLO_DARK;

                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), /*style,*/ onTimeSetListener, hour, minute, true);

                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
    }

    private void addDatePicker() {
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        return month < 1 || month > 12 ? "JAN" : months[month-1];
    }

    private void addRegularityList() {
        regularityListTextView = findViewById(R.id.regularityListTextView);
        regularityArray = new String[]{"One Time Request", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        selectedRegularity = new boolean[regularityArray.length];

        regularityListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(DogWalkingRequestActivity.this);

                // set title
                builder.setTitle("Select Regularity");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(regularityArray, selectedRegularity, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            regularityList.add(i);
                            // Sort array list
                            Collections.sort(regularityList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            regularityList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < regularityList.size(); j++) {
                            // concat array value
                            stringBuilder.append(regularityArray[regularityList.get(j)]);
                            // check condition
                            if (j != regularityList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        regularityListTextView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedRegularity.length; j++) {
                            // remove all selection
                            selectedRegularity[j] = false;
                            // clear language list
                            regularityList.clear();
                            // clear text view value
                            regularityListTextView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }
}