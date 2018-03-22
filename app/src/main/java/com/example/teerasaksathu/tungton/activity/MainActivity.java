package com.example.teerasaksathu.tungton.activity;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.example.teerasaksathu.tungton.R;
import com.example.teerasaksathu.tungton.dao.DataDao;
import com.example.teerasaksathu.tungton.manager.Http;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity {

    TextView result;


    SurfaceView cameraView;
    BarcodeDetector barcode;
    CameraSource cameraSource;
    SurfaceHolder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        Call<DataDao> call= Http.getInstance().getApi().listProduct();
        call.enqueue(new Callback<DataDao>() {
            @Override
            public void onResponse(Call<DataDao> call, Response<DataDao> response) {


                if (response.isSuccessful()) {
                    DataDao dataDao = response.body();
                    Log.d("teset =>", dataDao.getProductName());
                }else {
                    try {
                        Log.d("erorr =>",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataDao> call, Throwable t) {
                Log.d("onFailure =>", t.toString());
            }
        });



//        cameraView = findViewById(R.id.cameraView);
//        cameraView.setZOrderMediaOverlay(true);
//        holder = cameraView.getHolder();
//        barcode = new BarcodeDetector.Builder(this)
//                .setBarcodeFormats(Barcode.ALL_FORMATS)
//                .build();
//        if(!barcode.isOperational()){
//            Toast.makeText(getApplicationContext(), "Sorry, Couldn't setup the detector", Toast.LENGTH_LONG).show();
//            this.finish();
//        }
//        cameraSource = new CameraSource.Builder(this, barcode)
//                .setFacing(CameraSource.CAMERA_FACING_BACK)
//                .setRequestedFps(24)
//                .setAutoFocusEnabled(true)
//                .setRequestedPreviewSize(720,720)
//                .build();
//        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                try{
//                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
//                        cameraSource.start(cameraView.getHolder());
//                    }
//                }
//                catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//
//            }
//        });
//        barcode.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> barcodes =  detections.getDetectedItems();
//                if(barcodes.size() > 0){
//
//                    Barcode barcode = barcodes.valueAt(0);
//
//                    Log.d("test => ", barcode.displayValue);
//
//                }
//            }
//        });

    }


}
