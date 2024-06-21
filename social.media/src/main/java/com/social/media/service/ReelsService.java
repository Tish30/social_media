package com.social.media.service;

import com.social.media.model.Reels;
import com.social.media.model.User;

import java.util.List;

public interface ReelsService {
    public Reels createReels(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUserReels(Integer userId) throws Exception;

}
