package com.example.demo.feed;

import com.example.demo.post.Post;
import com.example.demo.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@RequestMapping("feed")
@AllArgsConstructor
public class FeedController {

    private final PostService postService;

    private static List<Post> posts = new ArrayList<>();

    @PostMapping
    public RedirectView newPost(
            Post post,
            Principal principal,
            LocalDateTime localdatetime) {

        RedirectView redirectView = new RedirectView();

        if (post.getPostContent().equals("")){
            redirectView.setUrl("feed?postempty");
            return redirectView;
        }

        postService.createNewPost(post, principal, localdatetime.now());

        redirectView.setUrl("feed?postsuccess");

        return redirectView;
    }

    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        return "feed";
    }

}
