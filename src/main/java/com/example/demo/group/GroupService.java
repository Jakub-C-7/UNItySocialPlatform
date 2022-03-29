package com.example.demo.group;

import com.example.demo.appuser.AppUser;
//import com.example.demo.groupMember.GroupMemberService;
import com.example.demo.editpersonalprofile.EditProfileRequest;
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

    /**
     * Function which creates a new group adds the creator to it as an Admin.
     * @param creator
     * @param name
     * @param description
     * @param type
     */
    public void createGroup(AppUser creator, String name, String description, String type ){

        AppGroup group = new AppGroup();

        group.setCreator(creator);
        group.setName(name);
        group.setDescription(description);
        group.setType(type);
        groupRepository.save(group);

        groupMemberService.addCreator(group, creator);
    }

    /**
     * Function for deleting groups by group id.
     * @param id
     */
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public boolean editName(EditGroupRequest request, AppGroup group){
        if (request.getName() != ""){
                group.setName(request.getName());
                groupRepository.save(group);
                return true;
        }
        return false;
    }

    public boolean editDescription(EditGroupRequest request, AppGroup group){
        if (request.getDescription() != ""){
            group.setDescription(request.getDescription());
            groupRepository.save(group);
            return true;
        }
        return false;
    }

    public boolean editType(EditGroupRequest request, AppGroup group){
        if (request.getDescription() != ""){
            group.setType(request.getType());
            groupRepository.save(group);
            return true;
        }
        return false;
    }
}
