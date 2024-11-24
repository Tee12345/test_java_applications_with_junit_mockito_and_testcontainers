package com.babatunde.estore.service;

import com.babatunde.estore.model.User;

public interface UserService {
     User createUser(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String repeatPassword);

}
