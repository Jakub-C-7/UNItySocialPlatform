package com.example.demo.chatMessage;

import com.example.demo.appuser.AppUser;
import com.example.demo.chat.Chat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * ChatMessageService Class contains methods to create and manipulate messages.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class ChatMessageService {

    ChatMessageRepository chatMessageRepository;

    /**
     * Function for creating a new chat.
     */
    public void createNewMessage(Chat chat, AppUser user, String messageContent, LocalDateTime localDateTime){
        ChatMessage message = new ChatMessage();
        message.setAuthor(user);
        message.setMessageDateTime(localDateTime);
        message.setChat(chat);
        message.setMessageContent(messageContent);

        chatMessageRepository.save(message);
    }

}
