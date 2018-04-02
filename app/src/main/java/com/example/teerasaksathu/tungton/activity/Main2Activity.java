package com.example.teerasaksathu.tungton.activity;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.teerasaksathu.tungton.R;




public class Main2Activity extends AppCompatActivity {

    TextView sumPrice, ton;
    EditText ed_pay;
    Button btnConfirm;
    String result,pay;
    int sumPriceInt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        init();
    }

    private void init() {

        sumPrice = findViewById(R.id.sum_price);



        Bundle bundle = getIntent().getExtras();
        sumPriceInt = bundle.getInt("sumPrice");
        sumPrice.setText(String.valueOf(sumPriceInt));
        ed_pay = findViewById(R.id.ed_pay);
        ton = findViewById(R.id.sumton);
        btnConfirm = findViewById(R.id.btnconfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                setResult(RESULT_OK, intent);
                startActivity(intent);

            }
        });
        result = sumPrice.getText().toString().trim();
        sumPriceInt = Integer.parseInt(result);

        ed_pay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pay = ed_pay.getText().toString();

                if (s.length() == 0) {
                    ton.setText("0");

                }else {
                    ton.setText(String.valueOf(Integer.parseInt(pay) - sumPriceInt));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
