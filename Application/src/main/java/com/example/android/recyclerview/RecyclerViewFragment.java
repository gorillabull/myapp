/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.recyclerview;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class RecyclerViewFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 4;
    private static final int DATASET_COUNT = 60;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;
    protected BoomMenuButton boomMenuButton ;
    private int hello ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hello = getArguments().getInt("key1");
        Toast.makeText(getContext() ,String.valueOf(getArguments().getInt("key1")),
                Toast.LENGTH_LONG).show();

        Log.v("BIG_TAG_lol",String.valueOf(hello));

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }
    //Constructor
    public RecyclerViewFragment(int a){



    }

    public RecyclerViewFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView ;
        switch (hello) {
            case 1:
                rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
                rootView.setTag(TAG);
                // BEGIN_INCLUDE(initializeRecyclerView)
                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
                // LinearLayoutManager is used here, this will layout the elements in a similar fashion
                // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
                // elements are laid out.
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

                if (savedInstanceState != null) {
                    // Restore saved layout manager type.
                    mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                            .getSerializable(KEY_LAYOUT_MANAGER);
                }
                setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

                ArrayList<Object> items  = new ArrayList<>();
                items.addAll(Arrays.asList(mDataset));

                mAdapter = new CustomAdapter(getContext(), mDataset, items);
                // Set CustomAdapter as the adapter for RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
                // END_INCLUDE(initializeRecyclerView)

                mLinearLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.linear_layout_rb);
                mLinearLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
                    }
                });

                mGridLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.grid_layout_rb);
                mGridLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER);
                    }
                });
                break;
            case 2:
                rootView = inflater.inflate(R.layout.info_recyler_frag,container,false) ;
                rootView.setTag(TAG);
                //init recycler view
                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView2);
                // LinearLayoutManager is used here, this will layout the elements in a similar fashion
                // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
                // elements are laid out.
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

                if (savedInstanceState != null) {
                    // Restore saved layout manager type.
                    mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                            .getSerializable(KEY_LAYOUT_MANAGER);
                }
                setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
                ArrayList<Object> items2 = new ArrayList<>();
                items2.add((new UserBox()));
                items2.add(new String("helloo"));
                items2.add(new UserBox());
                items2.add(new TraitDesc1());
                items2.add(new UserBox())   ;
                items2.add(new UserBox())   ;
                items2.add(new TraitDesc1());
                items2.add(new UserBox())   ;
                items2.add(new String("helloo"));
                items2.add(new UserBox())   ;
                mAdapter = new CustomAdapter(getContext(), mDataset, items2);
                //set custom adapter
                mRecyclerView.setAdapter(mAdapter);

        break;
            default:
             rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        }





        return rootView;
    }

    public void setInfoActivityRecyclerViewLayoutManager(LayoutManagerType layoutManagerType){
        int scrollPosition = 0;
        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }
    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getContext(), RecyclerViewFragment.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
