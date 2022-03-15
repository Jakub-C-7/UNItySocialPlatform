package com.example.demo.group;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.groupMember.GroupMember;
import com.example.demo.groupMember.GroupMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * GroupController Class provides routing to the groups page.
 *
 * @author jakub
 */
@Controller
@RequestMapping("group")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final AppUserRepository appUserRepository;

    @GetMapping
    public String defaultRoute() {


        return "redirect:/groups";
    }

    @GetMapping("{groupId}")
    public String getUserProfile(@PathVariable("groupId") Long groupId, Model model, Principal principal){
        boolean groupExists = groupRepository.findById(groupId).isPresent();

        if (!groupExists){
            return "redirect:/groups";
        }

        AppGroup group = groupRepository.getById(groupId);

        if (principal != null) {
            AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();
            GroupMember record = groupMemberRepository.findByGroupAndUser(group, loggedInUser);

            Boolean isAMember;
            Boolean memberPending;
            if (record != null && record.getAdded() == true){
                isAMember = true;

            } else if (record != null && record.getAdded() == false){
                memberPending = true;
                isAMember = false;
            }
            else {
                isAMember = false;
            }


//            //Check to see if the current user is a member
//            if (groupMemberRepository.findByGroupAndUser(group, loggedInUser) != null){
//                isAMember = true;
//                friendRecord = friendRepository.findByUserAndUsersFriend(loggedInUser, user).get(0);
//                model.addAttribute("friendRecord", friendRecord);
//
//            } else if (!friendRepository.findByUserAndUsersFriend(user, loggedInUser).isEmpty()) {
//                isAMember = true;
//                friendRecord = friendRepository.findByUserAndUsersFriend(user, loggedInUser).get(0);
//                model.addAttribute("friendRecord", friendRecord);
//            }
//
//            else {
//                isAMember = false;
//            }
//            model.addAttribute("isAMember", isAMember);
//
        }

        model.addAttribute("group", group);

//        List<Post> posts = postService.getPostsByUserId(groupId);
//        model.addAttribute("posts", posts);

        return "group";
    }
}
