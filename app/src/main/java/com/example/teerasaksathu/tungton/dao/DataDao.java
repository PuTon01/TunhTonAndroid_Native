package com.example.teerasaksathu.tungton.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by teerasaksathu on 19/3/2018 AD.
 */

public class DataDao {

    @SerializedName("productId")
    private String productId;
    @SerializedName("productBarcode")
    private String productBarcode;
    @SerializedName("productName")
    private String productName;
    @SerializedName("productBrand")
    private String productBrand;
    @SerializedName("productManufacturer")
    private String productManufacturer;
    @SerializedName("productSize")
    private String productSize;
    @SerializedName("productStatus")
    private String productStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
