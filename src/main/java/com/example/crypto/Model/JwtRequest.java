package com.example.crypto.Model;

import java.io.Serializable;
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String userName;
    private String password;
    private String email;
    //need default constructor for JSON Parsing
    public JwtRequest()
    {
    }
    public JwtRequest(String userName, String password,String email) {
        this.setUsername(userName);
        this.setPassword(password);
        this.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.userName;
    }
    public void setUsername(String username) {
        this.userName = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
