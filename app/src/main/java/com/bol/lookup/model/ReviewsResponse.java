package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ReviewsResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("reviews")
    private List<Review> reviews;
    @SerializedName("counts")
    private List<Count> counts;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Count> getCounts() {
        return counts;
    }

    public void setCounts(List<Count> counts) {
        this.counts = counts;
    }

}
