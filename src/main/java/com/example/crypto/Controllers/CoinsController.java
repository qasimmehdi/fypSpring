package com.example.crypto.Controllers;

import com.example.crypto.Model.CMC.CoinInfo;
import com.example.crypto.services.cmcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinsController {

    @Autowired
    private cmcService service;

    @GetMapping("get")
    public Flux<CoinInfo> getCoins(@RequestParam(required = false) String limit){

        if(limit == null){
            limit = "10";
        }

        return  service.getDetails(Integer.parseInt(limit));
    }

}
