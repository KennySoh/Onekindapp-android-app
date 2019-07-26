package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Entries2 extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView1;
    Button btnSave;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries2);
        toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        imageView1=findViewById(R.id.imageView1);
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("photo");

        //BitmapOptions is optional you can create bitmap without this also. This is the description of its use from google developer docs.
        //BitmapFactory.Options: null-ok; Options that control downsampling and whether the image should be completely decoded, or just is size returned.

        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length, new BitmapFactory.Options());
        imageView1.setImageBitmap(bitmap);

        btnSave = (Button) findViewById(R.id.buttonNext);

        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Into SQL DataBase
            }
        });

    }

    @Override
    public void setSupportActionBar(@Nullable androidx.appcompat.widget.Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }

}
