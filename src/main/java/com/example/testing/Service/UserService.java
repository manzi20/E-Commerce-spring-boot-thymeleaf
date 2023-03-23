package com.example.testing.Service;

import com.example.testing.model.Product;
import com.example.testing.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User  saveUsers(User user );



    User getUserById(Integer id);

    User updateUser( User user);

    void deleteUserById(Integer id);

    User getUserByEmail(String email);




}
