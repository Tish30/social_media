package com.social.media.service;

import com.social.media.model.Story;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story,Integer userId);
    public List<Story> findStoryByUserId(Integer userId);
}
