package com.example.demo.adminportal;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserService;
import com.example.demo.group.AppGroup;
import com.example.demo.group.GroupRepository;
import com.example.demo.group.GroupService;
import com.example.demo.post.Post;
import com.example.demo.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * AdminPortalController Class provides the routing of business logic for admin portal application functions.
 *
 * Managing users, posts, groups, group members, post comments, etc.
 *
 * @author jakub
 */
@Controller
@AllArgsConstructor
@RequestMapping("adminportal")
public class AdminPortalController {

    private final AppUserRepository appUserRepository;
    private final PostService postService;
    private final AppUserService appUserService;
    private final GroupService groupService;

    @GetMapping
    public String defaultRoute (){
        return "adminportal";
    }

    @GetMapping("manageusers")
    public String managingUsers (Model model, Principal principal){
        model.addAttribute("principal", principal);

        List<AppUser> users = appUserRepository.findAll();
        model.addAttribute("users", users);

        return "manageusers";
    }

    @GetMapping("manageposts")
    public String managingPosts (Model model){

        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        return "manageposts";
    }

    @GetMapping("managegroups")
    public String managingGroupsp (Model model){

        List<AppGroup> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "managegroups";
    }

    @PostMapping(path = "/deleteuser/{id}")
    public String deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);

        return "redirect:/adminportal/manageusers";
    }

    @PostMapping(path = "/deletepost/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);

        return "redirect:/adminportal/manageposts";
    }

    @PostMapping(path = "/deletegroup/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);

        return "redirect:/adminportal/managegroups";
    }

}
