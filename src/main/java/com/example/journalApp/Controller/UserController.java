package com.example.journalApp.Controller;


import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserRepository;
import com.example.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
   private UserService userService;

    @Autowired
    private UserRepository userRepository;

 /*   @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    Since, no user should be given access to view all users and its might be given to admin

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    we could make this publicly accessible, so that all public could create a new user
    so, we move this to public controller
  */

    @PutMapping
    //user has a right to update this only when he is logged in
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        //We store all the login info in Security Context of Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findUserByUsername(username);
        if(userInDb != null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

