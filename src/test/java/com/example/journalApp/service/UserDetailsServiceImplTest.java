package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
public class UserDetailsServiceImplTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("xyzxyz").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
