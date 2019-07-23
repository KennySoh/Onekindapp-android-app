package com.example.myapplication;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Diary_duplicate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_duplicate);

        //Actionbar
        View fab1= findViewById(R.id.floatingActionButton);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Diary_duplicate.this, "QRCODE", Toast.LENGTH_SHORT).show();
                Intent qr_intent = new Intent(Diary_duplicate.this, qr_code.class);
                Diary_duplicate.this.startActivity(qr_intent);
            }
        });

        //Btm Navigation Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_data:
                        Toast.makeText(Diary_duplicate.this, "Data", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case R.id.action_diary:
                        Toast.makeText(Diary_duplicate.this, "Diary", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_calendar:
                        Toast.makeText(Diary_duplicate.this, "Calender", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}