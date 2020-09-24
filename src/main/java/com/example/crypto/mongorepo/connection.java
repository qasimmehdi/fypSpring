package com.example.crypto.mongorepo;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class connection {

    public static MongoOperations mongoOps;


    public static MongoOperations get(){
        if(mongoOps == null){
            mongoOps = new MongoTemplate(MongoClients.create("mongodb+srv://Fyp:fyp12345@cluster0.3v8b6.mongodb.net/fyb?retryWrites=true&w=majority"), "fyp");
        }
        return mongoOps;
    }

}
