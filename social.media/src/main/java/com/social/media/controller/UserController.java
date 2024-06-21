package com.social.media.controller;

import com.social.media.model.User;
import com.social.media.repository.UserRepository;
import com.social.media.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/api/users")
    public List<User> getUser() {
      return userRepository.findAll();
    }

    @GetMapping("/api/users/{userId}")
    public User findById(@PathVariable("userId") Integer id) throws Exception {
        User user = userService.findUserById(id);
        return user;

    }
    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt ,@RequestBody User user) throws Exception {
        User reqUser =userService.findUserByJwt(jwt);
        User updatedUser=userService.updateUser(user,reqUser.getId());
        return updatedUser;
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new Exception("user not found with this id ");
        }
        userRepository.delete(user.get());
        return "user deleted successfully with id " +id;
    }

    @PutMapping("/api/users/follow/" +
            "{userId1}/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId1 , @PathVariable Integer userId2) throws Exception {
        User reqUser =userService.findUserByJwt(jwt);
        User followUser = userService.followUser(reqUser.getId(), userId2);
        return followUser;

    }


    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {

        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){

        User user=userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
