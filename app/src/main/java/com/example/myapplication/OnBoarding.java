package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.Html;
import android.widget.Toast;

public class OnBoarding extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;

    private ImageView[] mDots;

    private SliderAdapter mSliderAdapter;

    private Button mNextButton;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        mSliderViewPager= (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout=(LinearLayout) findViewById(R.id.dotsLayout);
        mNextButton=(Button) findViewById(R.id.nextButton);

        mSliderAdapter=new SliderAdapter(this);
        mSliderViewPager.setAdapter(mSliderAdapter);

        addDotsIndicator(0);

        mSliderViewPager.addOnPageChangeListener(viewListener);

        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // Can use mCurrentPosition to change buttons based on page
                // Just send to a different activity
                Toast.makeText(OnBoarding.this, "Diary", Toast.LENGTH_SHORT).show();
                Intent main_intent = new Intent(OnBoarding.this, MainActivity.class);
                finish();
                OnBoarding.this.startActivity(main_intent);
            }
        });

    }

    public void addDotsIndicator(int position){
        mDots= new ImageView[5];
        mDotLayout.removeAllViews();
        for(int i=0;i<mDots.length;i++){
            mDots[i]= new ImageView(this);
            mDots[i].setImageResource(R.drawable.unselecteddots);
            mDots[i].setScaleType(ImageView.ScaleType.CENTER_CROP);

            mDotLayout.addView(mDots[i]);
            mDots[i].getLayoutParams().height=30;
            mDots[i].getLayoutParams().width=60;
            mDots[i].setPadding(0, 0, 30, 0);
        }

        if(mDots.length>0){
            mDots[position].setImageResource(R.drawable.selecteddots);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage=position;
            if(position==4){
                mNextButton.setEnabled(true);
                mNextButton.setVisibility(View.VISIBLE);
            }else{
                mNextButton.setEnabled(false);
                mNextButton.setVisibility(View.INVISIBLE);
            }
            mSliderViewPager.getCurrentItem();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
