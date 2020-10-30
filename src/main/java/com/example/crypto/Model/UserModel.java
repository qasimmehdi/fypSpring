package com.example.crypto.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Document
public class UserModel {

    @Id
    private String id;


    private String userId;

    private String firstName;

    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    private String code;
    private Boolean isEmailVerified;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(String f, String l, String e, String u, String c, boolean v) {
        firstName = f;
        lastName = l;
        email = e;
        userName = u;
        code = c;
        isEmailVerified = v;
    }

    public UserModel() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Person [Id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ",email" + email + ",userName" + userName + "]";
    }
}
