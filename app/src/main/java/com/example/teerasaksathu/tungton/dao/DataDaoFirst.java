package com.example.teerasaksathu.tungton.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by teerasaksathu on 20/3/2018 AD.
 */

public class DataDaoFirst {

    @SerializedName("products")
    private List<DataDao> dataDaos;

    public List<DataDao> getDataDaos() {
        return dataDaos;
    }

    public void setDataDaos(List<DataDao> dataDaos) {
        this.dataDaos = dataDaos;
    }
}
