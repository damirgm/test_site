package com.server.demo;

import java.sql.Timestamp;

public class Message {

    private Long id;
    private String text;
    private Timestamp timestamp;

    public Message(Long id, String text, Timestamp timestamp) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
