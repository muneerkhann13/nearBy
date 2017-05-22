/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.json.response;

import com.cd.nearby.model.Merchant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asce
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    @JsonProperty("code")
    int code;
    @JsonProperty("description")
    String desc;
    @JsonProperty("rating")
    double rating;
    
    @JsonProperty("user_count")
    long userCount;

    @JsonProperty("merchants")
    private ArrayList<Merchants> merchants;

    @JsonProperty("offer")
    private ArrayList<Offer> offers;

    public Response() {
    }

    @JsonIgnore
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @JsonIgnore
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonIgnore
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @JsonIgnore
    public List<Merchants> getMerchants() {
        return merchants;
    }

    public void setMerchants(ArrayList<Merchants> merchants) {
        this.merchants = merchants;
    }

    @JsonIgnore
    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    
    
}

