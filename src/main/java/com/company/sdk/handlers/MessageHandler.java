package com.company.sdk.handlers;

@FunctionalInterface
public interface MessageHandler<T> {
    void handle(T body);
}
