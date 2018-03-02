package com.nearsoft.bitso.model;

import com.nearsoft.bitso.model.Trade;

import java.util.List;

public class ResponseWrapper <T> {

    private String success;
    private List<T> payload;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }
}
