package com.example.demo.groups;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.group.AppGroup;
import com.example.demo.group.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * GroupController Class provides routing to the groups page.
 *
 * @author jakub
 */
@Controller
@RequestMapping("groups")
@AllArgsConstructor
public class GroupsController {

    private final GroupService groupService;
    private final AppUserRepository appUserRepository;

    @GetMapping
    public String getAllGroups(Model model) {
        List<AppGroup> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "groups";
    }

    @PostMapping
    public String createGroup(Principal principal, @RequestParam String name, @RequestParam String description, @RequestParam String type){
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        groupService.createGroup(user, name, description, type);

        return "redirect:/groups?success";
    }
}
