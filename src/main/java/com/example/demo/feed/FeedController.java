package com.example.demo.feed;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.post.PostService;
import com.example.demo.postComment.PostComment;
import com.example.demo.postComment.PostCommentRepository;
import com.example.demo.postComment.PostCommentService;
import com.example.demo.postLike.PostLikeRepository;
import com.example.demo.postLike.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * FeedController Class provides routing to the feed page.
 *
 * Upon entering the feed page (GET request) the PostService is used to retrieve all valid user posts and populates
 * the feed page in order from newest to oldest. When a user attempts to create a new post (POST request),
 * the PostService is called to validate user input, create a new post, add it to the database as a new post,
 * and display it on the feed.
 *
 * @author jakub
 */
@Controller
@RequestMapping("feed")
@AllArgsConstructor
public class FeedController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final PostLikeService postLikeService;
    private final PostLikeRepository postLikeRepository;
    private final AppUserRepository appUserRepository;
    private final PostCommentRepository postCommentRepository;
    private final PostCommentService postCommentService;

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
    public String getAllPosts(Model model, Principal principal) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        model.addAttribute("principal", principal);
        model.addAttribute("postService", postService);
        model.addAttribute("postCommentService", postCommentService);

        return "feed";
    }

    @PostMapping(path = "/deletepost/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);

        return "redirect:/feed";
    }

    @PostMapping(path = "/deletepost/toprofile/{id}")
    public String deletePostPersonalProfile(@PathVariable Long id) {
        postService.deletePost(id);

        return "redirect:/personalprofile";
    }

    @PostMapping(path = "/likepost/{id}")
    public String likePost(@PathVariable Long id, Principal principal) {
        Post post = postRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        if (postLikeRepository.findByLikeUserAndPost(user, post) == null) {
            postLikeService.addLike(post, principal);
        } else {
            postLikeService.removeLike(post, principal);
        }

        return "redirect:/feed";
    }

    @PostMapping(path = "/likepost/toprofile/{id}")
    public String likePostPersonalProfile(@PathVariable Long id, Principal principal) {
        Post post = postRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        if (postLikeRepository.findByLikeUserAndPost(user, post) == null) {
            postLikeService.addLike(post, principal);
        } else {
            postLikeService.removeLike(post, principal);
        }

        return "redirect:/personalprofile";
    }

    @PostMapping(path = "/likepost/otheruserprofile/{id}/{pid}")
    public String likePostOtherUserProfile(@PathVariable Long id, @PathVariable Long pid, Principal principal) {
        Post post = postRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        if (postLikeRepository.findByLikeUserAndPost(user, post) == null) {
            postLikeService.addLike(post, principal);
        } else {
            postLikeService.removeLike(post, principal);
        }

        return "redirect:/profile/{pid}";
    }

    @PostMapping(path = "/addcomment/{id}")
    public String addComment(@PathVariable Long id, PostComment postComment, Principal principal) {
        Post post = postRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        postComment.setPost(post);
        postComment.setCommentAuthor(user);

        postCommentRepository.save(postComment);

        return "redirect:/feed";
    }

    @PostMapping(path = "/addcomment/toprofile/{id}")
    public String addCommentPersonalProfile(@PathVariable Long id, PostComment postComment, Principal principal) {
        Post post = postRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        postComment.setPost(post);
        postComment.setCommentAuthor(user);

        postCommentRepository.save(postComment);

        return "redirect:/personalprofile";
    }

    @PostMapping(path = "/addcomment/otheruserprofile/{id}/{pid}")
    public String addCommentOtherUserProfile(@PathVariable Long id, @PathVariable Long pid, PostComment postComment, Principal principal) {
        Post post = postRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        postComment.setPost(post);
        postComment.setCommentAuthor(user);

        postCommentRepository.save(postComment);

        return "redirect:/profile/{pid}";
    }

    @PostMapping(path = "/deletecomment/{id}")
    public String deleteComment(@PathVariable Long id) {

        postCommentRepository.deleteById(id);

        return "redirect:/feed";
    }

    @PostMapping(path = "/deletecomment/toprofile/{id}")
    public String deleteCommentPersonalProfile(@PathVariable Long id) {

        postCommentRepository.deleteById(id);

        return "redirect:/personalprofile";
    }

    @PostMapping(path = "/deletecomment/otheruserprofile/{id}/{pid}")
    public String deleteCommentOtherUserProfile(@PathVariable Long id, @PathVariable Long pid) {

        postCommentRepository.deleteById(id);

        return "redirect:/profile/{pid}";
    }
}
