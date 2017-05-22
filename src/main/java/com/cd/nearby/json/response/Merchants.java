/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.json.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Asce
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Merchants {

    @JsonProperty("mdn")
    String merchantMdn;
    @JsonProperty("lat")
    double lat;
    @JsonProperty("lng")
    double lng;
    @JsonProperty("shop_name")
    String shopName;
    @JsonProperty("merchant_name")
    String MerchantName;
    @JsonProperty("address")
    String address;
    @JsonProperty("email")
    String email;
    @JsonProperty("category")
    String category;
    @JsonProperty("rating")
    double rating;

    public Merchants() {
    }

    @JsonIgnore
    public String getMerchantMdn() {
        return merchantMdn;
    }

    public void setMerchantMdn(String merchantMdn) {
        this.merchantMdn = merchantMdn;
    }

    @JsonIgnore
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @JsonIgnore
    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @JsonIgnore
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @JsonIgnore
    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String MerchantName) {
        this.MerchantName = MerchantName;
    }

    @JsonIgnore
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JsonIgnore
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
