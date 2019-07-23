package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class Lottie extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    String DEBUG_TAG="Lottie_activity";
    LottieAnimationView scrollAnimation;

    //vars
    private GestureDetector mGestureDetector;

    private float pos_x;
    private float pos_y;
    private float curr_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);

        scrollAnimation = findViewById(R.id.animation_view);
        scrollAnimation.pauseAnimation();
//        scrollAnimation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                animationClicked();
//            }
//        });

        scrollAnimation.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this,this);

        ImageView animatedImageView=(ImageView) findViewById(R.id.animatedGif_ImageView);
        ImageView animatedImageView1=(ImageView) findViewById(R.id.animatedGif1_ImageView);
        ImageView animatedImageView2=(ImageView) findViewById(R.id.animatedGif2_ImageView);
        ImageView animatedImageView3=(ImageView) findViewById(R.id.animatedGif3_ImageView);
        ImageView animatedImageView4=(ImageView) findViewById(R.id.animatedGif4_ImageView);
        ImageView animatedImageView5=(ImageView) findViewById(R.id.animatedGif5_ImageView);
        ImageView animatedImageView6=(ImageView) findViewById(R.id.animatedGif6_ImageView);
        ImageView animatedImageView7=(ImageView) findViewById(R.id.animatedGif7_ImageView);
        ImageView animatedImageView8=(ImageView) findViewById(R.id.animatedGif8_ImageView);
        ImageView animatedImageView9=(ImageView) findViewById(R.id.animatedGif9_ImageView);
        ImageView animatedImageView10=(ImageView) findViewById(R.id.animatedGif10_ImageView);
        ImageView animatedImageView11=(ImageView) findViewById(R.id.animatedGif11_ImageView);
        ImageView animatedImageView12=(ImageView) findViewById(R.id.animatedGif12_ImageView);
        ImageView animatedImageView13=(ImageView) findViewById(R.id.animatedGif13_ImageView);
        ImageView animatedImageView14=(ImageView) findViewById(R.id.animatedGif14_ImageView);
        ImageView animatedImageView15=(ImageView) findViewById(R.id.animatedGif15_ImageView);
        ImageView animatedImageView16=(ImageView) findViewById(R.id.animatedGif15_ImageView);


        try {
            final GifDrawable gifFromResource = new GifDrawable( getResources(), R.raw.plant_normal);
            animatedImageView.setImageDrawable(gifFromResource);
            animatedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifFromResource.seekTo(10);
                gifFromResource.start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        gifFromResource.stop();
                    }
                }, 2000);
            }
        });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Glide.with(this)
                .load(R.raw.sunny_mid_sun_normal)
                .into(animatedImageView1);

        Glide.with(this)
                .load(R.raw.sunny_bright_cloud_in)
                .into(animatedImageView2);

        Glide.with(this)
                .load(R.raw.sunny_bright_cloud_normal)
                .into(animatedImageView3);

        Glide.with(this)
                .load(R.raw.sunny_bright_cloud_out)
                .into(animatedImageView5);
        Glide.with(this)
                .load(R.raw.sunny_mid_cloud_in)
                .into(animatedImageView6);
        Glide.with(this)
                .load(R.raw.sunny_mid_cloud_normal)
                .into(animatedImageView7);
        Glide.with(this)
                .load(R.raw.sunny_mid_cloud_out)
                .into(animatedImageView8);
        Glide.with(this)
                .load(R.raw.sunny_mid_sun_in)
                .into(animatedImageView9);
        Glide.with(this)
                .load(R.raw.sunny_mid_sun_normal)
                .into(animatedImageView10);
        Glide.with(this)
                .load(R.raw.sunny_mid_sun_out)
                .into(animatedImageView11);
        Glide.with(this)
                .load(R.raw.plant_dead)
                .into(animatedImageView12);
        Glide.with(this)
                .load(R.raw.plant_drooping_to_dead)
                .into(animatedImageView13);

        Glide.with(this)
                .load(R.raw.plant_normal)
                .into(animatedImageView14);
        Glide.with(this)
                .load(R.raw.plant_normal_to_drooping)
                .into(animatedImageView15);

        Glide.with(this)
                .load(R.raw.plant_reverse_drooping_to_normal)
                .into(animatedImageView16);


    }
    private void animationClicked() {
        scrollAnimation.playAnimation();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action =event.getAction();
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                pos_x=event.getX();
                pos_y=event.getY();
                curr_progress=scrollAnimation.getProgress();

                Log.d(DEBUG_TAG,"Action was DOWN: (x,y) ("+ event.getX()+" , "+event.getY()+")");
                Log.d(DEBUG_TAG,"Animation Progress: "+String.valueOf(scrollAnimation.getProgress()));
                return true;
            case (MotionEvent.ACTION_MOVE) :
                //Per dots shift 0.1f, width 1000f, 1700f height
                // divide dx by 5000f
                float animate_scale=5000f;
                float dx=event.getX()-pos_x;
                float dy=event.getY()-pos_y;
                scrollAnimation.setProgress(curr_progress+dx/animate_scale);

                Log.d(DEBUG_TAG,"Action was MOVE");
                Log.d(DEBUG_TAG,"onTouch: (x,y) ("+ event.getX()+" , "+event.getY()+")");

                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }


//        if(v.getId()==R.id.animation_view){
//            mGestureDetector.onTouchEvent(event);
//            return true;
//        }
//
//        return false;
    }

    /*GestureDetector*/
    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(DEBUG_TAG,"onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(DEBUG_TAG,"onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(DEBUG_TAG,"onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(DEBUG_TAG,"onScroll (x,y) :( "+ String.valueOf(distanceX)+" , "+String.valueOf(distanceY)+" )");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        Log.d(DEBUG_TAG,"onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(DEBUG_TAG,"onFling");
        return false;
    }
}
