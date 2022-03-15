package com.example.demo.groupMember;

import com.example.demo.appuser.AppUser;
import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * GroupMemberService Class contains methods to create, delete and edit group member records.
 *
 * @author jakub
 */
@Service
@AllArgsConstructor
public class GroupMemberService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    public void addCreator(Group group, AppUser creator){

        GroupMember groupMember = new GroupMember();

        groupMember.setUser(creator);
        groupMember.setGroup(group);
        groupMember.setAdded(true);
        groupMember.setRole(GroupMemberRole.GROUP_ADMIN);
        groupMemberRepository.save(groupMember);
    }

    public void addMember(Group group, AppUser user ){

        GroupMember groupMember = new GroupMember();

        groupMember.setUser(user);
        groupMember.setGroup(group);
        groupMember.setAdded(false);
        groupMember.setRole(GroupMemberRole.GROUP_USER);
        groupMemberRepository.save(groupMember);
    }

    public void acceptMember(Group group, AppUser user) {
        GroupMember record = groupMemberRepository.findByGroupAndUser(group, user);

        record.setAdded(true);

        groupMemberRepository.save(record);
    }

    public void setAdmin(Group group, AppUser user) {
        GroupMember record = groupMemberRepository.findByGroupAndUser(group, user);

        record.setRole(GroupMemberRole.GROUP_ADMIN);

        groupMemberRepository.save(record);
    }

    public void setUser(Group group, AppUser user) {
        GroupMember record = groupMemberRepository.findByGroupAndUser(group, user);

        record.setRole(GroupMemberRole.GROUP_USER);

        groupMemberRepository.save(record);
    }
}
