package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;


public class Accessories {

    @SerializedName("productId")
    private String productId;


    public Accessories(String productId) {
        this.productId = productId;

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
