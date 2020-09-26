package com.example.crypto.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;


@Document
public class UserModel {

    @Id
    private String recId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String code;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(String f, String l, String e, String u, String c) {
        firstName = f;
        lastName = l;
        email = e;
        userName = u;
        code = c;
    }

    public UserModel() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getId() {
        return recId;
    }

    public void setId(String id) {
        this.recId = id;
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
        return "Person [Id=" + recId + ", firstName=" + firstName + ", lastName=" + lastName + ",email" + email + ",userName" + userName + "]";
    }
}
