package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
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

        Glide.with(this)
                .load(R.raw.test)
                .into(animatedImageView);

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
