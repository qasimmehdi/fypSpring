package com.example.crypto.Controllers;

import com.example.crypto.mongorepo.MongoRepo;
import com.example.crypto.mongorepo.UserModel;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {



    private final MongoRepo repo;

    @Autowired
    public LoginController(@Qualifier("check") MongoRepo r) {
        this.repo = r;
    }

    @GetMapping
    public List<UserModel> get(){
        System.out.println("test");
        return repo.findAll();
    }

    @PostMapping
    public String post(@RequestBody UserModel user){
        System.out.println(user.getFirstName());
        repo.save(user);
        return "Success";
    }

/*    @GetMapping(path = "{id}")
    public Optional<UserModel> getByid(@PathVariable("id") String id){

        return this.repo.findByID(id);
    }*/
}
