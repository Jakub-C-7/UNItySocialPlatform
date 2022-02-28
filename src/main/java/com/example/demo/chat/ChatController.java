package com.example.demo.chat;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

/**
 * ChatController Class provides the routing of business logic for specific chat application functions.
 *
 * Creating, editing, deleting chats.
 *
 * @author jakub
 */
@Controller
@AllArgsConstructor
public class ChatController {

    AppUserRepository appUserRepository;
    ChatRepository chatRepository;
    ChatService chatService;

    @PostMapping(path = "createnewchat/{id}")
    public String newPost(@PathVariable Long id, Principal principal) {

        AppUser currentUser = appUserRepository.getById(
                appUserRepository.findByEmail(principal.getName()).get().getId());

        AppUser otherUser = appUserRepository.getById(id);

        Boolean chatAlreadyExists1 = !chatRepository.findByParticipantOneAndParticipantTwo(currentUser, otherUser).isEmpty();
        Boolean chatAlreadyExists2 = !chatRepository.findByParticipantOneAndParticipantTwo(otherUser, currentUser).isEmpty();

        if (chatAlreadyExists1 || chatAlreadyExists2) {
            //TODO: Redirect to the existing chat
            // return "redirect:/messageinbox/{id}"
            return "redirect:/messageinbox";
        }

        chatService.createNewChat(currentUser, otherUser);

        return "redirect:/messageinbox";
    }
}
