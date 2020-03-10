package com.example.android.common.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.example.android.recyclerview.MainActivity;

public class activity2 extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.

        super.onCreate(savedInstanceState);
    }

    @Override
    protected  void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
