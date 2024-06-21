package com.social.media.controller;

import com.social.media.model.Reels;
import com.social.media.model.User;
import com.social.media.service.ReelsService;
import com.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    ReelsService reelsService;

    @Autowired
    UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){

        User reqUser=userService.findUserByJwt(jwt);
        Reels createReels = reelsService.createReels(reel,reqUser);
        return createReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels(){

       List<Reels> allReels = reelsService.findAllReels();
        return allReels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> createReels(@PathVariable Integer userId) throws Exception {

       List<Reels> createReels = reelsService.findUserReels(userId);
        return createReels;
    }
}
