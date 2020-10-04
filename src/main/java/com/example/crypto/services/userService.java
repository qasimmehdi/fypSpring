package com.example.crypto.services;

import com.example.crypto.DAO.UserDao;
import com.example.crypto.Model.UserModel;
import com.example.crypto.mongorepo.connection;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service("userService")
public class userService implements UserDao {

    private MongoOperations mo = connection.get();

    @Override
    public List<UserModel> getAll() {
        return mo.findAll(UserModel.class);
    }

    @Override
    public UserModel getByCredentials(String user, String pass,String email) {
        if((user == null || email == null) && pass == null){
            return null;
        }

        Criteria cr = new Criteria();
        cr.orOperator(Criteria.where("userName").is(user).and("password").is(pass),
                Criteria.where("email").is(email).and("password").is(pass));
        Query query = new Query();
        query.addCriteria(cr);

        return mo.findOne(query,UserModel.class);
    }

    @Override
    public boolean DeleteUserById(String id) {
        return false;
    }

    @Override
    public Optional<UserModel> getUserById(String id) {
        return Optional.empty();
    }

    @Override
    public UserModel saveUSer(UserModel a) {

        return this.mo.save(a);
    }

    @Override
    public boolean updateUser(String id, UserModel a) {
        return false;
    }

    @Override
    public UserModel addEmail(String user,String email) {
        Query q = new Query();
        q.addCriteria(Criteria.where("userName").is(user));
        UserModel us = this.mo.findOne(q,UserModel.class);
        us.setEmail(email);
        return this.mo.save(us);
    }

    @Override
    public UserModel isEmailExist(String email) {
        Query q = new Query();
        q.addCriteria(Criteria.where("email").is(email));
        return this.mo.findOne(q,UserModel.class);
    }

    @Override
    public void setCode(String email,String code) {
        Query q = new Query();
        q.addCriteria(Criteria.where("email").is(email));
        UserModel a = this.mo.findOne(q,UserModel.class);
        a.setCode(code);
        this.mo.save(a);
    }

    @Override
    public UserModel checkCode(String code) {
        Query q = new Query();
        q.addCriteria(Criteria.where("code").is(code));
        return this.mo.findOne(q,UserModel.class);
    }

    @Override
    public UserModel getByid(String id) {
        Query q = new Query();
        q.addCriteria(Criteria.where("userId").is(id));
        return this.mo.findOne(q,UserModel.class);
    }

    public boolean isExist(UserModel a){
        Query query = new Query();
        Criteria cr = new Criteria();
        cr.orOperator(Criteria.where("userName").is(a.getUserName()),Criteria.where("email").is(a.getEmail()));
        query.addCriteria(cr);
        UserModel um = this.mo.findOne(query,UserModel.class,"userModel");
        return !(um == null);
    }
}