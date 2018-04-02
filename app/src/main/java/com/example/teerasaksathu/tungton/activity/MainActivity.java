package com.example.teerasaksathu.tungton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Window;
import android.widget.TextView;
import com.example.teerasaksathu.tungton.R;
import com.example.teerasaksathu.tungton.dao.DataDao;
import com.example.teerasaksathu.tungton.manager.Http;
import com.example.teerasaksathu.tungton.manager.NumberBarcode;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    SurfaceView cameraView;
    BarcodeDetector barcode;
    CameraSource cameraSource;
    SurfaceHolder holder;
    Button pay;
    int sum = 0;
    TextView sumProduct,price, sumPrice,nameproduct,description;
    public static final int REQUEST_CODE = 100;
    String storeId = "1";
    Boolean bBoolean = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();


    }

    public void init() {

        sumPrice = findViewById(R.id.sumprice);
        sumProduct = findViewById(R.id.sumproduct);
        price = findViewById(R.id.price);
        nameproduct = findViewById(R.id.nameproduct);
        cameraView = findViewById(R.id.cameraView);
        cameraView.setZOrderMediaOverlay(true);
        holder = cameraView.getHolder();
        //ตัวอ่านบาร์โค้ด
        barcode = new BarcodeDetector.Builder(this)
                //ให้เลยอ่าน บาร์โค้ดได้หลานอย่าง
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();
        if(!barcode.isOperational()){
            //ถ้าไม่สามารถอ่านไก้จะขึ้นตรงนี้
            Toast.makeText(this, "Sorry, Couldn't setup the detector", Toast.LENGTH_LONG).show();
            this.finish();
        }
        cameraSource = new CameraSource.Builder(MainActivity.this, barcode)
                //จะกล้องหน้าหรือ กล้องหลัง
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                //เฟลมเล็ทของกล้อง
                .setRequestedFps(24)
                // ออโต้โฟกัส
                .setAutoFocusEnabled(true)
                //ขนาดกล้องที่จะ โชว์ว่าสัดส่วนเท่าไหร่
                .setRequestedPreviewSize(480,800)
                .build();
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    // เปิดสิทธ์ในการเข้าถึงกล้อง
                    if(ContextCompat.checkSelfPermission(MainActivity.this
                            , android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        //สั่งให้กล้องโชว์ที่ตัว SurfaceView
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
                //จะอ่านบาร์ดโค้ดแล้วจะเอามาโชว์ตัวนี้
                if(barcodes.size() > 0) {

                    Barcode barcode = barcodes.valueAt(0);
                    Log.d("Barcode ==>", barcode.displayValue);




                }
            }
        });

        pay = findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("sumPrice", sum);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
        description = findViewById(R.id.textView4);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Description.class);
                intent.putExtra("sumPriceDescription", sum);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        nameproduct.setText("");
        price.setText("0");
        sumPrice.setText("0");
        sum = 0;
        init();

    }

    public void sCanBarcode() {

        if (NumberBarcode.getInstance().getBarcode() != null ) {

            String f = NumberBarcode.getInstance().getBarcode();
            if (NumberBarcode.getInstance().getBarcode() != f) {
                Call<DataDao> call = Http.getInstance().getApi().listProduct(NumberBarcode.getInstance().getBarcode(), storeId);

                call.enqueue(new Callback<DataDao>() {
                    @Override
                    public void onResponse(Call<DataDao> call, Response<DataDao> response) {
                        if (response.isSuccessful()) {
                            NumberBarcode.getInstance().setaBoolean(true);
                            DataDao dataDao = response.body();
                            nameproduct.setText(dataDao.getProductName());
                            price.setText(dataDao.getProductPrice());
                            sum += Integer.parseInt(dataDao.getProductPrice());
                            sumPrice.setText(String.valueOf(sum));
                            bBoolean = true;

                        } else {
                            try {
                                Toast.makeText(MainActivity.this
                                        , response.errorBody().string()
                                        , Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataDao> call, Throwable t) {
                        Toast.makeText(MainActivity.this
                                , t.toString()
                                , Toast.LENGTH_LONG).show();
                    }
                });
            }


        } else {
            Toast.makeText(MainActivity.this
                    , "แสกนใหม่อีกครั้ง"
                    , Toast.LENGTH_LONG).show();
        }
    }
}
