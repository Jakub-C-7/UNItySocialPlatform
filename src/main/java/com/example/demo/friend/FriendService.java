package com.example.demo.friend;

import com.example.demo.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * FriendService Class contains methods to send and accept friend requests.
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
    public void acceptFriendRequest(Friend friendRequest){
        friendRequest.setAdded(true);

        friendRepository.save(friendRequest);
    }

}
