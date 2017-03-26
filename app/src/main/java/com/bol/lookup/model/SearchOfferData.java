package com.bol.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrew Cameron
 */

public class SearchOfferData {
    @SerializedName("bolCom")
    private int bolCom;
    @SerializedName("nonProfessionalSellers")
    private int nonProfessionalSellers;
    @SerializedName("professionalSellers")
    private int professionalSellers;
    @SerializedName("offers")
    private List<SearchOffers> offers;


    public SearchOfferData(Integer bolCom, Integer nonProfessionalSellers, Integer professionalSellers,List<SearchOffers> offers) {
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

    public List<SearchOffers> getOffers() {
        return offers;
    }

    public void setOffers(List<SearchOffers> offers) {
        this.offers = offers;
    }

}
