package com.social.media.service.impl;

import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.repository.PostRepository;
import com.social.media.repository.UserRepository;
import com.social.media.service.PostService;
import com.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user=userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setUser(user);
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt( LocalDateTime.now());
        return postRepository.save(newPost);

    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findByPostId(postId);
        User user =userService.findUserById(userId);
        if (post.getUser().getId()!=user.getId()) {
            throw new Exception("You cant delete another user post");
        }
        postRepository.delete(post);

        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findByPostId(Integer postId) throws Exception {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new  Exception("Post not found with id: " + postId);
        }
        return optionalPost.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findByPostId(postId);
        User user =userService.findUserById(userId);
        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        }
        else user.getSavedPost().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likedPost(Integer postId, Integer userId) throws Exception {
        Post post = findByPostId(postId);
        User user =userService.findUserById(userId);

        if (post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }
        else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
