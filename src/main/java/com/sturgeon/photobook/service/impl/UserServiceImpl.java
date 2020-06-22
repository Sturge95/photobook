package com.sturgeon.photobook.service.impl;

import com.sturgeon.photobook.bo.User;
import com.sturgeon.photobook.bo.UserDetailsImpl;
import com.sturgeon.photobook.repository.UserRepository;
import com.sturgeon.photobook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserDetailsImpl.build(user);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean userExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean userExistsByUsername(String name) {
        return userRepository.existsByUsername(name);
    }
}
