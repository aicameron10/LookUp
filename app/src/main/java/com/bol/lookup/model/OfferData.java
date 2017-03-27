package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class OfferData {
    @SerializedName("bolCom")
    private int bolCom;
    @SerializedName("nonProfessionalSellers")
    private int nonProfessionalSellers;
    @SerializedName("professionalSellers")
    private int professionalSellers;
    @SerializedName("offers")
    private List<Offers> offers;


    public OfferData(Integer bolCom, Integer nonProfessionalSellers, Integer professionalSellers, List<Offers> offers) {
        this.bolCom = bolCom;
        this.nonProfessionalSellers = nonProfessionalSellers;
        this.professionalSellers = professionalSellers;
        this.offers = offers;

    }

    public int getBolCom() {
        return bolCom;
    }

    public void setBolCom(int bolCom) {
        this.bolCom = bolCom;
    }

    public int getNonProfessionalSellers() {
        return nonProfessionalSellers;
    }

    public void setNonProfessionalSellers(int nonProfessionalSellers) {
        this.nonProfessionalSellers = nonProfessionalSellers;
    }

    public int getProfessionalSellers() {
        return professionalSellers;
    }

    public void setProfessionalSellers(int professionalSellers) {
        this.professionalSellers = professionalSellers;
    }

    public List<Offers> getOffers() {
        return offers;
    }

    public void setOffers(List<Offers> offers) {
        this.offers = offers;
    }

}
