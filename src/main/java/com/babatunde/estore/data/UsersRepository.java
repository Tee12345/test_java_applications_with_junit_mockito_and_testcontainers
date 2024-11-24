package com.babatunde.estore.data;

import com.babatunde.estore.model.User;

public interface UsersRepository {

    boolean save(User user);
}
