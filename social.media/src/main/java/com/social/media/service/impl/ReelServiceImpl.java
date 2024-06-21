package com.social.media.service.impl;

import com.social.media.model.Reels;
import com.social.media.model.User;
import com.social.media.repository.ReelsRepository;
import com.social.media.service.ReelsService;
import com.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReelServiceImpl implements ReelsService {
    @Autowired
    ReelsRepository reelsRepository;

    @Autowired
    UserService userService;

    @Override
    public Reels createReels(Reels reels, User user) {
       Reels createReels = new Reels();
        createReels.setTitle(reels.getTitle());
        createReels.setVideo(reels.getVideo());
        createReels.setUser(user);

        return reelsRepository.save(createReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
