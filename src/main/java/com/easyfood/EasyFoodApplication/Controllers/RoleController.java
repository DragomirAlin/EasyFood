package com.easyfood.EasyFoodApplication.Controllers;


import com.easyfood.EasyFoodApplication.Models.ERole;
import com.easyfood.EasyFoodApplication.Models.User;
import com.easyfood.EasyFoodApplication.Service.RoleService;
import com.easyfood.EasyFoodApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/user/")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @DeleteMapping("/role/delete/{user_id}/{role_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRole(@PathVariable long user_id, @PathVariable long role_id) {
        roleService.deleteRole(user_id, role_id);
    }

    @PostMapping("/role/add/{user_id}/{role_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addRoleToUser(@PathVariable long user_id, @PathVariable long role_id) {
        roleService.addRoleToUser(user_id, role_id);
    }




}
