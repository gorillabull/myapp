package com.example.android.common.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.recyclerview.MainActivity;
import com.example.android.recyclerview.R;

public class activity2 extends FragmentActivity {
    private  ImageView imageView;
    private ImageView imageview2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.

        super.onCreate(savedInstanceState);

        setContentView(R.layout.info_activity);

        imageView = (ImageView) this.findViewById(R.id.imageView);
        imageview2  = (ImageView) this.findViewById(R.id.ImageView2);

        imageView.setImageResource(R.drawable.ic_launcher);
        imageview2.setImageResource(R.drawable.ic_launcher);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

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
