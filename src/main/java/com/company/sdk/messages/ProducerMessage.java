package com.company.sdk.messages;


public class ProducerMessage {
    private String queue;
    private Object body;

    public ProducerMessage(String queue, Object body) {
        this.queue = queue;
        this.body = body;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueueName(String queue) {
        this.queue = queue;
    }

    public String getCommand() {
        return "send";
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
