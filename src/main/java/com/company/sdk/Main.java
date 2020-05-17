package com.company.sdk;

import com.company.sdk.messages.Message;

public class Main {
    public static void main(String[] args) {
        Connector connector = Jlmq.connector("ws://localhost:8080/jlmq/");
        Consumer consumer = connector.consumer("1", Message.class, (message) -> System.out.println(message.getText()));
        Producer producer = connector.producer("1");

        producer.send(new Message("Test"));
    }
}
