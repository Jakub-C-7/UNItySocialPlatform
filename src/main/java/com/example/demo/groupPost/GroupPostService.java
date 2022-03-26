package com.example.demo.groupPost;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.group.AppGroup;
import com.example.demo.post.Post;
import com.example.demo.postLike.PostLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * PostService Class contains methods to create new posts in user groups.
 *
 * Takes user input to create new posts and save them in the database. Each post is assigned the author's email,
 * full name, and datetime of creation. Contains method to retrieve all posts from the database in descending
 * order from newest to oldest.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class GroupPostService {

    private final GroupPostRepository groupPostRepository;
    private final AppUserRepository appUserRepository;
    private final PostLikeRepository postLikeRepository;

    /**
     * Function for creating new posts.
     */
    public void createNewPost(GroupPost post, AppGroup group, Principal principal, LocalDateTime dateTime){

        Optional<AppUser> currentAppUser = appUserRepository.findByEmail(principal.getName());

//        var formattedCurrentDateTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));

        post.setPostDateTime(dateTime);
        post.setPostUser(currentAppUser.get());
        post.setGroup(group);

        groupPostRepository.save(post);
    }

    /**
     * Function for retrieving all posts in descending order by date.
     */
    public List<GroupPost> getPosts(AppGroup group) {
        return groupPostRepository.findAllByGroupSortedByDate(group);
    }

    /**
     * Function for deleting a post from the database using a post ID.
     * @param postId
     */
    public void deletePost(Long postId) {
        boolean exists = groupPostRepository.existsById(postId);
        if (!exists){
            throw new IllegalStateException("post with id " + postId + " does not exist");
        }
        groupPostRepository.deleteById(postId);
    }

    /**
     * Function for editing a post from the database using a post ID.
     * @param postId
     */
    public void editPost(Long postId, String postContent) {
        boolean exists = groupPostRepository.existsById(postId);
        if (!exists){
            throw new IllegalStateException("post with id " + postId + " does not exist");
        }

        GroupPost post = groupPostRepository.findById(postId).get();
        post.setPostContent(postContent);
        groupPostRepository.save(post);
    }

    /**
     * Function to get the like count of a post
     * @param post
     * @return
     */
    public Integer getLikeCount(GroupPost post) {

        if (postLikeRepository.findAllByGroupPost(post).isEmpty()) {
            return 0;
        } else {
            return postLikeRepository.findAllByGroupPost(post).size();
        }
    }

}
