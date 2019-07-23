package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.Html;
import android.widget.Toast;

public class OnBoarding extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

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
                OnBoarding.this.startActivity(main_intent);
            }
        });

    }

    public void addDotsIndicator(int position){
        mDots= new TextView[3];
        mDotLayout.removeAllViews();

        for(int i=0;i<mDots.length;i++){
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhiteOB));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhiteOB));
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
            if(position==2){
                mNextButton.setEnabled(true);
                mNextButton.setVisibility(View.VISIBLE);
            }else{
                mNextButton.setEnabled(false);
                mNextButton.setVisibility(View.INVISIBLE);
            }
            mSliderAdapter.restartVideo();
            mSliderViewPager.getCurrentItem();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
