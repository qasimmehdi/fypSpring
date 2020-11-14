package com.example.crypto.services;


import com.example.crypto.Model.AlertModel;
import com.example.crypto.Model.FCM.NotificationModel;
import com.example.crypto.Model.Post;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class fcmService {

    @Value("${fcm.secret}")
    private String secret;

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    private final RestTemplate restTemplate;

    public fcmService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }



    public boolean SubscribeToTopic(String token,String Topic){
        String url = "https://iid.googleapis.com/iid/v1/"+token+"/rel/topics/"+Topic;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer "+secret);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Post> re = this.restTemplate.postForEntity(url,request, Post.class);

        if(re.getStatusCode() != HttpStatus.BAD_REQUEST){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean unSubscribeTopic(String topic, String Token){
        String url = "https://iid.googleapis.com/iid/v1:batchRemove";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer "+secret);

        Map<String, Object> map = new HashMap<>();
        map.put("to","/topics/"+topic);
        map.put("registration_tokens",new String[]{Token});


        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map,headers);

        ResponseEntity<Post> re = this.restTemplate.postForEntity(url,request, Post.class);

        if(re.getStatusCode() != HttpStatus.BAD_REQUEST){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean SendNotificationToTopic(NotificationModel nm) throws IOException {
        String url = "https://fcm.googleapis.com/v1/projects/cryptassist-fbe9f/messages:send";
        FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir")+"/"+firebaseConfigPath);
        GoogleCredential googleCred = GoogleCredential.fromStream(serviceAccount);
        GoogleCredential scoped = googleCred.createScoped(
                Arrays.asList(
                        "https://www.googleapis.com/auth/firebase.database",
                        "https://www.googleapis.com/auth/userinfo.email",
                        "https://www.googleapis.com/auth/firebase", // Add this scope
                        "https://www.googleapis.com/auth/firebase.messaging",
                        "https://www.googleapis.com/auth/identitytoolkit"
                )
        );
        scoped.refreshToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer "+scoped.getAccessToken());

        HttpEntity<NotificationModel> entity = new HttpEntity<>(nm,headers);

        ResponseEntity<Post> re = this.restTemplate.postForEntity(url, entity, Post.class);
        if(re.getStatusCode() != HttpStatus.BAD_REQUEST){
            return true;
        }
        else{
            return false;
        }
    }

}
