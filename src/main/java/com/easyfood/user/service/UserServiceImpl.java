package com.easyfood.user.service;

import com.easyfood.user.persistence.User;
import com.easyfood.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> loadUserFromEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public Optional findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional findByTokenReset(String resetToken) {
        return userRepository.findByTokenReset(resetToken);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }



}
