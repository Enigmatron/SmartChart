package com.example.youwin1234.designstuff;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import static com.example.youwin1234.designstuff.R.*;
import static com.example.youwin1234.designstuff.R.layout.graph_testing;

public class GraphPlot extends Fragment {

    private static String TAG = "GraphPlot";

    private float[] yData = {23.5f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
    private String[] xData = {"Mitch", "Jessica", "Mohammad", "Kelsey", "Sam", "Robert", "Ashley"};
    PieChart pieChart;


    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(layout.graph_testing, container, false);

        Log.d(TAG, "onCreate: start to create chart");

        pieChart = (PieChart) v.findViewById(id.idPieChart);

        Description description = new Description();
        description.setText("powered by Hooli");
        pieChart.setDescription(description);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("IntelliGraph");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelTextSize(20);
        pieChart.setBackgroundColor(0xc1d7eeff);

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                //int pos1 = e.toString().indexOf("(sum): ");

            }

            @Override
            public void onNothingSelected() {

            }
        });
        return v;
    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        for(int i = 0; i < yData.length; i++) {
            yEntries.add(new PieEntry(yData[i], i));
        }
        for(int i = 0; i < xData.length; i++) {
            xEntries.add(xData[i]);
        }

        // create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntries, "test y data");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        // add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.DKGRAY);
        colors.add(Color.CYAN);
        colors.add(Color.WHITE);
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);

        pieDataSet.setColors(colors);

        // add legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

}
