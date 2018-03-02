package com.nearsoft.bitso.model;

import com.google.gson.annotations.SerializedName;

public class OrderMessage {


    private long  sequenced;
    @SerializedName(value = "o")
    private String oid;

    @SerializedName(value = "d")
    private String timestamp;

    @SerializedName(value = "r")
    private String rate;

    @SerializedName(value = "a")
    private String amount;

    @SerializedName(value = "v")
    private String value;

    @SerializedName(value = "t")
    private int indicator;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public int getIndicator() {
        return indicator;
    }

    public void setIndicator(int indicator) {
        this.indicator = indicator;
    }

    public long getSequenced() {
        return sequenced;
    }

    public void setSequenced(long sequenced) {
        this.sequenced = sequenced;
    }
}
