package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class Entries extends AppCompatActivity implements GreenAdapter.ListItemClickListener {
    String Tag="Entries";
    ImageView mImageView;
    TextView mTitleView;

    private int ENTRIES_2_CODE=201;


    //Recycler View
    private int NUM_LIST_ITEMS=100;
    GreenAdapter mAdapter;
    RecyclerView mNumbersList;
    ArrayList<String> myTitleSet;
    ArrayList<String> myBodySet;
    ArrayList<Long> myTimeStamp;
    ArrayList<byte[]> myImageSet;
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
//        Timestamp timeStamp1=new Timestamp(1548450970000L); //in Miliseconds https://www.epochconverter.com/
        myTitleSet=new ArrayList<String>();
        myBodySet=new ArrayList<String>();
        myTimeStamp= new ArrayList<Long>();
        myImageSet= new ArrayList<byte[]>();
        NUM_LIST_ITEMS=myTitleSet.size();

        //Linear LayoutManger  attaching adapter to recyclerview
        mNumbersList= (RecyclerView)findViewById(R.id.rv_number);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        mNumbersList.setLayoutManager((layoutManager));
        mNumbersList.setHasFixedSize(true);
        mAdapter=new GreenAdapter(NUM_LIST_ITEMS,myTitleSet,myBodySet,myTimeStamp,myImageSet,this);
        mNumbersList.setAdapter(mAdapter);

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Toast.makeText(Entries.this, "Take A Photo of your plant!", Toast.LENGTH_SHORT).show();
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
            myImageSet.add(image);

            Intent intent = new Intent(this, Entries2.class);
            intent.putExtra("photo", image);
            long timeStamp_mili=System.currentTimeMillis();
            myTimeStamp.add(timeStamp_mili);
            intent.putExtra("myTimestamp", timeStamp_mili);//Send current Time
            Entries.this.startActivityForResult(intent,ENTRIES_2_CODE);

        }

        if (requestCode==ENTRIES_2_CODE){
            try {
                //add Title,Body,TimeStamp,Pictures into RecyclerView from entries2
                byte[] byteArrayExtra1 = data.getByteArrayExtra("photo");
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayExtra1, 0, byteArrayExtra1.length, new BitmapFactory.Options());

                String listSubtitle1_s=data.getStringExtra("title");
                String listSubtitle2_s=data.getStringExtra("body");
                Log.i("mAdapterE1_rc1", "Title: "+listSubtitle1_s+", Body: "+listSubtitle2_s);

                myTitleSet.add(data.getStringExtra("title"));
                myBodySet.add(data.getStringExtra("body"));
                Log.i("mAdapter", "Title: "+myTitleSet.get(0)+", Body: "+myBodySet.get(0)+", Timestamp: "+myTimeStamp.get(0));
                mAdapter.updateData();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onListItemClick(int clickItemIndex) {

        //Send intent to entries 2 along with Data in
        Intent intent = new Intent(this, Entries2.class);
        intent.putExtra("myTimestamp", myTimeStamp.get(clickItemIndex));
        intent.putExtra("myTitleSet", myTitleSet.get(clickItemIndex));
        intent.putExtra("myBodySet", myBodySet.get(clickItemIndex));
        intent.putExtra("photo", myImageSet.get(clickItemIndex));
        startActivity(intent);
    }
}
