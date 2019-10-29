package com.example.princekelvin.charity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewCharityActivity extends AppCompatActivity {

    TextView Title, sDate, eDATE, tAmount, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_charity);

        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String startDate = intent.getStringExtra("sDATE");
        String endDate = intent.getStringExtra("eDate");
        String targetAmount = intent.getStringExtra("TargetAmount");
        String Description = intent.getStringExtra("Description");

        Title = findViewById(R.id.theTitle);
        sDate = findViewById(R.id.sDate);
        eDATE = findViewById(R.id.endDate);
        tAmount = findViewById(R.id.targetAmount);
        description = findViewById(R.id.theDescription);

        String endD = "End Date: "+endDate;
        String targetAmt = "Target Amount: Ksh."+targetAmount;

        Title.setText(title);
        sDate.setText(startDate);
        eDATE.setText(endD);
        tAmount.setText(targetAmt);
        description.setText(Description);

    }
}
