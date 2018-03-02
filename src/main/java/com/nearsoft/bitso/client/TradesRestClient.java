package com.nearsoft.bitso.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nearsoft.bitso.model.ResponseWrapper;
import com.nearsoft.bitso.model.Trade;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

public class TradesRestClient {

    public void getLastTrades(Consumer<List <Trade>> update) {
        try {
            URL url = null;
            url = new URL("https://api.bitso.com/v3/trades/?book=btc_mxn");
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(url.openStream());
            ResponseWrapper response= new Gson().fromJson(reader, new TypeToken<ResponseWrapper<Trade>>(){}.getType());
            update.accept(response.getPayload());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
