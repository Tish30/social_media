package com.social.media.controller;

import com.social.media.model.Comment;
import com.social.media.model.User;
import com.social.media.service.CommentService;
import com.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,
                                 @PathVariable("postId") Integer postId) throws Exception {
        User user=userService.findUserByJwt(jwt);

        Comment createComment=commentService.createComment(comment,postId, user.getId());
        return createComment;
    }
    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment (@RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer commentId) throws Exception {
        User user=userService.findUserByJwt(jwt);

        Comment likeComment=commentService.likeComment(commentId, user.getId());
        return likeComment;
    }
}
