package com.example.demo.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * GroupController Class provides routing to the groups page.
 *
 * @author jakub
 */
@Controller
@RequestMapping("group")
@AllArgsConstructor
public class GroupController {

    @GetMapping
    public String getAllGroups() {

        return "group";
    }
}
