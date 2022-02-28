package com.example.demo.chatMessage;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.chat.Chat;
import com.example.demo.chat.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * ChatMessageController Class provides the routing of business logic for message application functions.
 *
 * Creating, editing, deleting chats.
 *
 * @author jakub
 */
@Controller
@AllArgsConstructor
public class ChatMessageController {

    AppUserRepository appUserRepository;
    ChatMessageService chatMessageService;
    ChatRepository chatRepository;

    @PostMapping(path = "createnewchatmessage/{id}")
    public String newChatMessage(@PathVariable Long id, Principal principal, String messageContent,
                                 LocalDateTime localDateTime) {

        AppUser currentUser = appUserRepository.findByEmail(principal.getName()).get();
        Chat chat = chatRepository.findById(id).get();

        chatMessageService.createNewMessage(chat, currentUser, messageContent, localDateTime.now() );

        return "redirect:/chat/{id}";
    }
}
