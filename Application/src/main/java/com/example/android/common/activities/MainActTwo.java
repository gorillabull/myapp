package com.example.android.common.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.recyclerview.ChampBase;
import com.example.android.recyclerview.ChampStatsAxisFormatter;
import com.example.android.recyclerview.CustomAdapter;
import com.example.android.recyclerview.ItemBase;
import com.example.android.recyclerview.ItemBuilderSet;
import com.example.android.recyclerview.R;
import com.example.android.recyclerview.RadarGraphActivity;
import com.example.android.recyclerview.RecyclerViewFragment;
import com.example.android.recyclerview.TeamBuilderSet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import   com.example.android.recyclerview.XYMarkerView;
import   com.example.android.recyclerview.DayAxisValueFormatter;

import   com.example.android.recyclerview.MyValueFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;

import com.example.android.recyclerview.Fill;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.Orientation;




import com.github.mikephil.charting.utils.Utils;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

public class MainActTwo extends FragmentActivity implements
        OnChartValueSelectedListener{
    private BarChart chart;
    private ImageView imageView1;
    private ImageView imageView2;
    private Stack<Integer> emptySlots;
    private Stack<ChampDisplayTuple> champsAdded; // delete

    private SearchableList champsClicked;


    private ImageView [] champ_slots;
    private ImageView [] itemSlotsForChamps;
    private ItemBase [] itemsAddedToChamps;
    private ChampBase iconsDb;

    protected Typeface tfRegular;
    protected Typeface tfLight;

    private ArrayList<ChampBase> champsSelected_ForGraph;
    private Integer []  familySets;
    private Integer []  subFamilySets;
    private Integer []  familySetBonusCount;

    /**
     * Keeps track of which view the user is currently on (item or champ)
     */
    private Integer     viewSelectedState;


    private final int BLADEMASTER =1;
    private final int BLASTER = 2;
    private final int BRAWLER = 3;
    private final int CELESTIAL = 4;
    private final int CHRONO = 5;
    private final int CYBERNETIC = 6;
    private final int DARK_STAR = 7;
    private final int DEMOLITIONIST = 8;
    private final int MANA_REAVER =9 ;
    private final int MECH_PILOT = 10;
    private final int MERCENARY =11 ;
    private final int MYSTIC = 12;
    private final int PROTECTOR = 13;
    private final int REBEL = 14;
    private final int SNIPER = 0;
    private final int SORCERER =15 ;
    private final int SPACE_PIRATE = 16;
    private final int STAR_GUARDIAN = 17;
    private final int STARSHIP = 18;
    private final int VALKYRIA = 19;
    private final int VANGUARD = 20;
    private final int VOID =    21;

    private final int chart_HEALTH_MAX = 2000;
    private final int chart_ATTACK_MAX = 150;
    private final int chart_ARMOR = 60;
    private final int chart_SPELLRES = 20 ;


    private ArrayList<ArrayList<Integer>> traitBonuses;

    private PopupWindow popupWindow;
    LinearLayout popupLayout;
    LinearLayout.LayoutParams popupParams;

    /**Viewpager stuff
     *
     */
    private ViewPager2 viewPager2;
    private static final int NUM_PAGES = 2;
    private FragmentStateAdapter pagerAdapter;



    private BoomMenuButton boomMenuButtonNav;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;

    private Integer champSelectedForItems;

    private ArrayList<Integer> slotCounterArray;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am_two  );

        champ_slots = new ImageView[10];
        itemSlotsForChamps = new ImageView[30]; //3 items per champ


        emptySlots = new Stack<>()  ;

        champsAdded = new Stack<>();

        champsClicked = new SearchableList();

        iconsDb = new ChampBase();

        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        champsSelected_ForGraph = new ArrayList<>();

        familySets = new Integer[22];

        subFamilySets = new Integer[22];
        familySetBonusCount = new Integer[22];

        viewSelectedState = 0;
        for (int i=0; i<22; i++){
            familySetBonusCount[i] =0;
            familySets[i] =0;
            subFamilySets[i] = 0;
        }

