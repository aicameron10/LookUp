package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;


public class Offers {
    @SerializedName("id")
    private String id;
    @SerializedName("condition")
    private String condition;
    @SerializedName("price")
    private double price;
    @SerializedName("listPrice")
    private double listPrice;
    @SerializedName("availabilityDescription")
    private String availabilityDescription;


    public Offers(String id, String condition, Double price, Double listPrice, String availabilityDescription) {
        this.id = id;
        this.condition = condition;
        this.price = price;
        this.listPrice = listPrice;
        this.availabilityDescription = availabilityDescription;

    }

    public String getAvailabilityDescription() {
        return availabilityDescription;
    }

    public void setAvailabilityDescription(String availabilityDescription) {
        this.availabilityDescription = availabilityDescription;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }
}
