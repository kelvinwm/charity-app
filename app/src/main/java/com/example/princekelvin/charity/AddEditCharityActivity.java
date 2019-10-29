package com.example.princekelvin.charity;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.princekelvin.charity.Model.CharityModel;
import com.example.princekelvin.charity.ViewModel.CharityViewModel;

import java.util.Calendar;

public class AddEditCharityActivity extends AppCompatActivity {

    CharityViewModel charityViewModel;
    TextInputEditText addTitle, addTargetAmount, Description;
    TextView addStartDate, addEndDate;
    Button chooseStartDate, chooseEndDate, submit;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_charity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        charityViewModel = ViewModelProviders.of(this).get(CharityViewModel.class);
//        CharityModel charityModel = getIntent("sddd");

        addTitle = findViewById(R.id.addTitle);
        addTargetAmount = findViewById(R.id.addTargetAmount);
        Description = findViewById(R.id.Description);
        addStartDate = findViewById(R.id.addStartDate);
        addEndDate = findViewById(R.id.addEndDate);

        TextView textView = (TextView) toolbar.findViewById(R.id.toolbarTextView);

        //Buttons
        chooseStartDate = findViewById(R.id.chooseStartDate);
        chooseEndDate = findViewById(R.id.chooseEndDate);
        submit = findViewById(R.id.submit);
        final Intent intent = getIntent();
        final String formatType = intent.getStringExtra("formatType");

        if (formatType.contains("EDIT")) {
            textView.setText("Edit Charity Event");

            getSupportActionBar().setDisplayShowTitleEnabled(false);

            String title = intent.getStringExtra("TITLE");
            String startDate = intent.getStringExtra("sDATE");
            String endDate = intent.getStringExtra("eDate");
            String targetAmount = intent.getStringExtra("TargetAmount");
            String description1 = intent.getStringExtra("Description");

            addTitle.setText(title);
            addStartDate.setText(startDate);
            addEndDate.setText(endDate);
            addTargetAmount.setText(targetAmount);
            Description.setText(description1);
        } else {
            textView.setText("Add New Charity Event");
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = addTitle.getText().toString().trim();
                String target = addTargetAmount.getText().toString().trim();
                String description = Description.getText().toString().trim();
                String startDate = addStartDate.getText().toString().trim();
                String endDate = addEndDate.getText().toString().trim();

                if (title.isEmpty() || target.isEmpty() || description.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    Toast.makeText(AddEditCharityActivity.this, "Kindly add all the required information",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                String StartDate;
                if (startDate.contains("Start Date: ")) {
                    StartDate = startDate;
                } else {
                    StartDate = "Start Date: " + startDate;
                }
                CharityModel charityModel = new CharityModel(title, StartDate, endDate, target, description);
                if (formatType.contains("EDIT")) {
                    int id = intent.getIntExtra("Myid", -1);
                    charityModel.setId(id);
                    charityViewModel.updateCharityItem(charityModel);
                    Toast.makeText(AddEditCharityActivity.this, "Charity event updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    charityViewModel.insertCharityItem(charityModel);
                    Toast.makeText(AddEditCharityActivity.this, "New Charity event created successfully", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        chooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Choose start date
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddEditCharityActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                addStartDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        chooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Choose end date
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddEditCharityActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                addEndDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
    }

}
