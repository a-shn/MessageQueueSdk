package com.company.sdk.messages;


public class ProducerMessage {
    private String queueName;
    private Object body;

    public ProducerMessage(String queueName, Object body) {
        this.queueName = queueName;
        this.body = body;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
