package com.example.crypto.services;

import com.example.crypto.Model.CMC.CoinInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class cmcService {

    @Value("${cpc.apikey}")
    private String apiKey;

    private final WebClient client;

    public cmcService(WebClient.Builder webClientBuilder){
        this.client = webClientBuilder
                .baseUrl("https://pro-api.coinmarketcap.com/v1/cryptocurrency")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Flux<CoinInfo> getDetails(int limit){
       return this.client.get()
               .uri(uriBuilder ->
                   uriBuilder.path("/listings/latest")
                           .queryParam("limit",limit)
                           .build()
               )
               .header("X-CMC_PRO_API_KEY",apiKey)
               .retrieve()
               .bodyToFlux(CoinInfo.class);
    }


}
