package com.nearsoft.bitso.model;

public class Order {
    private String amount;
    private String price;



    @Override
    public String toString() {
        return "value " + getPrice() +",";
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
