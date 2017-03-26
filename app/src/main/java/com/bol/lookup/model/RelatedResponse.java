package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RelatedResponse {

    @SerializedName("accessories")
    private List<Accessories> accessories;

    public List<Accessories> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessories> accessories) {
        this.accessories = accessories;
    }


}
