package com.example.crypto.DAO;

import com.example.crypto.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserModel> getAll();

    UserModel getByCredentials(String user,String pass,String email);

    boolean DeleteUserById(String id);

    Optional<UserModel> getUserById(String id);

    UserModel saveUSer(UserModel a);

    boolean updateUser(String id,UserModel a);

    UserModel addEmail(String user,String email);

    UserModel isEmailExist(String email);

    void setCode(String email,String code);

    UserModel checkCode(String code);
}
