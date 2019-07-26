package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RecyclerViewTest extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS=100;
    GreenAdapter mAdapter;
    RecyclerView mNumbersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);

        mNumbersList= (RecyclerView)findViewById(R.id.rv_number);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        mNumbersList.setLayoutManager((layoutManager));
        mNumbersList.setHasFixedSize(true);
        mAdapter=new GreenAdapter(NUM_LIST_ITEMS);
        mNumbersList.setAdapter(mAdapter);
    }
}
