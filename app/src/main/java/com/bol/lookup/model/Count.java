package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Count {
    @SerializedName("total")
    private int total;
    @SerializedName("onestar")
    private int onestar;
    @SerializedName("twostar")
    private int twostar;
    @SerializedName("threestar")
    private int threestar;
    @SerializedName("fourstar")
    private int fourstar;
    @SerializedName("fivestar")
    private int fivestar;

    public Count(Integer total, Integer onestar, Integer twostar, Integer threestar, Integer fourstar, Integer fivestar) {
        this.total = total;
        this.onestar = onestar;
        this.twostar = twostar;
        this.threestar = threestar;
        this.fourstar = fourstar;
        this.fivestar = fivestar;

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOnestar() {
        return onestar;
    }

    public void setOnestar(int onestar) {
        this.onestar = onestar;
    }

    public int getTwostar() {
        return twostar;
    }

    public void setTwostar(int twostar) {
        this.twostar = twostar;
    }

    public int getThreestar() {
        return threestar;
    }

    public void setThreestar(int threestar) {
        this.threestar = threestar;
    }

    public int getFourstar() {
        return fourstar;
    }

    public void setFourstar(int fourstar) {
        this.fourstar = fourstar;
    }

    public int getFivestar() {
        return fivestar;
    }

    public void setFivestar(int fivestar) {
        this.fivestar = fivestar;
    }

}
