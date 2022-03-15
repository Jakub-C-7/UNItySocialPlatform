package com.example.demo.group;

import com.example.demo.appuser.AppUser;
import com.example.demo.groupMember.GroupMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * GroupService Class contains methods to create, delete and edit groups.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberService groupMemberService;

    public void createGroup(AppUser creator, String name, String description, String type ){

        Group group = new Group();

        group.setCreator(creator);
        group.setName(name);
        group.setDescription(description);
        group.setType(type);
        groupRepository.save(group);

        //add the user as an accepted member and admin of the group
        groupMemberService.addCreator(group, creator);
    }
}
