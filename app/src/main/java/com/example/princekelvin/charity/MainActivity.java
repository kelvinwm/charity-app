package com.example.princekelvin.charity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.princekelvin.charity.Adapter.ChariryAdapter;
import com.example.princekelvin.charity.Model.CharityModel;
import com.example.princekelvin.charity.ViewModel.CharityViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private CharityViewModel charityViewModel;
    private ChariryAdapter chariryAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.charityRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        charityViewModel = ViewModelProviders.of(this).get(CharityViewModel.class);


        getAllCharities();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCharity = new Intent(MainActivity.this, AddEditCharityActivity.class);
                addCharity.putExtra("formatType", "AddCharity");
                startActivity(addCharity);
            }
        });
    }

    private void getAllCharities() {
        charityViewModel.getAllCharities().observe(this, new Observer<List<CharityModel>>() {
            @Override
            public void onChanged(List<CharityModel> charityModels) {
                chariryAdapter = new ChariryAdapter((ArrayList<CharityModel>) charityModels, MainActivity.this);
                recyclerView.setAdapter(chariryAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
