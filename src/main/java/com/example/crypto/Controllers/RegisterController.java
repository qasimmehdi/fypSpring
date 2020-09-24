package com.example.crypto.Controllers;

import com.example.crypto.Model.UserModel;
import com.example.crypto.mongorepo.connection;
import com.example.crypto.services.userService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public UserModel save(@RequestBody UserModel us){
        return this.us.saveUSer(us);
    }
}
