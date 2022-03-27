package com.example.demo;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PostService postService;

    @Test
    public void contextLoads() {
    }

    @Test
	public void testCreatePost(){

        LocalDateTime dateTime = LocalDateTime.now();

        AppUser appUser = new AppUser(
                "firstname",
                "lastname",
                "first@last.gmail.com",
                "test",
                AppUserRole.USER);

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (!userExists) {
            appUserService.signUpUser(appUser);
        }

        AppUser author = appUserRepository.findByEmail(appUser.getEmail()).get();

        Post post = new Post("test", author, dateTime);

        postRepository.save(post);

		boolean postExists = postRepository.findById(post.getId()).isPresent();

		assertThat(postExists).isTrue();
	}

    @Test
    public void testDeletingPost(){

        AppUser appUser = new AppUser(
                "firstname",
                "lastname",
                "first@last.gmail.com",
                "test",
                AppUserRole.USER);

        Post post = postRepository.findByEmail(appUser.getEmail()).get();

        postRepository.delete(post);

        boolean postExists = postRepository.findById(post.getId()).isPresent();

        assertThat(postExists).isFalse();
    }

}
