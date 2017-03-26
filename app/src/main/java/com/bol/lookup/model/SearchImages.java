package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Andrew Cameron
 */

public class SearchImages {
    @SerializedName("type")
    private String type;
    @SerializedName("key")
    private String key;
    @SerializedName("url")
    private String url;


    public SearchImages(String type, String key, String url) {
        this.type = type;
        this.key = key;
        this.url = url;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
