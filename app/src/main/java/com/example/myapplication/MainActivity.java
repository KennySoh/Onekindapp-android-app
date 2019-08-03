package com.example.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {
    // Fragment codes
    private static final String TAG="MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private SectionsPagerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Always link new token instance to Amazon SNS Application endpoint: Register this device
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        Log.d("token", token);
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        //Swipe to refresh
        if(swipeRefreshLayout == null)
        {
            // Get swipe refresh layout object.
            swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
            // Set refresh indicator background color to green.
            swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
            // Set refresh indicator color to red.
            int indicatorColorArr[] = {R.color.colorWhite, R.color.colorWhite, R.color.colorGrey};
            swipeRefreshLayout.setColorSchemeResources(indicatorColorArr);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Http Call then stop swipeRefresh -> False
                httpCall();
            }
        });


        //Fragment codes
        mSectionsPagerAdapter= new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager) findViewById(R.id.container);

        adapter= new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment());
        adapter.addFragment(new Tab2Fragment());
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout=((TabLayout) findViewById(R.id.toolbarTop));
        tabLayout.setupWithViewPager(mViewPager);


        tabLayout.getTabAt(0).setText("Tank");
        tabLayout.getTabAt(1).setText("Information");
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));


        OkHttpClient client = new OkHttpClient();

        String url = "https://12od33mwyb.execute-api.us-east-2.amazonaws.com/dev/compare-yourself/all";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonarray = new JSONArray(myResponse);

//                                JSONObject jsonobject0 = jsonarray.getJSONObject(0);
//                                Tab1Fragment frag1 =(Tab1Fragment) adapter.getItem(0);
//                                Log.i("Fragments",frag1.setJsonData(jsonobject0));
//                                Tab1Fragment frag2 =(Tab1Fragment) adapter.getItem(2);
//                                Log.i("Fragments",frag2.setJsonData(myResponse));
//                                Tab1Fragment frag3 =(Tab1Fragment) adapter.getItem(2);
//                                Log.i("Fragments",frag3.setJsonData(myResponse));
                            }catch (Exception e){
                                //pass
                                Log.i("json","error");
                            }
                        }
                    });
                }
            }
        });
        // Http every 30 seconds
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                httpCall();
                handler.postDelayed(this, 6000); // Time change
            }
        };

        //Start
        handler.postDelayed(runnable, 1000);


        //Btm Navigation Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_data:
                        Toast.makeText(MainActivity.this, "Data", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_diary:
                        Toast.makeText(MainActivity.this, "Diary_duplicate", Toast.LENGTH_SHORT).show();
                        Intent diary_intent = new Intent(MainActivity.this, Diary_duplicate.class);
                        MainActivity.this.startActivity(diary_intent);
                        break;
                    case R.id.action_calendar:
                        Toast.makeText(MainActivity.this, "Calender", Toast.LENGTH_SHORT).show();
                        Intent lottie_intent = new Intent(MainActivity.this, Barcode_test.class);
                        MainActivity.this.startActivity(lottie_intent);

                        break;
                }
                return true;
            }
        });
    }
    public void httpCall(){
        OkHttpClient client = new OkHttpClient();

        String url = "https://12od33mwyb.execute-api.us-east-2.amazonaws.com/dev/compare-yourself/all";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // Set swiperefresh to false
                swipeRefreshLayout.setRefreshing(false);
                Log.i("httpcall","onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Log.i("httpcall",myResponse);

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Set swiperefresh to false
                            swipeRefreshLayout.setRefreshing(false);
                            JSONArray jsonarray = null;
                            JSONObject jsonobject0;
                            JSONObject jsonobject1;
                            JSONObject jsonobject2;

                            try {

                                //Null Json Object
                                JSONObject defaultData = new JSONObject();
                                defaultData.putOpt("waterlevel", "--");
                                defaultData.putOpt("watertemp", " -- ");
                                defaultData.putOpt("light", " -- ");
                                defaultData.putOpt("humidity", "--");
                                defaultData.putOpt("ambtemp", "--");

                                jsonarray = new JSONArray(myResponse);
                                jsonobject0 = jsonarray.getJSONObject(2);
                                jsonobject2 = defaultData;
                                Log.i("JSON", jsonobject0.toString());
                                jsonobject1=jsonarray.getJSONObject(2);
                                Tab1Fragment frag1 =(Tab1Fragment) adapter.getItem(0);
                                Log.i("Fragments",frag1.setJsonData(jsonobject1));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try{
                                jsonobject1=jsonarray.getJSONObject(2);
                                Log.i("Fragments",(jsonobject1.toString()));
                                Tab1Fragment frag2 =(Tab1Fragment) adapter.getItem(1);
                                Log.i("Fragments",frag2.setJsonData(jsonobject1));
                            }catch(Exception e){
                                //ignore
                            }
                        }
                    });
                }
            }
        });

    }
}
