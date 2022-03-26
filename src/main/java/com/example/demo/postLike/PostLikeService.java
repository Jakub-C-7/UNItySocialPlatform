package com.example.demo.postLike;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.groupPost.GroupPost;
import com.example.demo.post.Post;
import com.example.demo.postLike.PostLike;
import com.example.demo.postLike.PostLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * PostLikeService Class contains methods to add and remove likes from posts.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final AppUserRepository appUserRepository;

    /**
     * Function for adding a new like.
     */
    public void addLike(Post post, Principal principal){

        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        PostLike like = new PostLike(post, user);

        postLikeRepository.save(like);
    }

    /**
     * Function for adding a new like to a group post.
     */
    public void addGroupLike(GroupPost post, Principal principal){

        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        PostLike like = new PostLike(post, user);

        postLikeRepository.save(like);
    }

    /**
     * Function for removing a like from a given feed post.
     */
    public void removeLike(Post post, Principal principal){

        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        PostLike like = postLikeRepository.findByLikeUserAndPost(user, post);

        postLikeRepository.delete(like);
    }

    /**
     * Function for removing a like from a given group post.
     */
    public void removeGroupLike(GroupPost post, Principal principal){

        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        PostLike like = postLikeRepository.findByLikeUserAndGroupPost(user, post);

        postLikeRepository.delete(like);
    }

    /**
     * Function for retrieving the number of likes on a given post.
     */
    public Integer getLikes(Post post) { return postLikeRepository.findAllByPost(post).size(); }

    /**
     * Function for retrieving all posts that are authored by a specific user using that user's id.
     */
    public List<PostLike> getPostLikesByUserId(AppUser user) { return postLikeRepository.findAllByLikeUser(user); }

}
