package com.example.crypto.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Document
public class UserModel {

    @Id
    private String recId;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(String f, String l, String e, String u) {
        firstName = f;
        lastName = l;
        email = e;
        userName = u;
    }


    public UserModel() {

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

    public void setLastName(String lastName) {
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
