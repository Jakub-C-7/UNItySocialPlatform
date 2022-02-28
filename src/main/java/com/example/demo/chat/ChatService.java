package com.example.demo.chat;

import com.example.demo.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ChatService Class contains methods to create and manipulate chats.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class ChatService {

    ChatRepository chatRepository;

    /**
     * Function for retrieving all chats in descending order by date.
     */
    public List<Chat> getChats(AppUser p1, AppUser p2) {
        return chatRepository.findByParticipantOneOrParticipantTwo(p1, p2);
    }

    /**
     * Function for creating a new chat.
     */
    public void createNewChat(AppUser u1, AppUser u2){
        Chat chat = new Chat();
        chat.setParticipantOne(u1);
        chat.setParticipantTwo(u2);

        chatRepository.save(chat);
    }
}
