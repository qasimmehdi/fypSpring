package com.example.crypto.Controllers;

import java.util.Objects;

import com.example.crypto.JWT.JwtTokenUtil;
import com.example.crypto.Model.*;
import com.example.crypto.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private userService us;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername() == null ?
                        authenticationRequest.getEmail() :
                        authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        if(us.getByCredentials(authenticationRequest.getUsername(),authenticationRequest.getPassword(),authenticationRequest.getEmail()) == null){
            return new ResponseEntity<ErrorModel>(new ErrorModel("User Not Found"), HttpStatus.UNAUTHORIZED);
        }
        else{
            return ResponseEntity.ok(new JwtResponse(token));
        }
    }

}
