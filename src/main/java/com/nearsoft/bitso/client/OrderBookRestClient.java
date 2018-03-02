package com.nearsoft.bitso.client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nearsoft.bitso.model.Order;
import com.nearsoft.bitso.model.Trade;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

public class OrderBookRestClient {


    public void getOrderBook(List <Order> bids, List <Order> asks ) {
        try {
            URL url = null;
            url = new URL("https://api.bitso.com/v3/order_book/?book=btc_mxn");
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonObject response= new Gson().fromJson(reader, JsonObject.class);
            JsonObject payload = response.getAsJsonObject("payload");


            for(JsonElement bid: payload.getAsJsonArray("bids")){
                bids.add( new Gson().fromJson(bid, Order.class));
            }

            for(JsonElement ask: payload.getAsJsonArray("asks")){
                asks.add( new Gson().fromJson(ask, Order.class));
            }

            System.out.println("asks received" + asks);
            System.out.println("bids received" + bids);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
