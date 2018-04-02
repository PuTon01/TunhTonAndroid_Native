package com.example.teerasaksathu.tungton.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;


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
    private Boolean aBoolean;



    private NumberBarcode() {
        mContext = Contextor.getInstance().getContext();

    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {

        this.barcode = barcode;

    }

    public void setaBoolean(Boolean aBoolean) {

        this.aBoolean = aBoolean;

    }


}


