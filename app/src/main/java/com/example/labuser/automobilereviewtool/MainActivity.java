package com.example.labuser.automobilereviewtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewRecords(View v) {
        Intent intent = new Intent(this,SummaryView.class);
        startActivity(intent);
    }

    public void addRecord(View v) {

        Intent intent = new Intent(this,AddRecord.class);
        startActivity(intent);
    }



}


