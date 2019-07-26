package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class Entries extends AppCompatActivity {
    String Tag="Entries";
    Timestamp mTimeStamp;
    Calendar mCalendar;

    ImageView imageView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        imageView1=findViewById(R.id.myImageView2);

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

        //Fab Button
        View fab1= findViewById(R.id.floatingActionButton);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView1.setImageBitmap(imageBitmap);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] image = stream.toByteArray();

            Intent intent = new Intent(this, Entries2.class);
            intent.putExtra("photo", image);
            startActivity(intent);

        }
    }
}
