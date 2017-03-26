package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SearchResponse {

    @SerializedName("originalRequest")
    private Object originalRequest;
    @SerializedName("products")
    private List<SearchProducts> products;
    @SerializedName("totalResultSize")
    private int totalResultSize;



    public Object getOriginalRequest() {
        return originalRequest;
    }

    public void setOriginalRequest(Object originalRequest) {
        this.originalRequest = originalRequest;
    }

    public List<SearchProducts> getProducts() {
        return products;
    }

    public void setProducts(List<SearchProducts> products) {
        this.products = products;
    }

    public int getTotalResultSize() {
        return totalResultSize;
    }

    public void setTotalResultSize(int totalResultSize) {
        this.totalResultSize = totalResultSize;
    }

}
