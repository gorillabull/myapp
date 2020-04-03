package com.example.android.common.activities;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.recyclerview.ChampBase;
import com.example.android.recyclerview.ChampStatsAxisFormatter;
import com.example.android.recyclerview.R;
import com.example.android.recyclerview.RecyclerViewFragment;
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
import java.util.List;
import java.util.Stack;

import   com.example.android.recyclerview.XYMarkerView;
import   com.example.android.recyclerview.DayAxisValueFormatter;

import   com.example.android.recyclerview.MyValueFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;

import com.example.android.recyclerview.Fill;


import com.github.mikephil.charting.utils.Utils;

public class MainActTwo extends SampleActivityBase implements
        OnChartValueSelectedListener {
    private BarChart chart;
    private ImageView imageView1;
    private ImageView imageView2;
    private Stack<Integer> emptySlots;
    private Stack<ChampDisplayTuple> champsAdded; // delete

    private SearchableList champsClicked;


    private ImageView [] champ_slots;

    private ChampBase iconsDb;

    protected Typeface tfRegular;
    protected Typeface tfLight;

    private ArrayList<ChampBase> champsSelected_ForGraph;
    private Integer []  familySets;
    private Integer []  subFamilySets;
    private Integer []  familySetBonusCount;

    private final  int BLADEMASTER =1;
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
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am_two  );
        champ_slots = new ImageView[10];
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

        for (int i=0; i<22; i++){
            familySetBonusCount[i] =0;
            familySets[i] =0;
            subFamilySets[i] = 0;
        }

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false   );
        actionBar.hide();

        if (savedInstanceState == null){
            FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            Bundle args = new Bundle();
            args.putInt("key1",3);
            RecyclerViewFragment fragment = new RecyclerViewFragment();
            fragment.setArguments(args);

            transaction.replace(R.id.sample_content_fragment3,fragment);
            transaction.commit();
        }

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


        for(int i=0 ; i < champ_slots.length; i++){
            champ_slots[i].setImageResource(R.drawable.fade_red);
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
                    //clear it
                     v = (ImageView)v;
                     int tag = (Integer)v.getTag();                                               //get the tag of the display box image
                     if (!emptySlots.contains(tag)){
                         ((ImageView) v).setImageResource(R.drawable.fade_red);
                         emptySlots.push((Integer)v.getTag());//recycle the empty slot
                         //clear the icon from storage so it can appear again
                         champsClicked.removeByDisplayId(tag);
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

}
