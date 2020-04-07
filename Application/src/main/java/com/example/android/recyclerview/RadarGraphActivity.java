package com.example.android.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.common.activities.MainActTwo;
import com.example.android.common.activities.SampleActivityBase;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;

public class RadarGraphActivity extends SampleActivityBase {

    RadarChart chart;
    float [] chart_vals;
    BoomMenuButton boomMenuButton;

    @Override
        public void onCreate(Bundle savedInstanceState){
          super.onCreate(savedInstanceState);
            setContentView(R.layout.radar_graph_activity);
            chart = new RadarChart(this);

            chart_vals = new float[10];
            boomMenuButton = findViewById(R.id.radarboom);
            //bmb button
        for (int i=0; i<boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++   ){
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.tft3_ahri_mobile_)
                    .normalText("title").subNormalText("subtitle");
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    //
                    if (index ==0   ){
                        //the radar graph activity
                        Intent startRadarGraphActivity = new Intent(getBaseContext(), MainActTwo.class)  ;
                        startRadarGraphActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                        startRadarGraphActivity.putExtra("Hp",123f);
                        startRadarGraphActivity.putExtra("Armor",123f);
                        startRadarGraphActivity.putExtra("Stat3",123f);
                        startRadarGraphActivity.putExtra("Stat4",123f);
                        startRadarGraphActivity.putExtra("Stat5",123f);

                        startActivity(startRadarGraphActivity);

//                        boomMenuButton.getContext().startActivity(startRadarGraphActivity);

                    }

                }
            });
            boomMenuButton.addBuilder(builder);
        }
            //HP
            chart_vals[0] = getIntent().getFloatExtra("Hp",123f);
            chart_vals[1] = getIntent().getFloatExtra("Armor",123f);
            chart_vals[2] = getIntent().getFloatExtra("Stat3",123f);
            chart_vals[3] = getIntent().getFloatExtra("Stat4",123f);
            chart_vals[4] = getIntent().getFloatExtra("Stat5",123f);


        chart = findViewById(R.id.radarGraph1);
        chart.setBackgroundColor(Color.rgb(60, 65, 82));

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        setData();

        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
       // xAxis.setTypeface(tfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setTextSize(40f);
        xAxis.setValueFormatter(new ValueFormatter() {

            private final String[] mActivities = new String[]{"Burger", "Steak", "Salad", "Pasta", "Pizza"};

            @Override
            public String getFormattedValue(float value) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
       // yAxis.setTypeface(tfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //l.setTypeface(tfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
        l.setTextSize(40f);
         }


    private void setData() {

        float mul = 80;
        float min = 20;
        int cnt = 5;

        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mul) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mul) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
       // data.setValueTypeface(tfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();
    }
}
