package com.example.youwin1234.designstuff;

import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class GraphBar extends Fragment {

    private static String TAG = "GraphBar";

    private float[] yData = {23.5f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
    private String[] xData = {"Mitch", "Jessica", "Mohammad", "Kelsey", "Sam", "Robert", "Ashley"};
    BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.bar_chart, container, false);
        Log.d(TAG, "onCreateView: start to create chart");

        barChart = (BarChart) v.findViewById(R.id.idPieChart);

        Description description = new Description();
        description.setText("powered by Hooli");
        barChart.setDescription(description);
        barChart.setPinchZoom(true);
        barChart.setDrawBarShadow(true);
        barChart.setBackgroundColor(0xc1d7eeff);

        addDataSet();

        /*barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("y: ");
                String value = e.toString().substring(pos1 + 3);

                for(int i = 0; i < yData.length; i++) {
                    if(yData[i] == Float.parseFloat(value)) {
                        pos1 = i;
                    }
                }
                String category = xData[pos1];
                Toast.makeText(getActivity(), "Category: " + category + "\n" + "Value: " + value, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });*/

        return(v);
    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet: started");
        ArrayList<BarEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        for(int i = 0; i < yData.length; i++) {
            yEntries.add(new BarEntry(yData[i], i));
        }
        for(int i = 0; i < xData.length; i++) {
            xEntries.add(xData[i]);
        }

        // create data set
        BarDataSet barDataSet = new BarDataSet(yEntries, "test y data");
        //barDataSet.setStackLabels();

        // add colors to set
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(0xFFFFFFFF);
        colors.add(0xBCAF8F0F);
        colors.add(0xF00F9F0A);
        colors.add(0xB00020AA);
        colors.add(0xFFC0C0C0);
        colors.add(0xBA33FCAF);
        colors.add(0x99000009);

        barDataSet.setColors(colors);

        // add legend
        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);

        // create bar chart object
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();

    }

}
