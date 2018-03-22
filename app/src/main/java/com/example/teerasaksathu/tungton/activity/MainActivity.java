package com.example.teerasaksathu.tungton.activity;



import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import android.widget.TextView;



import com.example.teerasaksathu.tungton.R;
import com.example.teerasaksathu.tungton.dao.DataDao;
import com.example.teerasaksathu.tungton.dao.DataDaoFirst;
import com.example.teerasaksathu.tungton.manager.DataManager;
import com.example.teerasaksathu.tungton.manager.Http;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    String bacodeNumber ="8851727003056";
    SurfaceView cameraView;
    BarcodeDetector barcode;
    CameraSource cameraSource;
    SurfaceHolder holder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        bacodeScan();

        Call<DataDaoFirst> call = Http.getInstance().getApi().listProduct();

        call.enqueue(new Callback<DataDaoFirst>() {
            @Override
            public void onResponse(Call<DataDaoFirst> call, Response<DataDaoFirst> response) {
                if (response.isSuccessful()) {
                    DataDaoFirst dataDaoFirst = response.body();
                    DataManager.getInstance().setDaoFirst(dataDaoFirst);
                    Toast.makeText(MainActivity.this
                            ,dataDaoFirst.getDataDaos().get(0).getProductName()
                            ,Toast.LENGTH_LONG).show();
                }else {
                    try {
                        Toast.makeText(MainActivity.this
                                ,response.errorBody().string()
                                ,Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataDaoFirst> call, Throwable t) {
                Toast.makeText(MainActivity.this
                        ,t.toString()
                        ,Toast.LENGTH_LONG).show();
            }
        });






    }

    public void bacodeScan() {
        cameraView = findViewById(R.id.cameraView);
        cameraView.setZOrderMediaOverlay(true);
        holder = cameraView.getHolder();
        barcode = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();
        if(!barcode.isOperational()){
            Toast.makeText(this, "Sorry, Couldn't setup the detector", Toast.LENGTH_LONG).show();
            this.finish();
        }
        cameraSource = new CameraSource.Builder(MainActivity.this, barcode)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920,1024)
                .build();
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    if(ContextCompat.checkSelfPermission(MainActivity.this
                            , android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        cameraSource.start(cameraView.getHolder());
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        barcode.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes =  detections.getDetectedItems();
                if(barcodes.size() > 0){

                    Barcode barcode = barcodes.valueAt(0);
//                    bacodeNumber = barcode.displayValue;






                }
            }
        });
    }
}
