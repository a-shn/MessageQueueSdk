package com.company.sdk;

import java.net.URI;

public class Jlmq {
    public static Connector connector(String uri) {
        return new Connector(URI.create(uri));
    }
}
