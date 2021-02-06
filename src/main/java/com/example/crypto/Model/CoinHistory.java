package com.example.crypto.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinHistory {

    private long date;

    private double high;

    private double low;

    private double open;

    private double close;
    private double volume;
    private double quoteVolume;
    private double weightedAverage;


}
