package com.nearsoft.bitso.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trade {

    private String book;
    @SerializedName(value = "created_at")
    private String createdAt;
    private String amount;
    @SerializedName(value = "marker_side")
    private String makerSide;
    private String sell;
    private String price;
    private String tid;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMakerSide() {
        return makerSide;
    }

    public void setMakerSide(String makerSide) {
        this.makerSide = makerSide;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

}
