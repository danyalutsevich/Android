package com.danlutsevich.paint;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

// https://github.com/PhilJay/MPAndroidChart#usage

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    String[] info = {"Занят", "Не слышит", "Забыл дома", "В полиции", "В ТЦК", "В морге"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        PieChart pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(25, 5, 25, 0);

        pieChart.setCenterText("покрути меня!");

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(30f);

        pieChart.setEntryLabelColor(Color.BLACK);

        List<PieEntry> yvalues = new ArrayList<>();
        yvalues.add(new PieEntry(10f, info[0]));
        yvalues.add(new PieEntry(10f, info[1]));
        yvalues.add(new PieEntry(10f, info[2]));
        yvalues.add(new PieEntry(10f, info[3]));
        yvalues.add(new PieEntry(30f, info[4]));
        yvalues.add(new PieEntry(30f, info[5]));

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        dataSet.setSliceSpace(3f);

        ArrayList<String> xVals = new ArrayList<>();

        xVals.add(info[0]);
        xVals.add(info[1]);
        xVals.add(info[2]);
        xVals.add(info[3]);
        xVals.add(info[4]);
        xVals.add(info[5]);

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        // data.setValueFormatter(new DefaultValueFormatter(0));

        pieChart.setData(data);

        pieChart.setEntryLabelTextSize(13);

        int[] colors = {Color.RED, Color.rgb(255, 128, 0), Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};
        dataSet.setColors(ColorTemplate.createColors(colors));

        // dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        // dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        // dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        // dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        Description d = new Description();
        d.setTextSize(16);
        d.setPosition(65, 50);

        d.setTextAlign(Paint.Align.LEFT);
        d.setText("Что думает мама, если я не взял трубку");
        pieChart.setDescription(d);


        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        pieChart.animateXY(1500, 1500);

        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(1.2f);
        dataSet.setValueLinePart2Length(0.4f);

        pieChart.setOnChartValueSelectedListener(this);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null || h == null)
            return;
        Toast.makeText(this, "Value: " + e.getY() + ", index: " + h.getX()
                + "data: " + info[h.getDataIndex() + 1], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected() {
        // do nothing
    }
}