package com.easyfood.profile.service;

import com.easyfood.profile.model.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    Profile profile = new Profile("test",12,120,88.2,"man");

    @Autowired
    ObjectMapper objectMapper;

    public String getObjectMapper() throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(profile);
    }
}
