package com.easyfood.Service;

import com.easyfood.Models.User;
import com.easyfood.Repository.UserRepository;
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
