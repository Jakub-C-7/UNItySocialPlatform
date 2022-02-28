package com.example.demo.messageinbox;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.chat.Chat;
import com.example.demo.chat.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * MessageInboxController Class provides routing to the messageinbox page.
 *
 * @author jakub
 */
@Controller
@RequestMapping("messageinbox")
@AllArgsConstructor
public class MessageInboxController {

    ChatService chatService;
    AppUserRepository appUserRepository;

    @GetMapping
    public String getAllChats(Model model, Principal principal) {
        AppUser currentUser = appUserRepository.getById(
                appUserRepository.findByEmail(principal.getName()).get().getId());

        List<Chat> chats = chatService.getChats(currentUser, currentUser);
        model.addAttribute("chats", chats);

        model.addAttribute("principal", principal);

        return "messageinbox";
    }


}
