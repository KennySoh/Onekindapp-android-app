package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Home extends AppCompatActivity {

    LottieAnimationView animationView1;
    LottieAnimationView animationView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Animation Transition
        setAnimation();

        //Lottie Animations
        animationView1= (LottieAnimationView) findViewById(R.id.animation_view1);
        animationView2= (LottieAnimationView) findViewById(R.id.animation_view2);

        animationView2.pauseAnimation();//Pause Welcome Text to ply after logo completes

        animationView1.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("Animation:","start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("Animation:","end");
                animationView2.playAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("Animation:","cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("Animation:","repeat");
            }
        });

        animationView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Home", Toast.LENGTH_SHORT).show();
                Intent onboarding_intent = new Intent(Home.this, OnBoarding.class);
                if(Build.VERSION.SDK_INT>20){
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(Home.this);
                    startActivity(onboarding_intent,options.toBundle());
                }else {
                    startActivity(onboarding_intent);
                }
            }
        });


    }
    public void setAnimation(){
        if(Build.VERSION.SDK_INT>20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }
}
