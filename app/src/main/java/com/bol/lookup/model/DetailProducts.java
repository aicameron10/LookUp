package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DetailProducts {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("summary")
    private String summary;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("media")
    private List<Media> media;
    @SerializedName("offerData")
    private OfferData offerData;


    public DetailProducts(String id, String title, String summary, String shortDescription, List<Media> media, OfferData offerData) {
        this.media = media;
        this.summary = summary;
        this.shortDescription = shortDescription;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }


}
