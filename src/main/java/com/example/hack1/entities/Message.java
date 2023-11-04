package com.example.hack1.entities;



public class Message {
    public String content;
    public String type;

    public Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
