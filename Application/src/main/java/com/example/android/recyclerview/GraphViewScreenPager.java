package com.example.android.recyclerview;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android.common.activities.MainActTwo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.Stack;


public class GraphViewScreenPager extends  FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);

        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        pagerAdapter = new ScreenSlidePagerAdapter(this);

        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter { //FOR LARGE NUMBERS OF PAGES
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return SecondFragment.newInstance("sldf");
                case 1:
                    return  FirstFragment.newInstance("bla");
                case 3:
                    return new Fragment();
                default:
                    return new Fragment();
            }

        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }


    }


    //------------------------
    public  static class FirstFragment extends Fragment {



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.radar_markerview, container, false);


            return v;
        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);

        }
        public static FirstFragment newInstance(String text) {

            FirstFragment f = new FirstFragment();
            Bundle b = new Bundle();
            b.putString("msg", text);

            f.setArguments(b);

            return f;
        }

    }


    //----------------------------------------------------------------------------------------------------
    public static class SecondFragment extends Fragment implements OnChartValueSelectedListener {
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



        public static SecondFragment newInstance(String text) {

            SecondFragment f = new SecondFragment();
            Bundle b = new Bundle();
            b.putString("msg", text);

            f.setArguments(b);

            return f;
        }

        @Override
        public void onCreate(Bundle sis) {
            super.onCreate(sis);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
                                 savedInstanceState){
            View rootView = inflater.inflate(R.layout.info_recyler_frag,container,false);
            rootView.setTag(TAG);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

            mLayoutManager= new LinearLayoutManager(getActivity());
            mCurrentLayoutManagerType =LayoutManagerType.LINEAR_LAYOUT_MANAGER;

            if (savedInstanceState != null){
                mCurrentLayoutManagerType = (LayoutManagerType)savedInstanceState
                        .getSerializable(KEY_LAYOUT_MANAGER);

            }
            setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

            ArrayList<Object> items3 = new ArrayList<>();
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());
            items3.add(new TeamBuilderSet());

            Object main = getActivity();
            main = (GraphViewScreenPager ) main;

            mAdapter = new CustomAdapter(getContext(), mDataset, items3, main);
            //set custom adapter
            mRecyclerView.setAdapter(mAdapter);


            return rootView ;
        }
        @Override
        public void onValueSelected(Entry e, Highlight h) {

        }

        @Override
        public void onNothingSelected() {

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

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            Intent myIntent = new Intent(getContext(), RecyclerViewFragment.class);
            startActivityForResult(myIntent, 0);
            return true;
        }


    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}



