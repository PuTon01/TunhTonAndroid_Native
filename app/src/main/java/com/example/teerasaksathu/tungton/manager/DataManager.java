package com.example.teerasaksathu.tungton.manager;

import android.content.Context;

import com.example.teerasaksathu.tungton.ApiService.Api;
import com.example.teerasaksathu.tungton.dao.DataDaoFirst;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class DataManager {

    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    private Context mContext;
    private DataDaoFirst daoFirst;
    private DataManager() {
        mContext = Contextor.getInstance().getContext();


    }

    public DataDaoFirst getDaoFirst() {
        return daoFirst;
    }

    public void setDaoFirst(DataDaoFirst daoFirst) {
        this.daoFirst = daoFirst;
    }


}


