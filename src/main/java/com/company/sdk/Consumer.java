package com.company.sdk;

import com.company.sdk.handlers.MessageHandler;
import com.company.sdk.handlers.MessageHandlerWrap;
import com.company.sdk.messages.ConsumerMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Consumer {
    private final Connector connector;
    private MessageHandlerWrap<?> handler;
    private final ObjectMapper objectMapper;

    public <T> Consumer(Connector connector, Class<? extends T> messageClass, MessageHandler<T> handler) {
        this.connector = connector;
        this.handler = new MessageHandlerWrap<T>(messageClass, handler);
        objectMapper = new ObjectMapper();
    }

    void handle(String message) {
        JsonNode root;

        try {
            root = objectMapper.readTree(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Something wrong with JSON.", e);
        }

        String command = root.get("command").asText();
        if (!"receive".equals(command)) {
            throw new RuntimeException("Something wrong with JSON.");
        }

        String token = root.get("message").asText();
        connector.send(ConsumerMessage.acknowledge(token));

        try {
            handler.handle(root.get("body"));
            connector.send(ConsumerMessage.completed(token));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
