package com.easyfood.profile.service;

import com.easyfood.profile.model.Profile;
import com.easyfood.profile.persistence.ProfileUser;
import com.easyfood.profile.repository.ProfileRepository;
import com.easyfood.security.service.IAuthenticationFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    ProfileUser profile = new ProfileUser("test",12,120,88.2,"man");

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private ObjectMapper objectMapper;

    public String getObjectMapper() throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(profile);
    }

    public void saveProfile(){
        profileRepository.save(profile);
    }


    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }



}
