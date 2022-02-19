package com.example.demo.post;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * PostService Class contains methods to create new posts.
 *
 * Takes user input to create new posts and save them in the database. Each post is assigned the author's email,
 * full name, and datetime of creation. Contains method to retrieve all posts from the database in descending
 * order from newest to oldest.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;

    /**
     * Function for creating new posts.
     */
    public void createNewPost(Post post, Principal principal, LocalDateTime dateTime){

        Optional<AppUser> currentAppUser = appUserRepository.findByEmail(principal.getName());

//        var formattedCurrentDateTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));

        post.setPostDateTime(dateTime);
        post.setPostUser(currentAppUser.get());

        postRepository.save(post);
    }

    /**
     * Function for retrieving all posts in descending order by date.
     */
    public List<Post> getPosts() {
        return postRepository.findAllByDate();
    }

    /**
     * Function for retrieving all posts that are authored by a specific user using that user's id.
     */
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findAllByAuthorIdSortedByDate(userId);
    }

    /**
     * Function for deleting a post from the database using a post ID.
     * @param postId
     */
    public void deletePost(Long postId) {
        boolean exists = postRepository.existsById(postId);
        if (!exists){
            throw new IllegalStateException("post with id " + postId + " does not exist");
        }
        postRepository.deleteById(postId);
    }

}
