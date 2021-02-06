package com.example.crypto.services;

import com.example.crypto.Model.CMC.CoinInfo;
import com.example.crypto.Model.CoinHistory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Polenix {

    private final WebClient client;

    public Polenix(WebClient.Builder webClientBuilder){
        this.client = webClientBuilder
                .baseUrl("https://poloniex.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Flux<CoinHistory> getCoinHistory(String Currency, long from){
        return this.client.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/public?command=returnChartData&currencyPair=USDT_"+Currency+"&start="+from+"&end=9999999999&period=86400")
                                .build()
                )
                .retrieve()
                .bodyToFlux(CoinHistory.class);
    }
}
