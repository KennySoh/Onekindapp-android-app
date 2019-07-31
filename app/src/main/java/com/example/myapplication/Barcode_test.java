package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.w3c.dom.Text;

public class Barcode_test extends AppCompatActivity {
    Toolbar toolbar;

    //youtube
    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_test);

        //Setting backbutton on toolbar to go back
        toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Youtube
        surfaceView=(SurfaceView)findViewById(R.id.camerapreview);
        textView=(TextView)findViewById(R.id.textView);

        barcodeDetector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();
        if(!barcodeDetector.isOperational()){
            textView.setText("Could not set up the detector!");
            return;
        }

        cameraSource=new CameraSource.Builder(this,barcodeDetector).setRequestedPreviewSize(640, 480).build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try {
                    cameraSource.start(holder);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();

                if(qrCodes.size()!=0){
                    textView.post(new Runnable(){

                        @Override
                        public void run() {
                            Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            textView.setText(qrCodes.valueAt(0).displayValue);

                            //Send data via Intent, add into main activity
                            Intent myIntent=new Intent();
                            myIntent.putExtra("qrCode",qrCodes.valueAt(0).displayValue);
                            setResult(RESULT_OK, myIntent);
                            finish();

                        }
                    });
                }
            }
        });
        //Google documentation
//        TextView txtView = (TextView) findViewById(R.id.txtContent);
//        Button btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//
//        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
//        Bitmap myBitmap = BitmapFactory.decodeResource(
//                getApplicationContext().getResources(),
//                R.drawable.puppy);
//        myImageView.setImageBitmap(myBitmap);
//
//        BarcodeDetector detector =
//                new BarcodeDetector.Builder(getApplicationContext())
//                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
//                        .build();
//        if(!detector.isOperational()){
//            txtView.setText("Could not set up the detector!");
//            return;
//        }
//
//        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
//        SparseArray<Barcode> barcodes = detector.detect(frame);
//
//        Barcode thisCode = barcodes.valueAt(0);
//        txtView.setText(thisCode.rawValue);
    }
}
