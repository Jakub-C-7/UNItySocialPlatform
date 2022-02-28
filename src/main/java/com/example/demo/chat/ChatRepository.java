package com.example.demo.chat;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //Finding chats for either user.
    List<Chat> findByParticipantOneOrParticipantTwo(AppUser participantOne, AppUser participantTwo);

    //Finding only chats that have both users in.
    List<Chat> findByParticipantOneAndParticipantTwo(AppUser participantOne, AppUser participantTwo);

}
