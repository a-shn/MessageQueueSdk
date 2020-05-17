package com.company.sdk.messages;

public class ConsumerMessage {
    private final String command;
    private final String message;

    public static ConsumerMessage acknowledge(String message) {
        return new ConsumerMessage("acknowledge", message);
    }

    public static ConsumerMessage malformed(String message) {
        return new ConsumerMessage("malformed", message);
    }

    public static ConsumerMessage completed(String message) {
        return new ConsumerMessage("completed", message);
    }

    private ConsumerMessage(String command, String message) {
        this.command = command;
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }
}