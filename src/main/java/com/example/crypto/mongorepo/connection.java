package com.example.crypto.mongorepo;

import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class connection {

    public static MongoOperations mongoOps;

    public static MongoOperations get(){
        if(mongoOps == null){
            mongoOps = new MongoTemplate(MongoClients.create(), "fyp");
        }
        return mongoOps;
    }
}
