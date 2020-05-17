package com.company.sdk;

import com.company.sdk.handlers.MessageHandler;
import com.company.sdk.messages.SubscribeMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;

public class Connector {
    private final Endpoint endpoint;
    private Consumer consumer;
    private final ObjectMapper objectMapper;

    public Connector(URI uri) {
        endpoint = new Endpoint(this);
        objectMapper = new ObjectMapper();
        try {
            ContainerProvider.getWebSocketContainer().connectToServer(endpoint, uri);
        } catch (DeploymentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(Object message) {
        try {
            endpoint.send(objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public <T> Consumer consumer(String queueName, Class<T> classClass, MessageHandler<T> handler) {
        consumer = new Consumer(this, classClass, handler);
        send(new SubscribeMessage(queueName));
        return consumer;
    }

    public Producer producer(String queueName) {
        return new Producer(this, queueName);
    }
}
