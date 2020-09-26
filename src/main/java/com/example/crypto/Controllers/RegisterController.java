package com.example.crypto.Controllers;

import com.example.crypto.Model.UserModel;
import com.example.crypto.mongorepo.connection;
import com.example.crypto.services.userService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {


    private final userService us;

    @Autowired
    public RegisterController(@Qualifier("userService") userService r) {
        this.us = r;
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserModel a){

        if(us.isExist(a)){
            return new ResponseEntity<String>("Already Existed", HttpStatus.resolve(409));
        }
        else{
            return new ResponseEntity<UserModel>(this.us.saveUSer(a), HttpStatus.resolve(200));
        }
    }
}
