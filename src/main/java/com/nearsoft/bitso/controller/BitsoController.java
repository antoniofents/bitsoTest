package com.nearsoft.bitso.controller;

import com.nearsoft.bitso.client.DiffOrdersWebSocketClient;
import com.nearsoft.bitso.client.OrderBookRestClient;
import com.nearsoft.bitso.client.TradesRestClient;
import com.nearsoft.bitso.model.OrderBook;
import com.nearsoft.bitso.model.OrderMessage;
import com.nearsoft.bitso.model.Trade;
import com.nearsoft.bitso.view.BitsoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BitsoController {


    BitsoView view;
    Queue<OrderMessage> messages;
    List<Trade> trades;
    TradesRestClient tradesRestClient;
    OrderBookRestClient orderBookRestClient;
    DiffOrdersWebSocketClient diffOrdersWebSocketClient;
    OrderBook orderBook;


    public BitsoController(BitsoView view) {
        this.view = view;
        tradesRestClient = new TradesRestClient();
        this.orderBook = OrderBook.getInstance();
        orderBook.setAskAdded(view::updateAsks);
        orderBook.setBidAdded(view::updateBids);
        startDiffOrdersSocket();
        initTradesObserver();
    }


    private void startDiffOrdersSocket() {
        orderBookRestClient = new OrderBookRestClient();
        orderBookRestClient.getOrderBook(orderBook.getBids(), orderBook.getAsks());
        messages = new ConcurrentLinkedQueue<OrderMessage>();
        new DiffOrdersWebSocketClient(m ->{ messages.add(m);checkOrderUpdates();}).connectToWebSocket();
    }


    private void initTradesObserver() {
        trades = new ArrayList<>();
        //add timer
        //add timer for trades
        tradesRestClient.getLastTrades(this::processTrades);

    }

    private void processTrades(List trades) {
        //check if trades are contained and update
        view.updateTrades(trades);
    }


    private void checkOrderUpdates() {

        OrderMessage message;
        while ((message = messages.poll()) != null) {
            System.out.println("MESSAGE RECEIVER " + message.getSequenced() + " current" + orderBook.getSequence());
            if (message.getSequenced()>  orderBook.getSequence()) {
                processMessage(message);
            }

        }
    }

    private void processMessage(OrderMessage message) {
        System.out.println("PROCESSING MESSAGE " + message.getSequenced());
        orderBook.setSequence(message.getSequenced());
        if (message.getIndicator() == 0) {
            orderBook.evaluateBid(message);
        } else {
            orderBook.evaluateAsks(message);
        }
    }

}
