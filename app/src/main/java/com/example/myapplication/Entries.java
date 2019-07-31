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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class Entries extends AppCompatActivity implements GreenAdapter.ListItemClickListener {
    String Tag="Entries";
    ImageView mImageView;
    TextView mTitleView;


    //Recycler View
    private int NUM_LIST_ITEMS=100;
    GreenAdapter mAdapter;
    RecyclerView mNumbersList;
    String[] myTitleSet;
    String[] myBodySet;
    Timestamp[] myTimeStamp;
    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        //Getting title & imageId from Diary_duplicate page
        Intent myIntent= getIntent();
        String title=myIntent.getStringExtra("title");
        int imageId=myIntent.getIntExtra("imageId", R.drawable.image_mint_leave_adjusted);

        mImageView=findViewById(R.id.myImageView);
        mImageView.setImageResource(imageId);
        mTitleView=findViewById(R.id.myImageViewText);
        mTitleView.setText(title);

        //Main ImageView return to Diary.class
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        //Declaring timestamp,title,body to be send to recycler view & length of recyclerview
        Timestamp timeStamp1=new Timestamp(1548450970000L); //in Miliseconds https://www.epochconverter.com/
        Timestamp timeStamp2=new Timestamp(1551129370000L);
        Timestamp timeStamp3=new Timestamp(1553548570000L);

        myTitleSet=new String[]{"Day1", "My Second Day"};
        myBodySet=new String[]{"This is my First Post!", "The plants seems to be increasing in lightning rate"};
        myTimeStamp= new Timestamp[]{timeStamp1,timeStamp2};
        NUM_LIST_ITEMS=myTitleSet.length;

        //Linear LayoutManger  attaching adapter to recyclerview
        mNumbersList= (RecyclerView)findViewById(R.id.rv_number);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        mNumbersList.setLayoutManager((layoutManager));
        mNumbersList.setHasFixedSize(true);
        mAdapter=new GreenAdapter(NUM_LIST_ITEMS,myTitleSet,myBodySet,myTimeStamp,this);
        mNumbersList.setAdapter(mAdapter);

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

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] image = stream.toByteArray();

            Intent intent = new Intent(this, Entries2.class);
            intent.putExtra("photo", image);
            intent.putExtra("myTimestamp", System.currentTimeMillis());//Send current Time
            startActivity(intent);

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

        //Send intent to entries 2 along with Data in
        Intent intent = new Intent(this, Entries2.class);
        intent.putExtra("myTimestamp", myTimeStamp[clickItemIndex].getTime());
        intent.putExtra("myTitleSet", myTitleSet[clickItemIndex]);
        intent.putExtra("myBodySet", myBodySet[clickItemIndex]);
        startActivity(intent);
    }
}
