package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Entries2 extends AppCompatActivity {
    Toolbar toolbar;
    ImageView toolbar_sharing;
    Button btnSave;
    Intent intent;
    ViewGroup hiddenPanel;
    ImageView hiddenImage;
    TextView backgroundSocial;

    ImageView imageView1;
    EditText listSubtitle1;
    EditText listSubtitle2;
    TextView listDate1;
    TextView listDate2;
    TextView listDate3;
    TextView listDate4;

    byte[] byteArrayExtra;

    private final Map<Integer, String> month_map = new HashMap<Integer, String>(){
        {
            put(0, "January ");
            put(1, "February ");
            put(2, "March ");
            put(3, "April ");
            put(4, "May ");
            put(5, "June ");
            put(6, "July ");
            put(7, "August ");
            put(8, "September ");
            put(9, "October ");
            put(10, "November ");
            put(11, "December ");

        }
    };

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries2);

        //Setting backbutton on toolbar to go back
        toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        //Social Sharing
       toolbar_sharing=findViewById(R.id.toolbar_logo);
       hiddenPanel = findViewById(R.id.socialSharing);
       hiddenPanel.setVisibility(View.INVISIBLE);
       backgroundSocial=findViewById(R.id.background_social);
       final Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_up);
       final Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_down);
       toolbar_sharing.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               hiddenPanel.setVisibility(View.VISIBLE);
               hiddenPanel.startAnimation(bottomUp);
               backgroundSocial.setBackgroundColor(Color.parseColor("#A6131212"));
           }
       });
       hiddenImage=findViewById(R.id.socialSharingImage);
       hiddenImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               hiddenPanel.setVisibility(View.INVISIBLE);
               hiddenPanel.startAnimation(bottomDown);
               backgroundSocial.setBackgroundColor(Color.TRANSPARENT);
               Toast.makeText(getApplicationContext(), "Shared on Online", Toast.LENGTH_SHORT).show();

           }
       });


        //Setting phototaken into imageView
        imageView1=findViewById(R.id.imageView1);
        intent= getIntent();
        try {
            byteArrayExtra = intent.getByteArrayExtra("photo");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length, new BitmapFactory.Options());
            imageView1.setImageBitmap(bitmap);
        }catch (Exception e){

        }

        //Save Button
        btnSave = (Button) findViewById(R.id.buttonNext);
        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Into SQL DataBase
                long timeStamp_mili=intent.getLongExtra("myTimestamp",0);
                String listSubtitle1_s=listSubtitle1.getText().toString();
                String listSubtitle2_s=listSubtitle2.getText().toString();

                Intent myIntent=new Intent();
                Log.i("mAdapterE2_SaveBtn", "Title: "+listSubtitle1_s+", Body: "+listSubtitle2_s+", Timestamp: "+timeStamp_mili);
                myIntent.putExtra("photo", byteArrayExtra); //Return Photo
                myIntent.putExtra("myTimestamp", timeStamp_mili);// Return TimeStamp
                myIntent.putExtra("title",listSubtitle1_s ); //Return Title
                myIntent.putExtra("body", listSubtitle2_s); //Return Body
                setResult(RESULT_OK, myIntent);
                finish();
            }
        });


        //Fill in Title, Body, dates from Intent
       listSubtitle1 = (EditText) findViewById(R.id.myEditText_Subtitle1);
       listSubtitle2 = (EditText) findViewById(R.id.myEditText_Subtitle2);
       listDate1 = (TextView) findViewById(R.id.myEntriesText_Date1); //01 "Date"
       listDate2 = (TextView) findViewById(R.id.myEntriesText_Date2); //Friday
       listDate3 = (TextView) findViewById(R.id.myEntriesText_Date3); //Feburary 2019
       listDate4 = (TextView) findViewById(R.id.myEntriesText_Date4); //12.30 pm

       //Fill in Timestamp
       try {
           long timeStamp_mili=intent.getLongExtra("myTimestamp",0);

           Timestamp currentTimeStamp=new Timestamp(timeStamp_mili);
           // TimeStamps
           /* https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html*/
           /* https://www.mkyong.com/java/java-date-and-calendar-examples/*/
           Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
           mCalendar.setTime(currentTimeStamp);
           int year = mCalendar.get(Calendar.YEAR);
           int month = mCalendar.get(Calendar.MONTH);
           int day = mCalendar.get(Calendar.DAY_OF_MONTH);

           SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"	);
           String time = sdf.format(currentTimeStamp);
           // 12:52 PM

           SimpleDateFormat sdf_day = new SimpleDateFormat("EEEE"	);
           String day_name = sdf_day.format(currentTimeStamp);
           //Wednesday

           listDate1.setText(String.valueOf(day)); //01 "Date"
           listDate2.setText(day_name); //Friday
           listDate3.setText(month_map.get(month)+String.valueOf(year));//Feburary 2019
           listDate4.setText(time); //12.30pm // todo

       }catch (Exception e){
           e.printStackTrace();
       }
       //Fill in Title & Body
       try {
           String myTitle=intent.getStringExtra("myTitleSet");
           String myBody=intent.getStringExtra("myBodySet");

           listSubtitle1.setText(myTitle);
           listSubtitle2.setText(myBody);
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    @Override
    public void setSupportActionBar(@Nullable androidx.appcompat.widget.Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }

}
