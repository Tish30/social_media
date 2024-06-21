package com.social.media.service.impl;

import com.social.media.model.Comment;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.repository.CommentRepository;
import com.social.media.repository.PostRepository;
import com.social.media.service.CommentService;
import com.social.media.service.PostService;
import com.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);

        Post post=postService.findByPostId(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment comment=findCommentById(commentId);
        User user=userService.findUserById(userId);
        if (!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }
        else comment.getLiked().remove(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {

        Optional<Comment> cmt=commentRepository.findById(commentId);

        if (cmt.isEmpty()){
            throw new Exception("Comment not exist");
        }
        return cmt.get();
    }
}
