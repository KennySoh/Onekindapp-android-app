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

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    VideoView videoView;

    public void restartVideo(){
      videoView.seekTo(0);
    };

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Arrays
    public int[] slide_images={
            R.drawable.group10,
            R.drawable.group11,
            R.drawable.group12
    };

    public String[] slide_headings={
            "EAT",
            "SLEEP",
            "Code"
    };

    public String[] slide_descs={
            "Step 1",
            "Step 2",
            "Step 3"
    };
    public Integer[] slide_videos={
            R.raw.video_tasty,
            R.raw.step2,
            R.raw.step4_test
    };

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
        videoView=(VideoView)view.findViewById(R.id.videoView);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText((slide_headings[position]));
        slideDescription.setText(slide_descs[position]);
        videoView.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+slide_videos[position]));
        videoView.setMediaController(new MediaController(context));
        videoView.requestFocus();
        videoView.start();

        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout) object);
    }
}
