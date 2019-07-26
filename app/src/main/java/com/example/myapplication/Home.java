package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class Home extends AppCompatActivity {

    GifDrawable gifFromResource;
    ImageView animatedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        animatedImageView=findViewById(R.id.animatedGif_ImageView);
        try {
            gifFromResource = new GifDrawable( getResources(), R.raw.logo_start);
            animatedImageView.setImageDrawable(gifFromResource);
            gifFromResource.seekTo(0);
            gifFromResource.start();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    gifFromResource.stop();
                    Log.i("Animation position",String.valueOf(gifFromResource.getCurrentPosition()));

                }
            }, 4000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        animatedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onBoarding_intent = new Intent(Home.this, OnBoarding.class);
                Home.this.startActivity(onBoarding_intent);
            }
        });

    }
}
