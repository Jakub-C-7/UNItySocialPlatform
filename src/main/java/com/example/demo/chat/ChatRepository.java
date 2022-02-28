package com.example.demo.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ChatRepository Interface performs Chat queries between the application and the database.
 *
 * Creating, deleting, editing chats and chat info.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
