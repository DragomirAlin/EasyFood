package com.easyfood.EasyFoodApplication.Repository;

import com.easyfood.EasyFoodApplication.Models.ERole;
import com.easyfood.EasyFoodApplication.Models.Role;
import com.easyfood.EasyFoodApplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
    void deleteRoleByName(ERole name, String username);
}
