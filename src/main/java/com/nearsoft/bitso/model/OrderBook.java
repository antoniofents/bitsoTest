package com.nearsoft.bitso.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class OrderBook {

    private static int CONFIGURABLE_LIMIT=20;
    private static OrderBook orderBookInstance;
    private  List<Order> orderMessages= new ArrayList<>();
    private Consumer <List <Order>> bidAdded;
    private Consumer  <List <Order>>askAdded;

    private long sequence=1L;

    private  List<Order> bids= new ArrayList<>();
    private  List<Order> asks=new ArrayList<>();

    private  OrderBook(){ }

    public static OrderBook getInstance(){
        if(orderBookInstance==null){
            orderBookInstance= new OrderBook();
        }
        return orderBookInstance;
    }


    public void evaluateBid(OrderMessage message){

        Optional<Order> bestBid= getBids().stream().collect(Collectors.minBy(Comparator.comparing(Order::getPrice)));
        bestBid.ifPresent( order->{
            if(message.getAmount()!=null&& Double.parseDouble(order.getPrice())<Double.parseDouble(message.getAmount())){
                order.setPrice(message.getAmount());
                bidAdded.accept(getBestBids(CONFIGURABLE_LIMIT));
            }
        });
    }

    public void evaluateAsks(OrderMessage message){
        Optional<Order> bestBid= getAsks().stream().collect(Collectors.minBy(Comparator.comparing(Order::getPrice)));
        bestBid.ifPresent( order->{
            if(message.getAmount()!=null && Double.parseDouble(order.getPrice())<Double.parseDouble(message.getAmount())){
                order.setPrice(message.getAmount());
                askAdded.accept(getBestAsks(CONFIGURABLE_LIMIT));
            }
        });
    }


    public List <Order> getBestBids( int limit){
        return getBids().stream().sorted(Comparator.comparing(Order::getPrice)).limit(limit).collect(Collectors.toList());
    }



    public  List <Order>  getBestAsks(int limit){
        return getAsks().stream().sorted(Comparator.comparing(Order::getPrice)).limit(limit).collect(Collectors.toList());
    }



    public void setBidAdded(Consumer  <List <Order>> bidAdded) {
        this.bidAdded = bidAdded;
    }

    public void setAskAdded(Consumer <List <Order>> askAdded) {
        this.askAdded = askAdded;
    }

    public List<Order> getBids() {
        return bids;
    }

    public List<Order> getAsks() {
        return asks;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }
}
