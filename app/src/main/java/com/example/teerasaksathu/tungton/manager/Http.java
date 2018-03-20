package com.example.teerasaksathu.tungton.manager;

import android.content.Context;

import com.example.teerasaksathu.tungton.ApiService.Api;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class Http {

    private static Http instance;

    public static Http getInstance() {
        if (instance == null)
            instance = new Http();
        return instance;
    }

    private Context mContext;
    private Api api;

    private Http() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://150.95.26.138:3000/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

    }

    public Api getApi() {
        return api;
    }
}
