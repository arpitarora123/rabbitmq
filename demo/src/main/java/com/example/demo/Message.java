package com.example.demo;

import java.io.Serializable;

public class Message implements Serializable {

    String body;

    Message(String body) {
        this.body = body;
    }
}
