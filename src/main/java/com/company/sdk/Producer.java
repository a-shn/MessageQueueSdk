package com.company.sdk;

import com.company.sdk.messages.ProducerMessage;

public class Producer {
    private final Connector connector;
    private final String queueName;

    public Producer(Connector connector, String queueName) {
        this.connector = connector;
        this.queueName = queueName;
    }

    public void send(Object message) {
        connector.send(new ProducerMessage(queueName, message));
    }
}
