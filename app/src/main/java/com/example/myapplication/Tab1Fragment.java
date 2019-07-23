package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class Tab1Fragment extends Fragment {
    public static final String TAG = "Tab1Fragment";

    private TextView jsonText;
    private TextView dataText1;
    private TextView dataText2;
    private TextView dataText3;
    private TextView dataText4;
    private TextView dataText5;
    private TextView dataText6;
    private TextView dataText7;

    private String json;
    private String data_1;
    private String data_2;
    private String data_3;
    private String data_4;
    private String data_5;
    private String data_6;
    private String data_7;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);


        // Gif Animation
        ImageView animatedImageView1=(ImageView) view.findViewById(R.id.animatedGif_ImageView1);
        ImageView animatedImageView2=(ImageView) view.findViewById(R.id.animatedGif_ImageView2);
        ImageView animatedImageView3=(ImageView) view.findViewById(R.id.animatedGif_ImageView3);

        final GifDrawable gifFromResource1;
        final GifDrawable gifFromResource2;
        final GifDrawable gifFromResource3;


        try {
            gifFromResource1 = new GifDrawable( getResources(), R.raw.plant_normal);
            animatedImageView1.setImageDrawable(gifFromResource1);
//            animatedImageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    gifFromResource1.seekTo(10);
//                    gifFromResource1.start();
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Do something after 100ms
//                            gifFromResource1.stop();
//                        }
//                    }, 2000);
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            gifFromResource2 = new GifDrawable( getResources(), R.raw.sunny_mid_sun_normal);
            animatedImageView2.setImageDrawable(gifFromResource2);
            animatedImageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gifFromResource2.seekTo(10);
                    gifFromResource2.start();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            gifFromResource2.stop();
                        }
                    }, 2000);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            gifFromResource3 = new GifDrawable( getResources(), R.raw.sunny_mid_cloud_in);
            animatedImageView3.setImageDrawable(gifFromResource3);
            gifFromResource3.seekTo(10);
            gifFromResource3.start();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    gifFromResource3.stop();
                }
            }, 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }


        dataText1 = (TextView) view.findViewById(R.id.data_waterlevel);
        dataText2 = (TextView) view.findViewById(R.id.data_watertemp);
        dataText3 = (TextView) view.findViewById(R.id.data_sunlight);
        dataText4 = (TextView) view.findViewById(R.id.data_electricalconductivity);
        dataText5 = (TextView) view.findViewById(R.id.data_ph);
        dataText6 = (TextView) view.findViewById(R.id.data_humidity);
        dataText7 = (TextView) view.findViewById(R.id.data_ambtemp);
        if (data_1!=null) {
            dataText1.setText(data_1);
        }
        if (data_2!=null) {
            dataText2.setText(data_2);
        }
        if (data_3!=null) {
            dataText3.setText(data_3);
        }
        if (data_6!=null) {
            dataText6.setText(data_6);
        }
        if (data_7!=null) {
            dataText7.setText(data_7);
        }
        return view;
    }
    public String setJsonData(String mJson){
        // can remove when tab2 and tab3 is changed
        return "1";
    }
    public String setJsonData(JSONObject jsonobject){
        try {
            data_1 = jsonobject.getString("waterlevel");
            data_2 = jsonobject.getString("watertemp");
            data_3 = jsonobject.getString("light");
            //data_4 = jsonobject.getString("temp");
            //data_5 = jsonobject.getString("temp");
            data_6 = jsonobject.getString("humidity");
            data_7 = jsonobject.getString("ambtemp");

            dataText1.setText(data_1);
            dataText2.setText(data_2);
            dataText3.setText(data_3);
            dataText6.setText(data_6);
            dataText7.setText(data_7);

            Log.i("json",data_1);
        }catch (Exception e){
            //ignore
            Log.i("Fragments","Jsondata wrong");
            e.printStackTrace();
        }
        return "Data send";
    }
}
