package com.example.demo.chatMessage;

import com.example.demo.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ChatMessageRepository Interface performs message queries between the application and the database.
 *
 * Creating, deleting, editing messages and message info.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
