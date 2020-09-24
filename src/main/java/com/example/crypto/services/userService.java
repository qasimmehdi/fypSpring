package com.example.crypto.services;

import com.example.crypto.DAO.UserDao;
import com.example.crypto.Model.UserModel;
import com.example.crypto.mongorepo.connection;
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
    public UserModel getByCredentials(String user, String pass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user).and("password").is(pass));

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
}
