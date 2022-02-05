package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * TemplateController Class manages routing to a select few basic application pages (templates).
 *
 * The templates that the TemplateController routes to don't need a separate controller class which is
 * why a generic TemplateController is used.
 *
 * @author jakub
 */
@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginView(){
        return "login";
    }

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
