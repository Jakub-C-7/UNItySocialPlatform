package com.example.demo.group;

import com.example.demo.appuser.AppUser;
//import com.example.demo.groupMember.GroupMemberService;
import com.example.demo.groupMember.GroupMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AppGroup> getGroups(){
        return groupRepository.findAll();
    }

    public void createGroup(AppUser creator, String name, String description, String type ){

        AppGroup group = new AppGroup();

        group.setCreator(creator);
        group.setName(name);
        group.setDescription(description);
        group.setType(type);
        groupRepository.save(group);

        //add the user as an accepted member and admin of the group
        groupMemberService.addCreator(group, creator);
    }
}
