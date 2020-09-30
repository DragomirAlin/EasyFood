package com.easyfood.user.service;

import com.easyfood.user.persistence.User;
import com.easyfood.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> loadUserFromEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    public Optional findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    @Override
//    public Optional findUserByResetToken(String resetToken) {
//        return userRepository.findByResetToken(resetToken);
//    }

    public void save(User user) {
        userRepository.save(user);
    }



}
