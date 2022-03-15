package com.example.demo.group;

import com.example.demo.post.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * GroupController Class provides routing to the groups page.
 *
 * @author jakub
 */
@Controller
@RequestMapping("groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public String getAllGroups(Model model) {
        List<AppGroup> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "groups";
    }
}
