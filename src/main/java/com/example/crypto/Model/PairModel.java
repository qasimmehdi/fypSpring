package com.example.crypto.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PairModel {
    private String pair;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }
}
