package com.easyfood.Service;

import com.easyfood.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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

    public List<Collection> viewAllRoles(){
        return roleRepository.showAllRole();
    }


}

