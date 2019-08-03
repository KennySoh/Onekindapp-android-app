package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Arrays
    public int[] slide_images={
            R.raw.step_1,
            R.raw.step_2,
            R.raw.step_1,
            R.raw.step_2,
            R.raw.step_1
    };

    public String[] slide_headings={
            "Step 1",
            "Step 2",
            "Step 3",
            "Step 4",
            "Step 5"
    };

    public String[] slide_descs={
            "Insert Battery & Switch On",
            "Attach To Window Grill & Slot Module In",
            "Push & Connect Modules Together",
            "Add End pipe",
            "Water Your One Kind Window!"
    };
    public

    GifDrawable gifFromResource1;
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout)object;
    }

    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout1,container, false);

        ImageView slideImageView =(ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading= (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slide_description);


        try {
            gifFromResource1 = new GifDrawable( container.getResources(), slide_images[position]);
            slideImageView.setImageDrawable(gifFromResource1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        slideHeading.setText((slide_headings[position]));
        slideDescription.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout) object);
    }
}
