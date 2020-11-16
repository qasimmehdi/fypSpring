package com.example.crypto.Cron;


import com.example.crypto.JWT.JwtTokenUtil;
import com.example.crypto.Model.FCM.Body;
import com.example.crypto.Model.FCM.Message;
import com.example.crypto.Model.FCM.NotificationModel;
import com.example.crypto.services.alertService;
import com.example.crypto.services.fcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotificationCron {

    @Autowired
    private alertService as;




    @Scheduled(cron = "0 * * * * *")
    public void sendnotificationsJob() {
        this.as.sendNotifcations();
    }
}
