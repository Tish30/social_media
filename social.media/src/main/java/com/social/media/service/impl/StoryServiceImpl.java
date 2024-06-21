package com.social.media.service.impl;

import com.social.media.model.Story;
import com.social.media.service.StoryService;

import java.util.List;

public class StoryServiceImpl implements StoryService {
    @Override
    public Story createStory(Story story, Integer userId) {
        return null;
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) {
        return List.of();
    }
}
