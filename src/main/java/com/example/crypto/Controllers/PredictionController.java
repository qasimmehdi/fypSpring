package com.example.crypto.Controllers;

import com.example.crypto.ML.LinearRegression;
import com.example.crypto.Model.CoinHistory;
import com.example.crypto.services.Polenix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/prediction")
public class PredictionController {

    @Autowired
    private Polenix p;

    @GetMapping("/{coin}/{days}")
    public ResponseEntity<?> get(@PathVariable("coin") String coin,@PathVariable("days") int days){

        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date date = new Date(System.currentTimeMillis() - (5 * DAY_IN_MS));
        long from = date.getTime() / 1000;

        //data collection
        List<CoinHistory> lst = p.getCoinHistory(coin,from).collectList().block();

        double[] x = new double[lst.size()];
        double[] y = new double[lst.size()];
        int i = 0;

        for(CoinHistory ch:lst){
            x[i] = i+1;
            y[i] = ch.getHigh();
            i++;
        }
        LinearRegression o = new LinearRegression(x,y);

        List<Map<String,Object>> response = new ArrayList();

        for(int j = 0;j < days;j++){
            Map<String,Object> rsp = new HashMap();
            Date tstamp = new Date(System.currentTimeMillis() - ((j+1) * DAY_IN_MS));
            rsp.put("timestamp",tstamp.getTime() / 1000);
            rsp.put("prediction",o.predict(i));
            response.add(rsp);
            i++;
        }


        return ResponseEntity.status(200).body(response);
    }
}
