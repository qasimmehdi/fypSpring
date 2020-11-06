package com.example.crypto.Model.FCM;

public class Message {
    private String topic;
    private Body notification;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Body getNotification() {
        return notification;
    }

    public void setNotification(Body notification) {
        this.notification = notification;
    }


}
