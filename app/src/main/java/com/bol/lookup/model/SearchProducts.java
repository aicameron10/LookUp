package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SearchProducts {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("images")
    private List<SearchImages> images;
    @SerializedName("offerData")
    private OfferData offerData;


    public SearchProducts(String id, String title, List<SearchImages> images, OfferData offerData) {
        this.images = images;
        this.offerData = offerData;
        this.id = id;
        this.title = title;

    }

    public OfferData getOfferData() {
        return offerData;
    }

    public void setOfferData(OfferData offerData) {
        this.offerData = offerData;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SearchImages> getImages() {
        return images;
    }

    public void setImages(List<SearchImages> images) {
        this.images = images;
    }


}
