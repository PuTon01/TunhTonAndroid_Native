package com.example.teerasaksathu.tungton.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.teerasaksathu.tungton.R;

/**
 * Created by teerasaksathu on 28/3/2018 AD.
 */

public class Description extends AppCompatActivity {

    Button buttonpay;
    TextView textView9;
    private int sumPriceInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.description);

        init();


    }

    private void init() {

        textView9 = findViewById(R.id.textView9);
        Bundle bundle = getIntent().getExtras();
        sumPriceInt = bundle.getInt("sumPriceDescription");
        textView9.setText(String.valueOf(sumPriceInt));

        buttonpay = findViewById(R.id.button4);
        buttonpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Description.this, Main2Activity.class);
                intent.putExtra("sumPrice", sumPriceInt);
                startActivity(intent);
            }
        });


    }

}
