package com.example.demo.editpersonalpost;

import com.example.demo.groupPost.GroupPost;
import com.example.demo.groupPost.GroupPostRepository;
import com.example.demo.groupPost.GroupPostService;
import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * EditPersonalPostController Class provides routing to the editpersonalpost page.
 *
 * The editpersonalprofile page is accessed through the 'edit' button being clicked on a user's personal post.
 * Upon entering, a GET request is called which retrieves the postID from the URL path and enables the pre-population
 * of the text input field with the post's current content. Submitting the data in this text input field initiates
 * a POST request which calls the PostService to update the post and finally redirects the user back to the feed.
 *
 * @author jakub
 */
@Controller
@RequestMapping("editpersonalpost")
@AllArgsConstructor
public class EditPersonalPostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final GroupPostRepository groupPostRepository;
    private final GroupPostService groupPostService;

    @GetMapping(path = "/{id}")
    public String getPostToEdit(@PathVariable Long id, Model model, Principal principal) {
        Post post = postRepository.findById(id).get();
        if (post.getPostUser().getEmail().equals(principal.getName())) {
            model.addAttribute(post);
            Boolean editingGroupPost = false;
            model.addAttribute("editingGroupPost", editingGroupPost);
            return "editpersonalpost";
        } else {
            return "redirect:/feed";
        }
    }

    @PostMapping(path = "/{id}")
    public String editPost(@PathVariable Long id, String postContent) {

        postService.editPost(id, postContent);

        return "redirect:/feed?posteditsuccess";
    }

    @GetMapping(path = "/group{id}")
    public String getGroupPostToEdit(@PathVariable Long id, Model model, Principal principal) {
        GroupPost post = groupPostRepository.findById(id).get();

        if (post.getPostUser().getEmail().equals(principal.getName())) {
            model.addAttribute("post", post);
            Boolean editingGroupPost = true;
            model.addAttribute("editingGroupPost", editingGroupPost);
            return "editpersonalpost";
        } else {
            return "redirect:/groups";
        }
    }

    @PostMapping(path = "/group{id}/{gid}")
    public String editGroupPost(@PathVariable Long id, @PathVariable Long gid, String groupPostContent, Model model) {

        groupPostService.editPost(id, groupPostContent);

        return "redirect:/group/{gid}?posteditsuccess";
    }

}
