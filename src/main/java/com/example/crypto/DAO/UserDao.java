package com.example.crypto.DAO;

import com.example.crypto.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserModel> getAll();

    UserModel getByCredentials(String user,String pass);

    boolean DeleteUserById(String id);

    Optional<UserModel> getUserById(String id);

    UserModel saveUSer(UserModel a);

    boolean updateUser(String id,UserModel a);

}
