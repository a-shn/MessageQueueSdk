package com.company.sdk.messages;

public class SubscribeMessage {
    private final String queueName;

    public SubscribeMessage(String queueName) {
        this.queueName = queueName;
    }

    public String getCommand() {
        return "subscribe";
    }

    public String getQueue() {
        return queueName;
    }
}