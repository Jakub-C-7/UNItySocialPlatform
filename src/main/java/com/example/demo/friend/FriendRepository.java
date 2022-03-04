package com.example.demo.friend;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FriendRepository Interface performs friend list queries between the application and the database.
 *
 * Adding, removing, and retrieving friends.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface FriendRepository extends JpaRepository<Friend, Long> {

    //find all the friends of a user
    List<Friend> findByUser(AppUser user);

    //find a specific friend record
    List<Friend> findByUserAndUsersFriend(AppUser user, AppUser usersFriend);

}
