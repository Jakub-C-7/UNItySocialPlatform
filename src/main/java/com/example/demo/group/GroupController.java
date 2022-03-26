package com.example.demo.group;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.groupMember.GroupMember;
import com.example.demo.groupMember.GroupMemberRepository;
import com.example.demo.groupMember.GroupMemberRole;
import com.example.demo.groupMember.GroupMemberService;
import com.example.demo.groupPost.GroupPost;
import com.example.demo.groupPost.GroupPostRepository;
import com.example.demo.groupPost.GroupPostService;
import com.example.demo.post.Post;
import com.example.demo.postComment.PostComment;
import com.example.demo.postComment.PostCommentRepository;
import com.example.demo.postComment.PostCommentService;
import com.example.demo.postLike.PostLikeRepository;
import com.example.demo.postLike.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GroupController Class provides routing to the groups page.
 *
 * @author jakub
 */
@Controller
@AllArgsConstructor
public class GroupController {

    private final GroupMemberService groupMemberService;
    private final GroupService groupService;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final AppUserRepository appUserRepository;
    private final GroupPostService groupPostService;
    private final GroupPostRepository groupPostRepository;
    private final PostCommentRepository postCommentRepository;
    private final PostCommentService postCommentService;
    private final PostLikeRepository postLikeRepository;
    private final PostLikeService postLikeService;

    @GetMapping("group/{groupId}")
    public String getUserProfile(@PathVariable("groupId") Long groupId, Model model, Principal principal){
        boolean groupExists = groupRepository.findById(groupId).isPresent();

        if (!groupExists){
            return "redirect:/groups";
        }

        AppGroup group = groupRepository.getById(groupId);

        if (principal != null) {
            AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();
            GroupMember record = groupMemberRepository.findByGroupAndUser(group, loggedInUser);

            Boolean isAMember;
            Boolean memberPending;
            Boolean isAdmin;
            if (record != null && record.getAdded() == true){
                isAMember = true;
                memberPending = false;
                if (record.getRole() == GroupMemberRole.GROUP_ADMIN) {
                    isAdmin = true;
                }
                else {
                    isAdmin = false;
                }

            } else if (record != null && !record.getAdded()){
                memberPending = true;
                isAMember = false;
                isAdmin = false;
            }
            else {
                isAMember = false;
                memberPending = false;
                isAdmin = false;
            }
            model.addAttribute("isAMember", isAMember);
            model.addAttribute("memberPending", memberPending);
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("record", record);
        }

        List<GroupPost> posts = groupPostService.getPosts(group);
        model.addAttribute("posts", posts);
        model.addAttribute("principal", principal);
        model.addAttribute("group", group);
        model.addAttribute("postCommentService", postCommentService);
        model.addAttribute("groupPostService", groupPostService);

        return "group";
    }

    @PostMapping(path = "group/{gid}/deletepost/{id}")
    public String deletePost(@PathVariable Long id, @PathVariable Long gid ) {
        groupPostService.deletePost(id);

        return "redirect:/group/{gid}?deletesuccess";
    }

    @PostMapping("group/{groupId}")
    public String newPost(@PathVariable("groupId") Long id, GroupPost post, Principal principal, LocalDateTime localdatetime) {
        AppGroup group = groupRepository.findById(id).get();

        if (post.getPostContent().equals("")){
            return "redirect:/group/{groupId}?postempty";
        }

        groupPostService.createNewPost(post, group, principal, localdatetime.now());

        return "redirect:/group/{groupId}?postsuccess";

    }

    @GetMapping("groupadmininbox/{groupId}")
    public String getAdminInbox(@PathVariable("groupId") Long groupId, Model model, Principal principal){
        boolean groupExists = groupRepository.findById(groupId).isPresent();
        AppGroup group = groupRepository.getById(groupId);
        AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();
        GroupMember record = groupMemberRepository.findByGroupAndUser(group, loggedInUser);

        if (!groupExists){
            return "redirect:/groups";
        } else if (record == null) {
            return "redirect:/groups";
        } else if (record.getRole() != GroupMemberRole.GROUP_ADMIN) {
            return "redirect:/groups";
        }

        List<GroupMember> members = groupMemberRepository.findByGroupAndAddedFalse(group);

        model.addAttribute("members", members);

        model.addAttribute("group", group);

        return "groupadmininbox";
    }

    @GetMapping("managegroup/{groupId}")
    public String getManagingGroup(@PathVariable("groupId") Long groupId, Model model, Principal principal){
        boolean groupExists = groupRepository.findById(groupId).isPresent();
        AppGroup group = groupRepository.getById(groupId);
        AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();
        GroupMember record = groupMemberRepository.findByGroupAndUser(group, loggedInUser);

        if (!groupExists){
            return "redirect:/groups";
        } else if (record == null) {
            return "redirect:/groups";
        } else if (record.getRole() != GroupMemberRole.GROUP_ADMIN) {
            return "redirect:/groups";
        }

        List<GroupMember> members = groupMemberRepository.findByGroupAndAddedTrue(group);

        model.addAttribute("members", members);

        model.addAttribute("group", group);

        return "editgroup";
    }

    @GetMapping("editgroup/{groupId}")
    public String getEditingGroup(@PathVariable("groupId") Long groupId, Model model, Principal principal){
        boolean groupExists = groupRepository.findById(groupId).isPresent();
        AppGroup group = groupRepository.getById(groupId);
        AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();
        GroupMember record = groupMemberRepository.findByGroupAndUser(group, loggedInUser);

        if (!groupExists){
            return "redirect:/groups";
        } else if (record == null) {
            return "redirect:/groups";
        } else if (record.getRole() != GroupMemberRole.GROUP_ADMIN) {
            return "redirect:/groups";
        }

        model.addAttribute("group", group);

        return "editgroup";
    }

