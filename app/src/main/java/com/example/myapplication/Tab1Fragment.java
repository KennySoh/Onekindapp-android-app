package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Half;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

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

    private ViewSwitcher simpleViewSwitcher;

    GifDrawable gifFromResource1;
    GifDrawable gifFromResource2;
    GifDrawable gifFromResource3;
    GifDrawable gifFromResource4;
    GifDrawable gifFromResource5;
    GifDrawable gifFromResource6;
    GifDrawable gifFromResource7;
    GifDrawable gifFromResource8;

    ImageView animatedImageView1;// Plant gif
    ImageView animatedImageView2;// Sun gif
    ImageView animatedImageView3;// Mid Cloud
    ImageView animatedImageView4;// Water Container
    ImageView animatedImageView5;// Bright Cloud
    ImageView animatedImageView6;// Stars gif
    ImageView animatedImageView7;// Moon gif
    ImageView animatedImageView8;// Temp gif

    private TextView datafText1;
    private TextView datafText2;
    private TextView datafText3;
    private TextView datafText4;

    private float dataf_3=500;
    private float dataf_1=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        // Data text displays
        datafText1=view.findViewById(R.id.data_sunlight1);
        datafText2=view.findViewById(R.id.data_ambtemp1);
        datafText3=view.findViewById(R.id.data_watertemp1);
        datafText4=view.findViewById(R.id.data_waterlevel1);

        //View Switcher
        // get The references of Button and ViewSwitcher
        simpleViewSwitcher = (ViewSwitcher) view.findViewById(R.id.simpleViewSwitcher); // get the reference of ViewSwitcher
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_out_right);

        // set the animation type to ViewSwitcher
        simpleViewSwitcher.setInAnimation(in);
        simpleViewSwitcher.setOutAnimation(out);
        //simpleViewSwitcher.showNext(); To show next image


        datafText1.setText("--"+" kw/m\u00B2");
        datafText2.setText(("--.-") + " \u2103");
        datafText3.setText(("--.-") + "\u2103");

        // Gif Animation
        animatedImageView1=(ImageView) view.findViewById(R.id.animatedGif_ImageView1);
        animatedImageView2=(ImageView) view.findViewById(R.id.animatedGif_ImageView2);
        animatedImageView3=(ImageView) view.findViewById(R.id.animatedGif_ImageView3);
        animatedImageView4=(ImageView) view.findViewById(R.id.animatedGif_ImageView4);
        animatedImageView5=(ImageView) view.findViewById(R.id.animatedGif_ImageView5);
        animatedImageView6=(ImageView) view.findViewById(R.id.animatedGif_ImageView6);
        animatedImageView7=(ImageView) view.findViewById(R.id.animatedGif_ImageView7);
        animatedImageView8=(ImageView) view.findViewById(R.id.animatedGif_ImageView8);

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
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            gifFromResource4 = new GifDrawable( getResources(), R.raw.water_container);
            animatedImageView4.setImageDrawable(gifFromResource4);
            gifFromResource4.seekTo(0);
            gifFromResource4.start();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    gifFromResource4.stop();
                }
            }, 11500);
        } catch (IOException e) {
            e.printStackTrace();
        }

        animatedImageView6.setImageDrawable(null);

        try {
            gifFromResource8 = new GifDrawable( getResources(), R.raw.windblows);
            animatedImageView8.setImageDrawable(gifFromResource8);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    gifFromResource8.stop();
                    gifFromResource8.seekTo(0);
                    animatedImageView8.setImageDrawable(null);
                }
            }, 1500);
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

            datafText1.setText(data_3+" kw/m\u00B2");
            datafText2.setText(data_7 + " \u2103");
            datafText3.setText((data_2) + "\u2103");


            //Previous: dataf_3, New: data_3f,
            float data_3f=Float.valueOf(data_3); //Sunlight

            float brightCloud=130.0f;
            float midCloud=70.0f;
            float night=30.0f;

            //Bright Cloud Transition in
            if((dataf_3>brightCloud)||(dataf_3<=midCloud)){
                if(data_3f<=brightCloud&&data_3f>midCloud) {
                    Log.i("TEST",String.valueOf(data_3f)+" , "+String.valueOf(dataf_3));
                    Log.i("TEST","Bright Cloud In");
                    try {
                        gifFromResource5 = new GifDrawable( getResources(), R.raw.sunny_bright_cloud_in);
                        animatedImageView5.setImageDrawable(gifFromResource5);
                        gifFromResource5.start();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                gifFromResource5.stop();
                            }
                        }, 4000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            //Bright Cloud Transition Out
            if((dataf_3<=brightCloud&&dataf_3>=midCloud)){
                if(data_3f>brightCloud||data_3f<=midCloud) {
                    Log.i("TEST",String.valueOf(data_3f)+" , "+String.valueOf(dataf_3));
                    Log.i("TEST","Bright Cloud In");
                    try {
                        gifFromResource5 = new GifDrawable( getResources(), R.raw.sunny_bright_cloud_out);
                        animatedImageView5.setImageDrawable(gifFromResource5);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                gifFromResource5.stop();
                            }
                        }, 5000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            //Mid Cloud Transition in (midCloud^ -> midCloud) || (night_down -> mid_cloud)
            if((dataf_3>midCloud)||(dataf_3<=night)){
                if(data_3f<=midCloud&&data_3f>night) {
                    Log.i("TEST",String.valueOf(data_3f)+" , "+String.valueOf(dataf_3));
                    Log.i("TEST","Mid Cloud Transition in ");
                    try {
                        gifFromResource3 = new GifDrawable( getResources(), R.raw.sunny_mid_cloud_in);
                        animatedImageView3.setImageDrawable(gifFromResource3);
                        gifFromResource3.start();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                gifFromResource3.stop();
                            }
                        }, 4000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            //Mid Cloud Transition Out
            if((dataf_3<midCloud)&&dataf_3>night){
                if(data_3f>=midCloud||data_3f<=night) {
                    Log.i("TEST",String.valueOf(data_3f)+" , "+String.valueOf(dataf_3));
                    Log.i("TEST","Mid Cloud Out");
                    try {
                        gifFromResource3 = new GifDrawable( getResources(), R.raw.sunny_mid_cloud_out);
                        animatedImageView3.setImageDrawable(gifFromResource3);
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

                }
            }

            //Background Morning-> Night
            if((dataf_3>night)){
                if(data_3f<=night) {
                    //Background Change
                    simpleViewSwitcher.showNext();

                    //Star gif
                    try {
                        gifFromResource6 = new GifDrawable( getResources(), R.raw.star);
                        animatedImageView6.setImageDrawable(gifFromResource6);
                        gifFromResource6.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Sun Out & Moon in
                    try {
                        gifFromResource2 = new GifDrawable( getResources(), R.raw.sunny_mid_sun_out);
                        animatedImageView2.setImageDrawable(gifFromResource2);

                        gifFromResource7 = new GifDrawable( getResources(), R.raw.sunny_night_moon_in);
                        animatedImageView7.setImageDrawable(gifFromResource7);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                gifFromResource2.stop();

                                try {
                                    gifFromResource7= new GifDrawable( getResources(), R.raw.sunny_night_moon_normal);
                                    animatedImageView7.setImageDrawable(gifFromResource7);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 5000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    datafText1.setTextColor(Color.parseColor("#F2DDCB"));
                    datafText2.setTextColor(Color.parseColor("#F2DDCB"));
                    //datafText3.setTextColor(Color.parseColor("#F2DDCB"));
                    datafText4.setTextColor(Color.parseColor("#F2DDCB"));

                }
            }

            //Background Night-> Morning
            if((dataf_3<=night)){
                if(data_3f>night) {
                    simpleViewSwitcher.showNext();
                    //Stars disappear
                    animatedImageView6.setImageDrawable(null);

                    //Sun In & Moon Out
                    try {
                        gifFromResource2 = new GifDrawable( getResources(), R.raw.sunny_mid_sun_in);
                        animatedImageView2.setImageDrawable(gifFromResource2);

                        gifFromResource7 = new GifDrawable( getResources(), R.raw.sunny_night_moon_out);
                        animatedImageView7.setImageDrawable(gifFromResource7);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                gifFromResource7.stop();

                                try {
                                    gifFromResource2= new GifDrawable( getResources(), R.raw.sunny_mid_sun_normal);
                                    animatedImageView2.setImageDrawable(gifFromResource2);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 5000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    datafText1.setTextColor(Color.parseColor("#E1221D1D"));
                    datafText2.setTextColor(Color.parseColor("#E1221D1D"));
                    //datafText3.setTextColor(Color.parseColor("#F2DDCB"));
                    datafText4.setTextColor(Color.parseColor("#E1221D1D"));


                }
            }

            dataf_3=data_3f;// Must put animation above (Within data_3f & dataf_3)

            //Previous: dataf_1, New: data_1f,
            float data_1f=Float.valueOf(data_1); //Waterlevel

            float emptyTank=0;
            float halfTank=1;
            float fullTank=2;

            //Empty ->Half
            if((dataf_1<emptyTank+0.1f)) {
                if (data_1f +0.1f>halfTank&&data_1f<fullTank) {
                    Log.i("Animations","Empty->Half");
                    Log.i("Animations",String.valueOf(dataf_1)+" , "+String.valueOf(data_1f));
                    gifFromResource1 = new GifDrawable( getResources(), R.raw.plant_normal_to_drooping);
                    animatedImageView1.setImageDrawable(gifFromResource1);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            gifFromResource1.stop();

                            try {
                                gifFromResource1= new GifDrawable( getResources(), R.raw.plant_drooping);
                                animatedImageView1.setImageDrawable(gifFromResource1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 5000);
                }
            }

            //Empty ->Full
            if((dataf_1==emptyTank)) {
                if (data_1f == halfTank) {

                }
            }

            //Half-> Empty
            if((dataf_1== halfTank)) {
                if (data_1f == emptyTank) {

                }
            }

            //Half -> Full
            if((dataf_1==halfTank)) {
                if (data_1f == fullTank) {

                }
            }

            //Full ->Half
            if((dataf_1==fullTank)) {
                if (data_1f == halfTank) {

                }
            }

            //Full ->Empty
            if((dataf_1==fullTank)) {
                if (data_1f == emptyTank) {

                }
            }

            //Previous: dataf_1, New: data_1f,
            dataf_1=data_1f;// WaterLevel


            if(data_1.equals("0")) {
                datafText4.setText("Empty");
            }
            if(data_1.equals("1")) {
                datafText4.setText("Half");
            }
            if(data_1.equals("2")) {
                datafText4.setText("Full");
            }


            if(Float.parseFloat(data_2)>30.0){
                //High Temperature animation
                gifFromResource1.seekTo(100);
            }

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
