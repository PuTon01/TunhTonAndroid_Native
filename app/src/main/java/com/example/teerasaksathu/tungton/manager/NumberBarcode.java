package com.example.teerasaksathu.tungton.manager;

import android.content.Context;
import android.util.Log;

import com.example.teerasaksathu.tungton.ApiService.Api;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class NumberBarcode {

    private static NumberBarcode instance;

    public static NumberBarcode getInstance() {
        if (instance == null)
            instance = new NumberBarcode();
        return instance;
    }

    private Context mContext;
    private String barcode;

    private NumberBarcode() {
        mContext = Contextor.getInstance().getContext();

    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        if (barcode != null) {
            this.barcode = barcode;
        }else {
            Log.d("ressetbarcode =>", barcode);
        }
    }
}
