package com.example.demo.controller;

import com.example.demo.post.Post;
import com.example.demo.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * TemplateController class used to manage routing to application pages (templates).
 */
@Controller
@RequestMapping("/")
public class TemplateController {

    private static List<Post> posts = new ArrayList<>();

    @GetMapping("login")
    public String getLoginView(){
        return "login";
    }

//    @GetMapping("feed")
//    public String getFeedView(Model model){
//        model.addAttribute("posts", posts);
//        return "feed";
//    }

    @GetMapping("register")
    public String getRegistrationView(){
        return "register";
    }

    @GetMapping("personalprofile")
    public String getPersonalProfileView(){return "personalprofile";}

    @GetMapping("editpersonalprofile")
    public String getEditPersonalProfileView(){return "editpersonalprofile";}

    @GetMapping("messageinbox")
    public String getMessageInboxView(){return "messageinbox";}

}
