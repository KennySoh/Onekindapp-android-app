package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Entries extends AppCompatActivity {
    String Tag="Entries";
    Timestamp mTimeStamp;
    Calendar mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        // TimeStamps
        /* https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html*/
        /* https://www.mkyong.com/java/java-date-and-calendar-examples/*/
        mCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
        mTimeStamp= new Timestamp(System.currentTimeMillis());
        mCalendar.setTime(mTimeStamp);
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        Log.i(Tag,String.valueOf(year)+" "+String.valueOf(month)+ " "+String.valueOf(day));

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"	);
        String time = sdf.format(mTimeStamp);
        Log.i(Tag,time); // 12:52 PM

        SimpleDateFormat sdf_day = new SimpleDateFormat("EEE"	);
        String day_name = sdf_day.format(mTimeStamp);
        Log.i(Tag,day_name); //Wed


        //Entries Onclick
        View cardView1= findViewById(R.id.cardView1);
        cardView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(Entries.this, "Entries2", Toast.LENGTH_SHORT).show();
                Intent entries2_intent = new Intent(Entries.this, Entries2.class);
                Entries.this.startActivity(entries2_intent);
            }
        });

    }
}
