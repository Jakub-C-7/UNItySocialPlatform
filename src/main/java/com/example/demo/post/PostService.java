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

    //Function for creating new posts
    public void createNewPost(Post post, Principal principal, LocalDateTime dateTime){

        Optional<AppUser> currentAppUser = appUserRepository.findByEmail(principal.getName());

//        var formattedCurrentDateTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));

        post.setPostAuthorEmail(principal.getName());
        post.setPostAuthorName(currentAppUser.get().getFullName());
//        post.setPostDateTime(formattedCurrentDateTime);
        post.setPostDateTime(dateTime);

        postRepository.save(post);
    }

    //Function for retrieving all posts in order by date
    public List<Post> getPosts() {
        return postRepository.findAllByDate();
    }

}
