package com.example.demo.chat;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.chatMessage.ChatMessage;
import com.example.demo.chatMessage.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * ChatController Class provides the routing of business logic for chat application functions.
 *
 * Creating, editing, deleting chats.
 *
 * @author jakub
 */
@Controller
@AllArgsConstructor
@RequestMapping("chat")
public class ChatController {

    AppUserRepository appUserRepository;
    ChatRepository chatRepository;
    ChatService chatService;
    ChatMessageRepository chatMessageRepository;

    @GetMapping
    public String defaultRoute (){
        return "redirect:/messageinbox";
    }

    @GetMapping(path = "{id}")
    public String getChat(@PathVariable Long id, Principal principal, Model model){
        Chat chat = chatRepository.findById(id).get();

        if (!chat.getParticipantOne().getEmail().equals(principal.getName()) && !chat.getParticipantTwo().getEmail().equals(principal.getName())){
            return "redirect:/messageinbox";
        }
                List<ChatMessage> messages = chatMessageRepository.findByChat(chat);

        model.addAttribute("messages", messages);
        model.addAttribute("chat", chat);

        return "chat";
    }

    @PostMapping(path = "createnewchat/{id}")
    public String newChat(@PathVariable Long id, Principal principal) {

        AppUser currentUser = appUserRepository.getById(
                appUserRepository.findByEmail(principal.getName()).get().getId());

        AppUser otherUser = appUserRepository.getById(id);

        Boolean chatAlreadyExists1 = !chatRepository.findByParticipantOneAndParticipantTwo(currentUser, otherUser).isEmpty();
        Boolean chatAlreadyExists2 = !chatRepository.findByParticipantOneAndParticipantTwo(otherUser, currentUser).isEmpty();

        //If chat already exists, redirect to that chat.
        if (chatAlreadyExists1) {
            Long chatId = chatRepository.findByParticipantOneAndParticipantTwo(currentUser, otherUser).get(0).getId();

             return "redirect:/chat/" + chatId;
        } else if (chatAlreadyExists2) {
            Long chatId =  chatRepository.findByParticipantOneAndParticipantTwo(otherUser, currentUser).get(0).getId();

            return "redirect:/chat/" + chatId;
        }

        chatService.createNewChat(currentUser, otherUser);

        return "redirect:/messageinbox";
    }
}
