package com.example.crypto.mongorepo;

import com.mongodb.DBObject;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("check")
public interface MongoRepo extends MongoRepository<UserModel, String> {


}
