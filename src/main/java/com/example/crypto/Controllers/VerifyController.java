package com.example.crypto.Controllers;

import com.example.crypto.Model.UserModel;
import com.example.crypto.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/verifyEmail")
public class VerifyController {

    @Autowired
    private userService us;

    @GetMapping("{id}")
    public String verify(@PathVariable String id){
        UserModel a = us.getByid(id);
        a.setEmailVerified(true);
        us.saveUSer(a);
        return "Email Verified Successfully";
    }
}
