package com.nearsoft.bitso.view;

import com.nearsoft.bitso.model.Order;
import com.nearsoft.bitso.model.Trade;

import java.util.List;

public interface BitsoView {


    public void launchApp();

    public void cleanAndRestart();

    public void updateTrades(List<Trade> trades);
    public void updateBids(List<Order> trades);
    public void updateAsks(List<Order> trades);
}
