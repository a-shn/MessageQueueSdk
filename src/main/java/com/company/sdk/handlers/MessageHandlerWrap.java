package com.company.sdk.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageHandlerWrap<T> implements MessageHandler<JsonNode>{
    private final Class<? extends T> messageClass;
    private final MessageHandler<T> handler;
    private final ObjectMapper objectMapper;

    public MessageHandlerWrap(Class<? extends T> messageClass, MessageHandler<T> handler) {
        this.messageClass = messageClass;
        this.handler = handler;
        objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(JsonNode body) {
        handler.handle(objectMapper.convertValue(body, messageClass));
    }
}
