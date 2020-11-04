package com.easyfood.profile.controller;

import com.easyfood.product.service.ProductService;
import com.easyfood.profile.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping(path = "/show")
    public String show() throws JsonProcessingException {
        return profileService.getObjectMapper();
    }

    @PostMapping("/insert")
    public void insert(){
        profileService.saveProfile();
    }
}
