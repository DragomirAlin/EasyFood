//package com.easyfood.user.controller;
//
//import com.easyfood.role.repository.RoleRepository;
//import com.easyfood.security.jwt.JwtUtils;
//import com.easyfood.user.dto.User;
//import com.easyfood.user.repository.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.result.StatusResultMatchers;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(AuthController.class)
//public class AuthControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//
//    @MockBean
//    AuthenticationManager authenticationManager;
//
//    @MockBean
//    UserRepository userRepository;
//
//    @MockBean
//    RoleRepository roleRepository;
//
//    @MockBean
//    PasswordEncoder encoder;
//
//    @MockBean
//    JwtUtils jwtUtils;
//
//    @Test
//    public void authenticateUser() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .post("/signin")
//                .content(asJsonString(new User("userfortesting", "usertest@test.ro", "testing123")))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//    }
//
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//}
