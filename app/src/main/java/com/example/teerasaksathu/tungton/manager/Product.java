package com.example.teerasaksathu.tungton.manager;

/**
 * Created by teerasaksathu on 2/4/2018 AD.
 */

public class Product {

    private int productId;
    private int saleQuantity;
    private double salePrice;
    private double saleTotalPrice;

    public Product(int productId, int saleQuantity, double salePrice) {
        this.productId = productId;
        this.saleQuantity = saleQuantity;
        this.salePrice = salePrice;
        this.saleTotalPrice = getSaleTotalPrice();
    }

    public int getProductId() {
        return productId;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity += saleQuantity;
    }


    public double getSalePrice() {
        return salePrice;
    }


    public double getSaleTotalPrice() {
        return this.saleQuantity * this.salePrice;
    }

    public void setSaleTotalPrice() {
        this.saleTotalPrice = getSaleTotalPrice();
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", saleQuantity=" + saleQuantity +
                ", salePrice=" + salePrice +
                ", saleTotalPrice=" + saleTotalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (saleQuantity != product.saleQuantity) return false;
        if (Double.compare(product.salePrice, salePrice) != 0) return false;
        return Double.compare(product.saleTotalPrice, saleTotalPrice) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productId;
        result = 31 * result + saleQuantity;
        temp = Double.doubleToLongBits(salePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(saleTotalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
