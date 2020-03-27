package com.example.android.common.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.android.recyclerview.CustomAdapter;
import com.example.android.recyclerview.MainActivity;
import com.example.android.recyclerview.R;
import com.example.android.recyclerview.RecyclerViewFragment;
import com.nightonke.boommenu.BoomMenuButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewAnimator;

import com.example.android.common.activities.SampleActivityBase;
import com.example.android.common.logger.Log;
import com.example.android.common.logger.LogFragment;
import com.example.android.common.logger.LogWrapper;
import com.example.android.common.logger.MessageOnlyLogFilter;






public class activity2 extends SampleActivityBase {





    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;
    protected BoomMenuButton boomMenuButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.

        super.onCreate(savedInstanceState);

        setContentView(R.layout.info_activity);

   /*
        imageView = (ImageView) this.findViewById(R.id.imageView);
        imageview2  = (ImageView) this.findViewById(R.id.ImageView2);

        imageView.setImageResource(R.drawable.ic_launcher);
        imageview2.setImageResource(R.drawable.ic_launcher);
    */

        if (savedInstanceState == null){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Bundle args = new Bundle();
            args.putInt("key1",2);
            RecyclerViewFragment fragment = new RecyclerViewFragment();
            fragment.setArguments(args);

            transaction.replace(R.id.info_fragment,fragment);
            transaction.commit();

        }
       /*HIDE SHOW ACTION BAR ON SCROLL
        this.mRecyclerView.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener(){
            int mLastFirstVisible =0;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState){}

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy ){

            }
        });

        */
    }

  /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.info_activity, container) ;

        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView2);

        mLayoutManager = new LinearLayoutManager(getActivity());


        mAdapter = new CustomAdapter(getBaseContext(), null,null);


    }
   */

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
