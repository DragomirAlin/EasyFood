package com.easyfood.user.service;

import com.easyfood.user.persistence.User;
import com.easyfood.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> loadUserFromEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }


}
