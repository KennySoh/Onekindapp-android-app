package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

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

        //TOdelete hackish
//        ((MainActivity)getActivity()).httpCall();

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
