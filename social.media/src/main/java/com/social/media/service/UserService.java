package com.social.media.service;

import com.social.media.model.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Integer id) throws Exception;

    public User findUserByEmail(String email);

    User updateUser(User user, Integer id) throws Exception;

    public User followUser(Integer userId1 , Integer userId2) throws Exception;

    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);
}
