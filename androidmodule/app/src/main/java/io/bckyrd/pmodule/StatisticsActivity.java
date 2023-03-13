package io.bckyrd.pmodule;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Add these two lines to show the title and the back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Statistics");

        // Find the LineChart view by its ID
        //LineChart lineChart = findViewById(R.id.lineChart);

        // Get a reference to the "voltage" node in the Firebase database
       // DatabaseReference voltageRef = FirebaseDatabase.getInstance().getReference().child("voltage");

        //Insert data entries into the "voltage" node
        /*
        voltageRef.push().setValue(new Entry(2, 230));
        voltageRef.push().setValue(new Entry(3, 240));
        voltageRef.push().setValue(new Entry(4, 220));
         */

        // Create a LineChart object
        LineChart lineChart = findViewById(R.id.lineChart);

// Get a reference to the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference voltageRef = database.getReference("voltage");

// Add a ValueEventListener to the voltageRef reference
        voltageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Entry> dataEntries = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    float x = snapshot.child("x").getValue(Float.class);
                    float y = snapshot.child("y").getValue(Float.class);
                    dataEntries.add(new Entry(x, y));
                }

                // Show only the last 3 data entries
                int startIndex = dataEntries.size() > 3 ? dataEntries.size() - 3 : 0;
                List<Entry> lastThreeEntries = dataEntries.subList(startIndex, dataEntries.size());

                // Create a LineDataSet object and customize its appearance
                LineDataSet dataSet = new LineDataSet(lastThreeEntries, "");
                dataSet.setColor(Color.LTGRAY);
                dataSet.setDrawCircles(true);
                dataSet.setCircleColor(Color.BLACK);
                dataSet.setCircleRadius(5f);
                dataSet.setDrawValues(true);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(12f);
                dataSet.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getAxisLabel(float value, AxisBase axis) {
                        return String.valueOf((int) value);
                    }
                });

                // Create a LineData object and add the LineDataSet to it
                LineData data = new LineData(dataSet);

                // Set some custom formatting for the X axis
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setGranularity(1f);
                xAxis.setGranularityEnabled(true);
                xAxis.setDrawGridLines(false);
                xAxis.setDrawAxisLine(false);
                xAxis.setDrawLabels(false);

                // Set some custom formatting for the Y axis
                YAxis yAxis = lineChart.getAxisLeft();
                yAxis.setDrawGridLines(false);
                yAxis.setDrawAxisLine(false);
                yAxis.setGranularityEnabled(true);
                yAxis.setDrawLabels(false);

                // Hide the right Y axis
                lineChart.getAxisRight().setEnabled(false);

                // Disable interactions with the chart
                lineChart.setTouchEnabled(false);
                lineChart.setDragEnabled(false);
                lineChart.setScaleEnabled(false);
                lineChart.setPinchZoom(false);

                // Remove the description label
                lineChart.getDescription().setEnabled(false);

                // Set the LineData object as the data for the LineChart view
                lineChart.setData(data);

                // Invalidate the LineChart view to refresh it
                lineChart.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
