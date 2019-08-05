package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class CalenderView extends AppCompatActivity {
    GifDrawable gifFromResource1;
    ImageView animatedImageView1;// lock gif
    TextView textView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);

        animatedImageView1=(ImageView) findViewById(R.id.animatedGif_ImageView1);
        textView= findViewById(R.id.textView1);

        try {
            gifFromResource1 = new GifDrawable( getResources(), R.raw.lock);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gifFromResource1.seekTo(0);
                    animatedImageView1.setImageDrawable(gifFromResource1);
                    textView.setText("For Future Development");

                }
            }, 1000);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Btm Navigation Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_data:
//                        Toast.makeText(Diary_duplicate.this, "Data", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent diary_intent = new Intent(CalenderView.this, MainActivity.class);
                        CalenderView.this.startActivity(diary_intent);
                        break;
                    case R.id.action_diary:
                        finish();
                        Intent diary2_intent = new Intent(CalenderView.this, Diary_duplicate.class);
                        CalenderView.this.startActivity(diary2_intent);

                        break;
                    case R.id.action_calendar:
                        break;
                }
                return true;
            }
        });

        //Setting backbutton on toolbar to go back
        toolbar= findViewById(R.id.toolBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent diary_intent = new Intent(CalenderView.this, MainActivity.class);
                CalenderView.this.startActivity(diary_intent);;
            }
        });

    }
}