traitBonuses = new ArrayList<>();
        Integer [] blademastertraits = new Integer[3];
        blademastertraits[0] = 0;
        blademastertraits[1] = 2;
        blademastertraits[2] = 3;

        traitBonuses    .add(new ArrayList<>(Arrays.asList(blademastertraits)));

       // ActionBar actionBar = getActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(false   );
       // actionBar.hide();




        setTitle("Team Power Calculator");
        champ_slots[0] = (ImageView)findViewById(R.id.imageView3_1);
        champ_slots[1] = (ImageView)findViewById(R.id.imageView4);
        champ_slots[2] = (ImageView)findViewById(R.id.imageView5);
        champ_slots[3] = (ImageView)findViewById(R.id.imageView6);
        champ_slots[4] = (ImageView)findViewById(R.id.imageView7);
        champ_slots[5] = (ImageView)findViewById(R.id.imageView8);
        champ_slots[6] = (ImageView)findViewById(R.id.imageView10);
        champ_slots[7] = (ImageView)findViewById(R.id.imageView11);
        champ_slots[8] = (ImageView)findViewById(R.id.imageView12);
        champ_slots[9] = (ImageView)findViewById(R.id.imageView13);

        champSelectedForItems =0;
        slotCounterArray = new ArrayList<>(10);
        itemsAddedToChamps = new ItemBase[30];//holds items user adds to champions on board.

        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);
        slotCounterArray.add(0);

        //-------------------
        itemSlotsForChamps[0] = (ImageView)findViewById(R.id.ch1icon1);
        itemSlotsForChamps[1] = (ImageView)findViewById(R.id.ch1icon2);
        itemSlotsForChamps[2] = (ImageView)findViewById(R.id.ch1icon3);
        itemSlotsForChamps[3 ] = (ImageView)findViewById(R.id.ch2icon1);
        itemSlotsForChamps[4 ] = (ImageView)findViewById(R.id.ch2icon2);
        itemSlotsForChamps[5 ] = (ImageView)findViewById(R.id.ch2icon3);
        itemSlotsForChamps[6 ] = (ImageView)findViewById(R.id.ch3icon1);
        itemSlotsForChamps[7 ] = (ImageView)findViewById(R.id.chi3icon2);
        itemSlotsForChamps[8 ] = (ImageView)findViewById(R.id.ch3icon3);
        itemSlotsForChamps[9 ] = (ImageView)findViewById(R.id.ch4icon1);
        itemSlotsForChamps[10] = (ImageView)findViewById(R.id.ch4icon2);
        itemSlotsForChamps[11] = (ImageView)findViewById(R.id.ch4icon3);
        itemSlotsForChamps[12] = (ImageView)findViewById(R.id.ch5icon1);
        itemSlotsForChamps[13] = (ImageView)findViewById(R.id.ch5icon2);
        itemSlotsForChamps[14] = (ImageView)findViewById(R.id.ch5icon3);
        itemSlotsForChamps[15] = (ImageView)findViewById(R.id.ch6icon1);
        itemSlotsForChamps[16] = (ImageView)findViewById(R.id.ch6icon2);
        itemSlotsForChamps[17] = (ImageView)findViewById(R.id.ch6icon3);
        itemSlotsForChamps[18] = (ImageView)findViewById(R.id.ch7icon1);
        itemSlotsForChamps[19] = (ImageView)findViewById(R.id.ch7icon2);
        itemSlotsForChamps[20] = (ImageView)findViewById(R.id.ch7icon3);
        itemSlotsForChamps[21] = (ImageView)findViewById(R.id.ch8icon1);
        itemSlotsForChamps[22] = (ImageView)findViewById(R.id.ch8icon2);
        itemSlotsForChamps[23] = (ImageView)findViewById(R.id.ch8icon3);
        itemSlotsForChamps[24] = (ImageView)findViewById(R.id.ch9icon1);
        itemSlotsForChamps[25] = (ImageView)findViewById(R.id.ch9icon2);
        itemSlotsForChamps[26] = (ImageView)findViewById(R.id.ch9icon3);
        itemSlotsForChamps[27] = (ImageView)findViewById(R.id.ch10icon1);
        itemSlotsForChamps[28] = (ImageView)findViewById(R.id.ch10icon2);
        itemSlotsForChamps[29] = (ImageView)findViewById(R.id.ch10icon3);

        for (int i =0; i <itemSlotsForChamps.length;i ++){
            itemSlotsForChamps[i].setImageResource(R.drawable.infinity_edge);
        }

        for(int i=0 ; i < champ_slots.length; i++){
            champ_slots[i].setImageResource(R.drawable.black_background);
            champ_slots[i].setTag(i);
            //mark it so we can index it;
            emptySlots.push(i);
            //stack of available slots
            /**
             * Remove clicked chamption and all its stats
             */
            champ_slots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v = (ImageView) v;
                    int tag = (Integer) v.getTag();

                    //first check which recycler were on
                    if (viewSelectedState == 1) {
                        //in this case, we only select the champ to be assigned items
                        //check if the veiw has a champ in it.
                        if (!emptySlots.contains(tag)) {
                            for (int i = 0; i < champ_slots.length; i++) {
                                champ_slots[i].setAlpha(0.5f);
                            }

                            champ_slots[tag].setAlpha(1f);
                            champSelectedForItems = tag;
                        }
                        return;
                    }
                    if (viewSelectedState == 0) {
                        //clear it
                        //get the tag of the display box image
                        if (!emptySlots.contains(tag)) {
                            ((ImageView) v).setImageResource(R.drawable.black_background);
                            emptySlots.push((Integer) v.getTag());//recycle the empty slot
                            //clear the icon from storage so it can appear again
                            champsClicked.removeByDisplayId(tag);

                            //clear the items
                            itemSlotsForChamps[tag*3].setImageResource(R.drawable.black_background);
                            itemSlotsForChamps[tag*3+1].setImageResource(R.drawable.black_background);
                            itemSlotsForChamps[tag*3+2].setImageResource(R.drawable.black_background);

                            itemsAddedToChamps[(tag*3)] = null;
                            itemsAddedToChamps[(tag*3+1)] = null;
                            itemsAddedToChamps[(tag*3+2)] = null;

                        }

                    }
                }
            });
        }

        {   // // Chart Style // //
            chart = findViewById(R.id.chart1);
            chart.setOnChartValueSelectedListener(this);

            chart.setDrawBarShadow(false);
            chart.setDrawValueAboveBar(true);

            chart.getDescription().setEnabled(false);

            // if more than 60 entries are displayed in the chart, no values will be
            // drawn
            chart.setMaxVisibleValueCount(60);

            // scaling can now only be done on x- and y-axis separately
            chart.setPinchZoom(false);

            chart.setDrawGridBackground(false);
            // chart.setDrawYLabels(false);

            ValueFormatter xAxisFormatter = new ChampStatsAxisFormatter(chart);

            LimitLine ll1 = new LimitLine(80f, "Optimally all bars go past this line.");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            ll1.setTextColor(Color.WHITE);
            ll1.setTypeface(tfRegular);

            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTypeface(tfLight);
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f); // only intervals of 1 day
            xAxis.setLabelCount(7);
            xAxis.setValueFormatter(xAxisFormatter);
            xAxis.setTextColor(Color.WHITE);

            ValueFormatter custom = new MyValueFormatter("%");

            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setTypeface(tfLight);
            leftAxis.setLabelCount(8, false);
            leftAxis.setValueFormatter(custom);
            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            leftAxis.setSpaceTop(15f);
            leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
            leftAxis.setTextColor(Color.WHITE);
            leftAxis.addLimitLine(ll1);

            YAxis rightAxis = chart.getAxisRight();
            rightAxis.setDrawGridLines(false);
            rightAxis.setTypeface(tfLight);
            rightAxis.setLabelCount(8, false);
            rightAxis.setValueFormatter(custom);
            rightAxis.setSpaceTop(15f);
            rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
            rightAxis.setTextColor(Color.WHITE);

            Legend l = chart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            l.setForm(Legend.LegendForm.SQUARE);
            l.setFormSize(9f);
            l.setTextSize(11f);
            l.setXEntrySpace(4f);
            l.setTextColor(Color.WHITE);

            XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
            mv.setChartView(chart); // For bounds control
            chart.setMarker(mv); // Set the marker to the chart
        }


        setData(5,100);
        chart.invalidate();


        viewPager2 = (ViewPager2)findViewById(R.id.dataViewPager);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        pagerAdapter= new ScreenSlidePagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            /**
             * Invoked when the user scrolls to the next or previous pane.
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewSelectedState = position;

                if (viewSelectedState ==0){
                    onItemsSelector(0);

                    return;
                }
                //in this case, heroes selected needs some work.
                if (viewSelectedState ==1){
                    onItemsSelector(1);
                }
                Toast.makeText(MainActTwo.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public boolean equals(@Nullable Object obj) {
                return super.equals(obj);
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @NonNull
            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        });


        if (savedInstanceState == null){
     /*
            FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            Bundle args = new Bundle();

            args.putInt("key1",3);
            RecyclerViewFragment fragment = new RecyclerViewFragment();
            fragment.setArguments(args);

            transaction.replace(R.id.sample_content_fragment3,fragment);
            transaction.commit();
      */
        }


        boomMenuButtonNav  = (BoomMenuButton)findViewById(R.id.bmb123);

        for (int i =0; i< boomMenuButtonNav.getPiecePlaceEnum().pieceNumber(); i++){
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.tft3_ahri_mobile_)
                    .normalText("title").subNormalText("subtitle");
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    //
                    if (index ==1   ){
                        //the radar graph activity
                        Intent startRadarGraphActivity = new Intent(getBaseContext(), RadarGraphActivity.class)  ;
                        startRadarGraphActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


                        startRadarGraphActivity.putExtra("Hp",123f);
                        startRadarGraphActivity.putExtra("Armor",123f);
                        startRadarGraphActivity.putExtra("Stat3",123f);
                        startRadarGraphActivity.putExtra("Stat4",123f);
                        startRadarGraphActivity.putExtra("Stat5",123f);

                        startActivity(startRadarGraphActivity);
                        //boomMenuButtonNav.getContext().startActivity(startRadarGraphActivity);

                    }

                }
            });
            boomMenuButtonNav.addBuilder(builder);
        }


    }

    /**
     * Called when a user selects the items pane on the viewpager.
     */
    private void onItemsSelector(int whichPane){
        //find the view and modify it
        if (whichPane==1){
            ConstraintLayout constraintLayout = findViewById(R.id.selectedChampsView);
            constraintLayout.setBackgroundColor(Color.GREEN);

            //darken the icons except the one which the user has currently selected.
            for (int i=0 ;i<champ_slots.length;i++){
                champ_slots[i].setAlpha(0.5f);
            }

            champ_slots[0].setAlpha(1f);

            return;
        }
        if (whichPane==0){
            ConstraintLayout constraintLayout = findViewById(R.id.selectedChampsView);
            constraintLayout.setBackgroundColor(Color.parseColor("#525461") );

            //darken the icons except the one which the user has currently selected.
            for (int i=0 ;i<champ_slots.length;i++){
                champ_slots[i].setAlpha(1f);
            }

            champ_slots[0].setAlpha(1f);
        }

    }
    /**
     * Method in the main activity which is called inside a fragment containing a recyclerview
     * The RW passes an integer coressponding to the id of the champion the user has selected by
     * pressing on them.
     * @param a id of champion.
     */
    public void RecyclerViewChampions_OnClick(int a, ChampBase champBase){
    Toast.makeText(this, String.valueOf(a), Toast.LENGTH_SHORT).show();


//  check if theres room        and if the icon is already displayed.
    if (!emptySlots.empty() && !champsClicked.containsChampionId(a)) {

        int imageView = emptySlots.pop();

        ChampDisplayTuple disp = new ChampDisplayTuple(a,imageView);

        champsClicked.push(disp);
       // champsAdded.push(disp);

        champ_slots[imageView].setImageResource(champBase.iconId);

        champsSelected_ForGraph.add(champBase);
        calcStats();


    }else{
        Toast.makeText(this, "Too many champions!", Toast.LENGTH_SHORT).show();
    }
}

    /**
     * Id be nice to abstract this a bit more..
     * @param a
     * @param itemBase
     */
    public void RecyclerViewItems_OnClick(int a, ItemBase itemBase){
    Toast.makeText(this, String.valueOf(a), Toast.LENGTH_SHORT).show();
    int idx = champSelectedForItems *3 ;

    //0, 3, 5, 7... 24, 27
    int temp = slotCounterArray.get(champSelectedForItems);
    idx += temp;
    temp++;
    if (temp>2){
        temp =0;
    }
    slotCounterArray.set(champSelectedForItems, temp    );


    itemSlotsForChamps[idx].setImageResource(itemBase.iconId);
    itemsAddedToChamps[idx] = itemBase;



}

