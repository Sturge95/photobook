package com.sturgeon.photobook.service;

import com.sturgeon.photobook.bo.User;

public interface UserService {
    void saveUser(User user);

    boolean userExistByEmail(String email);

    boolean userExistsByUsername(String name);
}
