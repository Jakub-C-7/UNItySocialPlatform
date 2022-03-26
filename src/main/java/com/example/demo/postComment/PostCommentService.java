package com.example.demo.postComment;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.groupPost.GroupPost;
import com.example.demo.post.Post;
import com.example.demo.postLike.PostLike;
import com.example.demo.postLike.PostLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * PostCommentService Class contains methods to add and remove comments from posts.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final AppUserRepository appUserRepository;

    /**
     * Function for adding a new comment.
     */
    public void addComment(Post post, Principal principal){

        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        PostComment comment = new PostComment(post, user);

        postCommentRepository.save(comment);
    }

    /**
     * Function for removing a comment from a given post.
     */
    public void removeComment(Long id){
        postCommentRepository.delete(postCommentRepository.findById(id).get());
    }

    /**
     * Function for retrieving all comments under a specific post.
     */
    public List<PostComment> getPostComments(Post post) { return postCommentRepository.findAllByPost(post); }

    /**
     * Function for retrieving all comments under a specific group post.
     */
    public List<PostComment> getGroupPostComments(GroupPost post) {
        return postCommentRepository.findAllByGroupPost(post);
    }

}
