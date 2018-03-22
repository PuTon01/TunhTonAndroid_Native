package com.example.teerasaksathu.tungton.ApiService;

import com.example.teerasaksathu.tungton.dao.DataDaoFirst;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;

/**
 * Created by teerasaksathu on 19/3/2018 AD.
 */

public interface Api {

    @GET("products")
    Call<DataDaoFirst> listProduct();
}
