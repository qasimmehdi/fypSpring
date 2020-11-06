package com.example.crypto.Controllers;

import com.example.crypto.JWT.JwtTokenUtil;
import com.example.crypto.Model.AlertModel;
import com.example.crypto.Model.FCM.Body;
import com.example.crypto.Model.FCM.Message;
import com.example.crypto.Model.FCM.NotificationModel;
import com.example.crypto.services.alertService;
import com.example.crypto.services.fcmService;
import com.example.crypto.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private alertService as;

    @Autowired
    private JwtTokenUtil jwt;

    @Autowired
    private fcmService fs;

    @PostMapping
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token,@Valid @RequestBody AlertModel am) throws IOException {
        String id = this.jwt.getIdFromToken(token.split(" ")[1]);
        am.setUserId(id);
        boolean subscribed = this.fs.SubscribeToTopic(am.getToken(),"cryptassist-" + am.getCurrencyName());
        if(this.as.Existed(id,am.getCurrencyName()).stream().count() > 0){
            return new ResponseEntity<String>("Already Subscribed", HttpStatus.resolve(409));
        }
       else if(subscribed){
            return new ResponseEntity<AlertModel>(this.as.save(am), HttpStatus.resolve(200));
        }
        else{
            return new ResponseEntity<String>("Invalid Token", HttpStatus.resolve(400));
        }

    }

    @GetMapping
    public List<AlertModel> getall(@RequestHeader("Authorization") String token){
        String id = this.jwt.getIdFromToken(token.split(" ")[1]);
        return this.as.getAll(id);
    }
}