    @PostMapping("editgroup/{groupId}")
    public String editGroup(@PathVariable("groupId") Long groupId, EditGroupRequest request, Principal principal){
        AppGroup group = groupRepository.findById(groupId).get();
        boolean nameResult = groupService.editName(request, group);
        boolean descriptionResult = groupService.editDescription(request, group);
        boolean typeResult = groupService.editType(request, group);

        // If one of the updates succeeds.
        if (nameResult || descriptionResult || typeResult){

            return "redirect:/editgroup/{groupId}?success";

        } else {
            return "redirect:/editgroup/{groupId}?error";
        }

    }

    @GetMapping("memberlist/{groupId}")
    public String getMemberList(@PathVariable("groupId") Long groupId, Model model, Principal principal){
        boolean groupExists = groupRepository.findById(groupId).isPresent();
        AppGroup group = groupRepository.getById(groupId);

        if (!groupExists){
            return "redirect:/groups";
        }

        if (principal != null) {
            AppUser loggedInUser = appUserRepository.findByEmail(principal.getName()).get();
            GroupMember record = groupMemberRepository.findByGroupAndUser(group, loggedInUser);

            Boolean isAdmin;
            if (record != null && record.getAdded() == true) {
                if (record.getRole() == GroupMemberRole.GROUP_ADMIN) {
                    isAdmin = true;
                } else {
                    isAdmin = false;
                }
            } else {
                isAdmin = false;
            }
            model.addAttribute("isAdmin", isAdmin);
        }

        GroupMemberRole admin = GroupMemberRole.GROUP_ADMIN;
        GroupMemberRole user = GroupMemberRole.GROUP_USER;

        model.addAttribute("admin", admin);
        model.addAttribute("user", user);

        List<GroupMember> members = groupMemberRepository.findByGroupAndAddedTrue(group);

        model.addAttribute("members", members);

        model.addAttribute("group", group);

        model.addAttribute("principal", principal);

        return "groupmemberlist";
    }

    @PostMapping(path = "group/removemember/{id}/{groupId}")
    public String removeMember(@PathVariable Long id, @PathVariable Long groupId) {
        groupMemberRepository.deleteById(id);

        return "redirect:/memberlist/{groupId}?deletesuccess";
    }

    @PostMapping(path = "group/promotemember/{id}/{groupId}")
    public String promoteMember(@PathVariable Long id, @PathVariable Long groupId) {
        GroupMember member = groupMemberRepository.findById(id).get();

        groupMemberService.setAdmin(member);

        return "redirect:/memberlist/{groupId}?promotesuccess";
    }

    @PostMapping(path = "group/demotemember/{id}/{groupId}")
    public String demoteMember(@PathVariable Long id, @PathVariable Long groupId, Model model) {
        GroupMember member = groupMemberRepository.findById(id).get();

        groupMemberService.setUser(member);

        return "redirect:/memberlist/{groupId}?demotesuccess";
    }

    @PostMapping(path = "group/sendjoinrequest/{id}")
    public String sendGroupJoinRequest(@PathVariable Long id, Principal principal) {

        AppUser currentUser = appUserRepository.getById(
                appUserRepository.findByEmail(principal.getName()).get().getId());

        AppGroup group = groupRepository.getById(id);

        groupMemberService.addMemberRequest(group,currentUser);

        return "redirect:/group/{id}";
    }

    @PostMapping(path = "group/acceptjoinrequest/{id}/{gid}")
    public String acceptGroupJoinRequest(@PathVariable Long id,@PathVariable Long gid) {

        AppUser user = appUserRepository.getById(id);

        AppGroup group = groupRepository.getById(gid);

        groupMemberService.acceptMember(group, user);

        return "redirect:/groupadmininbox/{gid}?acceptsuccess";
    }

    @PostMapping(path = "group/denyjoinrequest/{id}/{gid}")
    public String denyGroupJoinRequest(@PathVariable Long id, @PathVariable Long gid ) {

        AppUser user = appUserRepository.getById(id);

        AppGroup group = groupRepository.getById(gid);

        groupMemberService.denyRequest(group, user);

        return "redirect:/groupadmininbox/{gid}?denysuccess";
    }

    @PostMapping(path = "/addcomment/{id}/{gid}")
    public String addComment(@PathVariable Long id, @PathVariable Long gid, PostComment postComment, Principal principal) {
        GroupPost post = groupPostRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        postComment.setGroupPost(post);
        postComment.setCommentAuthor(user);

        postCommentRepository.save(postComment);

        return "redirect:/group/{gid}";
    }


    @PostMapping(path = "/deletecomment/{id}/{gid}")
    public String deleteComment(@PathVariable Long id, @PathVariable Long gid) {

        postCommentRepository.deleteById(id);

        return "redirect:/group/{gid}";
    }

    @PostMapping(path = "/likepost/{id}/{gid}")
    public String likePost(@PathVariable Long id, @PathVariable Long gid, Principal principal) {
        GroupPost post = groupPostRepository.getById(id);
        AppUser user = appUserRepository.findByEmail(principal.getName()).get();

        if (postLikeRepository.findByLikeUserAndGroupPost(user, post) == null) {
            postLikeService.addGroupLike(post, principal);
        } else {
            postLikeService.removeGroupLike(post, principal);
        }

        return "redirect:/group/{gid}";
    }
}
