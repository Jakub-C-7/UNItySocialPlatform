package com.example.demo.friend;


import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

/**
 * FriendController Class provides the routing of business logic for friend application functions.
 *
 * @author jakub
 */
@Controller
@AllArgsConstructor
public class FriendController {

    AppUserRepository appUserRepository;
    FriendRepository friendRepository;
    FriendService friendService;

    @GetMapping(path = "friendlist")
    public String friendList(Principal principal, Model model){

        AppUser currentUser = appUserRepository.getById(
                appUserRepository.findByEmail(principal.getName()).get().getId());

        List<Friend> friends = friendRepository.findAddedFriends(currentUser);

        model.addAttribute("friends", friends);

        return "friendlist";

    }

    @GetMapping(path = "friendrequests")
    public String friendRequests(Principal principal, Model model){

        AppUser currentUser = appUserRepository.getById(
                appUserRepository.findByEmail(principal.getName()).get().getId());

        List<Friend> friends = friendRepository.findByUsersFriend(currentUser);

        model.addAttribute("friends", friends);

        return "friendrequests";

    }

    @PostMapping(path = "sendfriendrequest/{id}")
    public String newChat(@PathVariable Long id, Principal principal) {

        AppUser currentUser = appUserRepository.getById(
                appUserRepository.findByEmail(principal.getName()).get().getId());

        AppUser otherUser = appUserRepository.getById(id);

        friendService.sendFriendRequest(currentUser, otherUser);

        return "redirect:/profile/{id}";
    }

    @PostMapping(path = "denyfriendrequest/{id}")
    public String denyFriendRequest(@PathVariable Long id, Principal principal) {

        Friend friendRequest = friendRepository.getById(id);

        friendRepository.delete(friendRequest);

        return "redirect:/friendrequests?denysuccess";
    }

    @PostMapping(path = "acceptfriendrequest/{id}")
    public String acceptFriendRequest(@PathVariable Long id) {

        Friend friendRequest = friendRepository.getById(id);

        friendRequest.setAdded(true);

        friendRepository.save(friendRequest);

        Friend friendRequest2 = new Friend();
        friendRequest2.setUser(friendRequest.getUsersFriend());
        friendRequest2.setUsersFriend(friendRequest.getUser());
        friendRequest2.setAdded(true);

        friendRepository.save(friendRequest2);

        return "redirect:/friendrequests?acceptsuccess";
    }

    @PostMapping(path = "removefriend/{id}")
    public String removeFriend(@PathVariable Long id) {

        Friend friend = friendRepository.getById(id);

        friendRepository.delete(friend);

        return "redirect:/friendlist?removesuccess";
    }
}
