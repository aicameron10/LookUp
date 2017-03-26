package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DetailResponse {

    @SerializedName("products")
    private List<DetailProducts> products;

    public List<DetailProducts> getProducts() {
        return products;
    }

    public void setProducts(List<DetailProducts> products) {
        this.products = products;
    }

}
