package com.easyfood.EasyFoodApplication.Service;

import com.easyfood.EasyFoodApplication.Models.ERole;
import com.easyfood.EasyFoodApplication.Repository.RoleRepository;
import com.easyfood.EasyFoodApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void deleteRole(long user_id, long role_id) {
        roleRepository.deleteRoleBytrews(user_id, role_id);
    }

    public void addRoleToUser(long user_id, long role_id) {
        roleRepository.addRoleToUser(user_id, role_id);
    }
}

class UserRole{

}