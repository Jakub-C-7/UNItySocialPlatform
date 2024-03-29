package com.example.demo.friend;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    //find all the friends or sent friend requests of a user
    List<Friend> findByUser(AppUser user);

    //find all received friend requests
    List<Friend> findByUsersFriendAndAddedFalse(AppUser user);

    //find a specific friend record
    List<Friend> findByUserAndUsersFriend(AppUser user, AppUser usersFriend);

    //find all of a user's friends 1
    @Transactional
    @Query("SELECT c FROM Friend c WHERE c.added = true and c.usersFriend =:usersFriend")
    List<Friend> findAddedFriends1(AppUser usersFriend);

    //find all of a user's friends 2
    @Transactional
    @Query("SELECT c FROM Friend c WHERE c.added = true and c.user =:user")
    List<Friend> findAddedFriends2(AppUser user);

}
