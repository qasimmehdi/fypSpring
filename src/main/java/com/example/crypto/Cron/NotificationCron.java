package com.example.crypto.Cron;


import com.example.crypto.JWT.JwtTokenUtil;
import com.example.crypto.Model.FCM.Body;
import com.example.crypto.Model.FCM.Message;
import com.example.crypto.Model.FCM.NotificationModel;
import com.example.crypto.services.alertService;
import com.example.crypto.services.fcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotificationCron {

    @Autowired
    private alertService as;


    @Autowired
    private fcmService fs;

    @Scheduled(cron = "0 */12 * * * *")
    public void sendnotifications() throws Exception {
        NotificationModel nm = new NotificationModel();
        Message m = new Message();
        Body d = new Body();

        as.getAllDistinct().forEach(x -> {
            d.setTitle(x.getCurrencyName());
            d.setBody("Upto $5");
            m.setNotification(d);
            m.setTopic("cryptassist-"+x.getCurrencyName());
            nm.setMessage(m);
            try {
                fs.SendNotificationToTopic(nm);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
