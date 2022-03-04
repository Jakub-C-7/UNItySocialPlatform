package com.example.demo.friend;

import com.example.demo.appuser.AppUser;
import com.example.demo.chat.Chat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * FriendService Class contains methods to add, remove, and view friends.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class FriendService {

    FriendRepository friendRepository;

    /**
     * Function for sending a friend request to a user.
     */
    public void sendFriendRequest(AppUser user, AppUser usersFriend){
        Friend friendRecord = new Friend();
        friendRecord.setUser(user);
        friendRecord.setUsersFriend(usersFriend);
        friendRecord.setAdded(false);

        friendRepository.save(friendRecord);
    }

    /**
     * Function for accepting a user's friend request and
     */
    public void acceptFriendRequest(AppUser usersFriend, AppUser user){
        Friend friendRecord = friendRepository.findByUserAndUsersFriend(user, usersFriend).get(0);
        friendRecord.setAdded(true);

        friendRepository.save(friendRecord);
    }

    /**
     * Function for removing a user from being a friend.
     */
    public void removeFriend(AppUser user, AppUser usersFriend){
        Friend friendRecord = friendRepository.findByUserAndUsersFriend(user, usersFriend).get(0);

        friendRepository.delete(friendRecord);
    }
}
