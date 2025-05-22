package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private UserService userService;
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));

    }


    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "maniram",
            "mani"
    })
    public void testFindByUsername(String user){
        assertNotNull(userRepository.findByUsername(user), "failed for: "+user);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,7"
    })
    public void test(int a, int b, int expeced){
        assertEquals(expeced, a+b);
    }
}