private int getItemIndexFromSlotId(int slotId){
        return slotId * 3;
}
private void setItemForChampInDisplaySlot(int slotid){
        if (viewSelectedState==1){
            //somehow insert items and display them for the given view in "slotid"

        }

}

    private void calcStats() {
        int hp = 0;
        int attack =0;
        int spell =0;
        int armor =0;
        int overall =0 ;


        for (int i =0; i<champsSelected_ForGraph.size();i++){
            hp+=champsSelected_ForGraph.get(i).hp   ;
            attack+=champsSelected_ForGraph.get(i).dps  ;

            familySets[champsSelected_ForGraph.get(i).family] ++;
            familySets[champsSelected_ForGraph.get(i).subFamily] ++;



        }

        /**
         * FamilySetBonusCount is either 0 1 2 or 3
         *  for each of its indices. Multiply each index by an appropriate multiple and
         *  add either hp armor or whatever to it to modify it.
         */
        for (int i =0; i<22; i++){
            switch (i){
                case BLADEMASTER:
                    if ( familySets[i] >=3  ){
                        familySetBonusCount[i] = traitBonuses.get(i).get(familySets[i]/3);

                        familySetBonusCount[i] = familySets[i] /3; //3 or 6 (1 or 2 )
                    }
                    break;
                case BLASTER:
                    if ( familySets[i] >=3  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 (1 or 2 )
                    }
                    break;

                case BRAWLER:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 (1 or 2 )
                    }
                    break;

                case CELESTIAL:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 or 6  (1 or 2 or 3)
                    }
                    break;

                case CHRONO:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 or 6  (1 or 2 or 3)
                    }
                    break;

                case CYBERNETIC:
                    if ( familySets[i] >=3  ){
                        familySetBonusCount[i] = familySets[i] /3; //3 or 6   (1 or 2 )
                    }
                    break;

                case DARK_STAR:
                    if ( familySets[i] >=3  ){
                        familySetBonusCount[i] = familySets[i] /3; //3 or 6  (1 or 2 )
                    }
                    break;

                case DEMOLITIONIST:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = 1; //2 or nothing
                    }
                    break;

                case MANA_REAVER:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2or 4
                    }
                    break;

                case MECH_PILOT:
                    if ( familySets[i]  ==3  ){
                        familySetBonusCount[i] = 1; // 3 or nothing
                    }
                    break;

                case MERCENARY:
                    familySetBonusCount[i] =1;
                    break;

                case MYSTIC:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 (1 or 2 )
                    }
                    break;

                case PROTECTOR:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 (1 or 2 )
                    }
                    break;

                case REBEL:
                    if ( familySets[i] >=3  ){
                        familySetBonusCount[i] = familySets[i] /3; //3 or 6   (1 or 2 )
                    }
                    break;

                case SNIPER:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = 1; //2 or nothing
                    }
                    break;

                case SORCERER:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 or 6  (1 or 2 or 3)
                    }
                    break;

                case SPACE_PIRATE:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 (1 or 2 )
                    }
                    break;

                case STAR_GUARDIAN:
                    if ( familySets[i] >=3  ){
                        familySetBonusCount[i] = familySets[i] /3; //3 or 6   (1 or 2 )
                    }
                    break;

                case STARSHIP:
                    familySetBonusCount[i] = 1;
                    break;

                case VALKYRIA:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = 1; //2 or nothing
                    }
                    break;

                case VANGUARD:
                    if ( familySets[i] >=2  ){
                        familySetBonusCount[i] = familySets[i] /2; //2 or 4 (1 or 2)
                    }
                    break;

                case VOID :
                    if ( familySets[i] >=3 ){
                        familySetBonusCount[i] = 1; //3 or nothing
                    }
                    break;

                default:

                    break;
            }
        }

        attack = attack + familySetBonusCount[SNIPER] * attack * 2;
        attack = attack + familySetBonusCount[BLADEMASTER] * attack * 2 ;
        attack = attack + familySetBonusCount[BLASTER] * attack * 2 ;

        hp = hp + familySetBonusCount[BRAWLER] * hp * 4 ;
        hp = hp + familySetBonusCount[CELESTIAL] * hp * 2 ;

        attack = attack + familySetBonusCount[CHRONO] * attack * 2 ;

        attack = attack + familySetBonusCount[CYBERNETIC] * attack * 2 ;
        hp = hp + familySetBonusCount[CYBERNETIC] * hp * 2 ;

        attack = attack + familySetBonusCount[DARK_STAR] * attack * 2 ;

        hp = hp + familySetBonusCount[DEMOLITIONIST] * hp * 2 ;

        hp = hp + familySetBonusCount[MANA_REAVER] * hp * 2 ;


        attack = attack + familySetBonusCount[MECH_PILOT] * attack * 2 ;
        hp = hp + familySetBonusCount[MECH_PILOT] * hp * 2 ;

        attack = attack + familySetBonusCount[MERCENARY] * attack * 4 ;
        hp = hp + familySetBonusCount[MERCENARY] * hp * 4 ;

        hp = hp + familySetBonusCount[MYSTIC] * hp * 4 ;

        hp = hp + familySetBonusCount[PROTECTOR] * hp * 6 ;

        attack = attack + familySetBonusCount[REBEL] * attack * 4 ;
        hp = hp + familySetBonusCount[REBEL] * hp * 4 ;

        attack = attack + familySetBonusCount[SORCERER] * attack * 4 ;

        hp = hp + familySetBonusCount[SPACE_PIRATE] * hp * 4 ;

        attack = attack + familySetBonusCount[STAR_GUARDIAN] * attack * 3 ;

        attack = attack + familySetBonusCount[STARSHIP] * attack * 3 ;

        attack = attack + familySetBonusCount[VALKYRIA] * attack * 3 ;

        hp = hp + familySetBonusCount[VANGUARD] * hp * 4 ;

        attack = attack + familySetBonusCount[VOID] * attack * 9 ;

        float atkF = 10000;
        float hpF = atkF;
        atkF = (float) attack / atkF;
        if (atkF > 100){
            atkF = (float)Math.sqrt(atkF/50);
        }
        hpF = (float)   hp / hpF    ;



        BarEntry health = new BarEntry(1f, hpF);
        BarEntry attack1 = new BarEntry(2f, atkF);
        BarEntry three = new BarEntry(3f,50f);
        BarEntry four = new BarEntry(4f, 75f);
        BarEntry five = new BarEntry(5f, 4f);


        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        /*
        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));

            if (Math.random() * 100 < 25) {
                values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
            } else {
                values.add(new BarEntry(i, val));
            }
        }
         */
        values.add(health);
        values.add(attack1);
        values.add(three);
        values.add(four)    ;
        values.add(five)    ;

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "");

            set1.setDrawIcons(false);

            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

            List<GradientColor> gradientFills = new ArrayList<>();
            gradientFills.add(new GradientColor(startColor1, endColor1));
            gradientFills.add(new GradientColor(startColor2, endColor2));
            gradientFills.add(new GradientColor(startColor3, endColor3));
            gradientFills.add(new GradientColor(startColor4, endColor4));
            gradientFills.add(new GradientColor(startColor5, endColor5));

            set1.setGradientColors(gradientFills);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(tfLight);
            data.setBarWidth(0.9f);

            chart.setData(data);
        }

        chart.invalidate();
    }


    private final RectF onValueSelectedRectF = new RectF();
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = onValueSelectedRectF;
        chart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = chart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + chart.getLowestVisibleX() + ", high: "
                        + chart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    /**5 , 100
     *
     * @param count
     * @param range
     */
    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));

            if (Math.random() * 100 < 25) {
                values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
            } else {
                values.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "");

            set1.setDrawIcons(false);

            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

            List<GradientColor> gradientFills = new ArrayList<>();
            gradientFills.add(new GradientColor(startColor1, endColor1));
            gradientFills.add(new GradientColor(startColor2, endColor2));
            gradientFills.add(new GradientColor(startColor3, endColor3));
            gradientFills.add(new GradientColor(startColor4, endColor4));
            gradientFills.add(new GradientColor(startColor5, endColor5));

            set1.setGradientColors(gradientFills);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(tfLight);
            data.setBarWidth(0.9f);

            chart.setData(data);
        }
    }

    @Override
    public void onNothingSelected() { }

    @Override
    public void onBackPressed(){

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
                    if (secondFragment== null){
                        secondFragment = SecondFragment.newInstance("abc");
                        return secondFragment;
                    }else   {
                        return secondFragment;
                    }
                case 1:
                    if (firstFragment== null){
                        firstFragment = FirstFragment.newInstance("abc");
                        return firstFragment;
                    }else   {
                        return firstFragment;
                    }
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

    public  static class FirstFragment extends Fragment {
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.info_recyler_frag,container,false);
            rootView.setTag(TAG);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView2);

            mLayoutManager= new LinearLayoutManager(getActivity());
            mCurrentLayoutManagerType =LayoutManagerType.LINEAR_LAYOUT_MANAGER;

            if (savedInstanceState != null){
                mCurrentLayoutManagerType = (LayoutManagerType)savedInstanceState
                        .getSerializable(KEY_LAYOUT_MANAGER);

            }
            setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
            ArrayList<Object> items3 = new ArrayList<>();

            items3.add(new ItemBuilderSet());
            items3.add(new ItemBuilderSet());
            items3.add(new ItemBuilderSet());
            items3.add(new ItemBuilderSet());
            items3.add(new ItemBuilderSet());
            items3.add(new ItemBuilderSet());
            items3.add(new ItemBuilderSet());


            Object main = getActivity();
            main = (MainActTwo ) main;

            mAdapter = new CustomAdapter(getContext(), items3, main, 123);
            //set custom adapter
            mRecyclerView.setAdapter(mAdapter);


            return rootView;
        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);

        }
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

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView2);

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
            main = (MainActTwo ) main;

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

    private class SearchableList{
        private ArrayList<ChampDisplayTuple> arrayList;

        public SearchableList(){
            arrayList = new ArrayList<>();
        }

        public void push(ChampDisplayTuple tuple){
            arrayList.add(tuple);

        }
        public boolean containsChampionId(int id){
            for (int i =0; i <arrayList.size(); i++){
                if (arrayList.get(i).champId == id){
                    return true;
                }
            }
            return false;
        }

        //index 0 to 9
        public boolean isSlotFilled(int slotid){
            for (int i =0; i <arrayList.size(); i++){
                if (arrayList.get(i).champId == slotid){
                    return true;
                }
            }
            return false;
        }


        public void removeByDisplayId(int id){
            for (int i =0; i<arrayList.size();i++){
                if (arrayList.get(i).dispId==id){
                    arrayList.remove(i);
                    break;
                }
            }
        }


     /*
        public ArrayList<Entry> getChartData(){
            ArrayList<Entry> ret = new ArrayList<>();
            for (int i=0; i<arrayList.size();i++){
                Entry entry = new Entry(arrayList.get(i).);
            }
      */

    }
    private class ChampDisplayTuple{
        public int champId, //global id of the champion
                dispId;     //id in the display when selected.
        public int hp, dps;


        public ChampDisplayTuple(int chId, int displayId){
            champId=chId;
            dispId=displayId;
        }

       /*
        public ChampDisplayTuple(int chId, int displayId){
            champId=chId;
            dispId=displayId;
        }
        */


    }



    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
