package com.easyfood.user.service;


import com.easyfood.user.persistence.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    Optional<User> loadUserFromEmail(String email);
    public Optional<User> findUserByEmail(String email);
    public Optional<User> findByTokenReset(String resetToken);
    public void save(User user);

}
