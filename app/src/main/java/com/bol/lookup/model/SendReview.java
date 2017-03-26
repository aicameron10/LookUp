package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;


public class SendReview {

    @SerializedName("productId")
    private String productId;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("title")
    private String title;
    @SerializedName("review")
    private String review;
    @SerializedName("rating")
    private int rating;
    @SerializedName("email")
    private String email;


    public SendReview(String productId, String fullname, String title, String review,
                      Integer rating, String email) {
        this.fullname = fullname;
        this.review = review;
        this.rating = rating;
        this.email = email;
        this.productId = productId;
        this.title = title;

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
