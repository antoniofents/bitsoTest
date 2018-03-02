package com.nearsoft.bitso.client;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nearsoft.bitso.model.OrderMessage;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.function.Consumer;
import java.util.logging.Logger;

@ClientEndpoint
public class DiffOrdersWebSocketClient {

    Consumer onMessageAction;

    public DiffOrdersWebSocketClient(Consumer<OrderMessage> consumer){
        this.onMessageAction=consumer;
    }

    private static final Logger LOGGER = Logger.getLogger(DiffOrdersWebSocketClient.class.getName());
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("session started");
    }

    @OnMessage
    public void onMessage(String input) {

        JsonObject response = new Gson().fromJson(input, JsonObject.class)  ;
        JsonArray payload = response.getAsJsonArray("payload");
        if(payload!=null){

            OrderMessage order = new Gson().fromJson(payload.get(0), OrderMessage.class);
            order.setSequenced(response.get("sequence").getAsLong());
            onMessageAction.accept(order);
        }
    }


    @OnClose
    public void onClose() {
        //TODO: add close impls
    }


    public void connectToWebSocket() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            URI uri = URI.create("wss://ws.bitso.com");
            container.connectToServer(this, uri);
            this.session.getAsyncRemote().sendText("{\"action\":\"subscribe\",\"book\":\"btc_mxn\",\"type\":\"diff-orders\"}");

        } catch (DeploymentException | IOException ex) {
            //TODO: impl error message
        }
    }


}