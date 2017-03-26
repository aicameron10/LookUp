package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Review {
    @SerializedName("id")
    private int id;
    @SerializedName("created")
    private String created;
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


    public Review(Integer id, String created, String fullname, String title, String review,
                  Integer rating, String email) {
        this.created = created;
        this.fullname = fullname;
        this.review = review;
        this.rating = rating;
        this.email = email;
        this.id = id;
        this.title = title;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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
