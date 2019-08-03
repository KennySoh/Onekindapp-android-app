package com.example.myapplication;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Diary_duplicate extends AppCompatActivity implements GreenAdapter_Diary.ListItemClickListener {
    // return seed scanners
    private int QR_CODE=200;

    //Recycler View
    private int NUM_LIST_ITEMS=100;
    GreenAdapter_Diary mAdapter;
    RecyclerView mNumbersList;
    ArrayList<String> myTitleSet;
    private Toast mToast;

    //Database
    DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_duplicate);

        //Database
        mDbHelper= DatabaseHelper.getInstance(this);

        //Declaring Title for Recyclerview
        myTitleSet=new ArrayList<String >();
        ArrayList<String> test;

//        try {
//            test = mDbHelper.readSeed();
//            Log.i("dataaa",String.valueOf(test.size()));
//            for(int i = 0; i < test.size(); i++){
//                Log.i("dataaa",test.get(i));
//            }
////            mDbHelper.clearDataBase();
//            myTitleSet=test;
//        }catch (Exception e){
//            Log.i("dataaa","Failed 1");
//            e.printStackTrace();
//        }


        NUM_LIST_ITEMS=myTitleSet.size();

        //Linear LayoutManger  attaching adapter to recyclerview
        mNumbersList= (RecyclerView)findViewById(R.id.rv_number);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this );
        mNumbersList.setLayoutManager((layoutManager));
        mNumbersList.setHasFixedSize(true);
        mAdapter=new GreenAdapter_Diary(NUM_LIST_ITEMS,myTitleSet,this);
        mNumbersList.setAdapter(mAdapter);

        //Actionbar
        View fab1= findViewById(R.id.floatingActionButton);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Diary_duplicate.this, "QRCODE", Toast.LENGTH_SHORT).show();
                Intent qr_intent = new Intent(Diary_duplicate.this, Barcode_test.class);
                Diary_duplicate.this.startActivityForResult(qr_intent,QR_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==QR_CODE){
            try {
                String seed = data.getStringExtra("qrCode");
                Toast.makeText(Diary_duplicate.this, seed, Toast.LENGTH_SHORT).show();
                //add Seeds into recycler view.
                String itemToFind = "Item 1";
                if(!myTitleSet.contains(seed)) {//Ensure no repeated seeds are added

                    myTitleSet.add(seed);//myTitleSet automatically updates mTitleSet in adapter
                    //add into SQLite, include current position?
//                    int currentPos=myTitleSet.size()-1;
//                    mDbHelper.createSeed(currentPos, seed);
                    mAdapter.updateData();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onListItemClick(int clickItemIndex) {
        if(mToast!=null){
            mToast.cancel();
        }
        String toastMessage="ITEM #"+clickItemIndex+" Clicked";
        mToast=Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        mToast.show();

        Intent entries_intent = new Intent(Diary_duplicate.this, Entries.class);
        String currentTitle=mAdapter.mTitleSet.get(clickItemIndex); //sending title
        entries_intent.putExtra("title",currentTitle);
        int currentImageId =mAdapter.mImagesId.get(currentTitle);   // sending imageid
        entries_intent.putExtra("imageId",currentImageId);
        Diary_duplicate.this.startActivity(entries_intent);
    }
}